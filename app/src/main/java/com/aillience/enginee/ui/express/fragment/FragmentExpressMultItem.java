package com.aillience.enginee.ui.express.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.aillience.enginee.util.BasicParameters;
import com.aillience.enginee.util.MyLog;
import com.yfl.library.base.adapter.BaseRecyclerAdapter;
import com.yfl.library.support.MultiItemTypeSupport;
import com.yfl.library.recycler.MyRecyclerView;
import com.aillience.enginee.R;
import com.aillience.enginee.mvp.model.entity.ExpressEntity;
import com.aillience.enginee.ui.express.adapter.recycle.ExpressAdapter2;
import com.aillience.enginee.ui.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 普通recycle多类型显示
 * @author yfl
 */
@SuppressLint("StaticFieldLeak")
public class FragmentExpressMultItem extends BaseFragment {

    @BindView(R.id.rv_express)
    MyRecyclerView rvExpress;

    private ExpressAdapter2 expressAdapter;
    private MultiItemTypeSupport<ExpressEntity> support;

    private static FragmentExpressMultItem fragmentExpressMultItem;
    private List<ExpressEntity> entityList;

    public static FragmentExpressMultItem newInstance(){
        if (fragmentExpressMultItem == null) {
            synchronized (FragmentExpressMultItem.class) {
                fragmentExpressMultItem = new FragmentExpressMultItem();
            }
        }
        return fragmentExpressMultItem;
    }

    @Override
    public void injectAction() {
        if (fragmentComponent != null){
            fragmentComponent.inject(this);
        }
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
                support=new MultiItemTypeSupport<ExpressEntity>() {
            @Override
            public int getLayId(int viewType) {
                if(viewType==1){
                    return R.layout.item_express2;
                }
                return R.layout.item_express;
            }

            @Override
            public int getViewType(int position, ExpressEntity expressEntity) {
                if (position% BasicParameters.INT_two==0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        expressAdapter= new ExpressAdapter2() {
            @Override
            protected MultiItemTypeSupport<ExpressEntity> getSupport() {
                return support;
            }

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
        rvExpress.setLayoutManager(false,true,3);
///       rvExpress.addItemDecoration();
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
