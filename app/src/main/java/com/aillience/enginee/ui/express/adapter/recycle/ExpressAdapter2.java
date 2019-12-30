package com.aillience.enginee.ui.express.adapter.recycle;

import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.base.adapter.MultiItemBaseAdapter;
import com.aillience.enginee.R;
import com.aillience.enginee.mvp.model.entity.ExpressEntity;

/**
 * Happy every day.
 * Created by yfl on 2017/9/20 0020
 * explain: 作用释义
 */

public abstract class ExpressAdapter2 extends MultiItemBaseAdapter<ExpressEntity> {
    @Override
    protected void bindViewHolder(BaseViewHolder holder, ExpressEntity item, int position) {
        holder.setText(R.id.tv_item_context,item.getContext());
        holder.setText(R.id.tv_item_location,item.getLocation());
        holder.setText(R.id.tv_item_time,item.getTime());
    }
}
