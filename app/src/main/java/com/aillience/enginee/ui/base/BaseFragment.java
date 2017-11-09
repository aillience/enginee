package com.aillience.enginee.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aillience.enginee.app.MyApp;
import com.aillience.enginee.dagger.component.DaggerFragmentComponent;
import com.aillience.enginee.dagger.component.FragmentComponent;
import com.aillience.enginee.dagger.module.FragmentModule;
import com.aillience.enginee.mvp.base.IBasePresenter;

import butterknife.ButterKnife;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7 0007
 * explain: fragment基类
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment{
    protected FragmentComponent fragmentComponent;
    protected T mIPresenter;

    private View mFragmentView;

    public abstract void injectAction();

    public abstract void initViews(View view);

    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        injectAction();
        if(mIPresenter!=null){
            mIPresenter.onCreate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mFragmentView);
//            initViews(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mIPresenter!=null){
            mIPresenter.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentView=null;
    }

    private void initFragmentComponent(){
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((MyApp)MyApp.getAppContext()).getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }
}
