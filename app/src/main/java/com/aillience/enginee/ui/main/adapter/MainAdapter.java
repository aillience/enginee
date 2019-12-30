package com.aillience.enginee.ui.main.adapter;

import android.content.Context;
import android.widget.Button;

import com.aillience.enginee.R;
import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.base.adapter.BaseRecyclerAdapter;

import java.util.List;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-12-25
 * @description: 就是一个普通类
 * @lastUpdateTime 2019-12-25
 * #更新内容
 * ===============================
 **/
public class MainAdapter extends BaseRecyclerAdapter<String> {
    private List<String> list;
    private Context context;
    public MainAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }
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
        return R.layout.item_main;
    }

    @Override
    protected void bindViewHolder(BaseViewHolder holder, String item, int position) {
        Button btn = holder.getView(R.id.btn_model);
        btn.setText(item);
        btn.setClickable(false);
    }
}
