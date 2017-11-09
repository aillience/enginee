package com.aillience.enginee.ui.adapter.recycle;

import android.content.Context;

import com.yfl.library.base.adapter.BaseRecyclerAdapter;
import com.yfl.library.base.BaseViewHolder;
import com.aillience.enginee.R;
import com.aillience.enginee.mvp.model.entity.ExpressEntity;


import java.util.List;

/**
 * Happy every day.
 * Created by yfl on 2017/9/18 0018
 * explain: 快递查询的结果显示adapter，MyRecyclerView控件
 */

public abstract class ExpressAdapter extends BaseRecyclerAdapter<ExpressEntity> {
    private Context context;
//    private List<ExpressEntity> expressEntityList;
    protected abstract List<ExpressEntity> getExpressEntityList();
    public ExpressAdapter(Context con){
        this.context=con;
//        this.expressEntityList=entityList;
    }
    @Override
    protected Context getContext() {
        return context;
    }


    @Override
    protected List<ExpressEntity> getData() {
        return getExpressEntityList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_express;
    }
    @Override
    protected void bindViewHolder(BaseViewHolder holder, ExpressEntity item, int position) {
        holder.setText(R.id.tv_item_context,item.getContext());
        holder.setText(R.id.tv_item_location,item.getLocation());
        holder.setText(R.id.tv_item_time,item.getTime());
    }
}
