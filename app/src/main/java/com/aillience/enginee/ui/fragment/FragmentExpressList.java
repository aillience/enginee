package com.aillience.enginee.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.View;
import android.widget.ListView;
import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.base.adapter.BaseCustomAdapter;
import com.aillience.enginee.R;
import com.aillience.enginee.mvp.model.entity.ExpressEntity;
import com.aillience.enginee.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 普通list显示
 */
@SuppressLint("StaticFieldLeak")
public  class FragmentExpressList extends BaseFragment {
    @BindView(R.id.lv_express)
    ListView lvExpress;

    private BaseCustomAdapter<ExpressEntity> customAdapter;
    private static FragmentExpressList fragmentExpressList;
    private List<ExpressEntity> entityList=new ArrayList<>();
    public static FragmentExpressList newInstance(){
        if (fragmentExpressList == null) {
            synchronized (FragmentExpressList.class) {
                fragmentExpressList = new FragmentExpressList();
            }
        }
        return fragmentExpressList;
    }

    @Override
    public void injectAction() {
        if (fragmentComponent != null)
            fragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initAdapter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_express_list;
    }

    private void initAdapter() {
        customAdapter = new BaseCustomAdapter<ExpressEntity>() {
            @Override
            protected Context getContext() {
                return getActivity();
            }

            @Override
            protected List<ExpressEntity> getData() {
                return entityList;
            }

            @Override
            protected int getLayoutId() {
                return R.layout.item_express;
            }

            @Override
            protected void convert(BaseViewHolder holder, ExpressEntity item) {
                holder.setText(R.id.tv_item_context, item.getContext());
                holder.setText(R.id.tv_item_location, item.getLocation());
                holder.setText(R.id.tv_item_time, item.getTime());
            }
        };
        lvExpress.setAdapter(customAdapter);
    }
    public void notifyDataSetChanged(){
        if(customAdapter!=null){
            customAdapter.notifyDataSetChanged();
        }
    }
    public void setEntityList(List<ExpressEntity> expressEntityList){
        this.entityList=expressEntityList;
    }

}
