package com.aillience.enginee.ui.fragment;

import android.annotation.SuppressLint;

import android.view.View;

import com.aillience.enginee.util.MyLog;
import com.yfl.library.base.adapter.BaseRecyclerAdapter;
import com.yfl.library.recycler.GridItemDecoration;
import com.yfl.library.recycler.MyRecyclerView;
import com.aillience.enginee.R;
import com.aillience.enginee.mvp.model.entity.ExpressEntity;
import com.aillience.enginee.ui.adapter.recycle.ExpressAdapter;
import com.aillience.enginee.ui.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 普通recycle显示
 */
@SuppressLint("StaticFieldLeak")
public class FragmentExpressRecycle extends BaseFragment {

    @BindView(R.id.rv_express)
    MyRecyclerView rvExpress;

    protected ExpressAdapter expressAdapter;

    private List<ExpressEntity> entityList;

    private static FragmentExpressRecycle fragmentExpressRecycle;

    public static FragmentExpressRecycle newInstance(){
        if (fragmentExpressRecycle == null) {
            synchronized (FragmentExpressRecycle.class) {
                fragmentExpressRecycle = new FragmentExpressRecycle();
            }
        }
        return fragmentExpressRecycle;
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
        return R.layout.fragment_express_recycler;
    }

    private void initAdapter() {
        expressAdapter= new ExpressAdapter(getContext()) {
            @Override
            protected List<ExpressEntity> getExpressEntityList() {
                return entityList;
            }
        };
        expressAdapter.setViewClick(new BaseRecyclerAdapter.viewClick() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                MyLog.i("Click position="+position);
                rvExpress.scrollToPosition(position);
            }

            @Override
            public void onItemLongClick(View view, Object item, int position) {

            }
        });
        rvExpress.setLayoutManager(true,true,2);
        rvExpress.addItemDecoration(new GridItemDecoration.Builder(getContext())
                .setHorizontalSpan(R.dimen.x2)
                .setVerticalSpan(R.dimen.x2)
                .setColorResource(R.color.colorPrimary)
                .setShowLastLine(false)
                .build());
        rvExpress.setAdapter(expressAdapter);
    }
    public void notifyDataSetChanged(){
        if(expressAdapter!=null){
            expressAdapter.notifyDataSetChanged();
        }
    }
    public void setEntityList(List<ExpressEntity> expressEntityList){
        this.entityList=expressEntityList;
    }
}
