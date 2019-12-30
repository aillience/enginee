package com.aillience.enginee.ui.list.adapter;

import android.content.Context;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.view.View;

import com.aillience.enginee.R;
import com.aillience.enginee.util.PublicToast;
import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.base.adapter.BaseDiffAdapter;

import java.util.List;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-12-27
 * @description: 就是一个普通类
 * @lastUpdateTime 2019-12-27
 * #更新内容
 * ===============================
 **/
public class PartAdapter extends BaseDiffAdapter<String> {
    private Context context;
    private List<String> list;
    public PartAdapter(Context context1,List<String> list1){
        this.context = context1;
        this.list = list1;
        listDiffer = new AsyncListDiffer<>(PartAdapter.this,new PartItemCallback());
    }

    /**
     * 上下文
     *
     * @return Context
     */
    @Override
    protected Context getContext() {
        return context;
    }

    @Override
    protected List<String> getData() {
        return list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_part;
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, String item, int position) {
        holder.setText(R.id.tv_item_context,item);
    }

    @Override
    public void setViewClick(itemClickEvent<String> action) {
        super.setViewClick(new itemClickEvent<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                PublicToast toast = new PublicToast(context);
                toast.show("点击列表:"+item);
            }

            @Override
            public void onItemLongClick(View view, String item, int position) {

            }
        });
    }
}
