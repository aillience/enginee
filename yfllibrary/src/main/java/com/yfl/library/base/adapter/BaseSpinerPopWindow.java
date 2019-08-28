package com.yfl.library.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yfl.library.R;
import com.yfl.library.base.BaseViewHolder;

import java.util.List;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-04-15
 * @description: 重写PopupWindow，用于一些下拉列表框显示
 * @lastUpdateTime 2019-04-15
 * #更新内容
 * ===============================
 **/
public class BaseSpinerPopWindow extends PopupWindow {

    private LayoutInflater inflater;
    private ListView mListView;
    private List<String> list;
    private MyAdapter mAdapter;
    private int defaultPosition = 0;
    private Context mContext;

    public BaseSpinerPopWindow(Context context, List<String> list, AdapterView.OnItemClickListener clickListener) {
        super(context);
        mContext = context;
        inflater= LayoutInflater.from(context);
        this.list=list;
        init(clickListener);
    }

    //更新所选位置
    public void updatePosition(int pos){
        defaultPosition = pos;
        if(mAdapter == null){
            mAdapter = new MyAdapter();
        }
        mAdapter.notifyDataSetChanged();
    }

    private void init(AdapterView.OnItemClickListener clickListener){
        View view = inflater.inflate(R.layout.layout_pop_window, null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0x1c232c);
//        setBackgroundDrawable(dw);
        setClippingEnabled(false);
        //add by yfl on 20190604 取消边框四周的阴影
        setBackgroundDrawable(null);
        mListView = view.findViewById(R.id.listview);
        if(mAdapter == null){
            mAdapter=new MyAdapter();
        }
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(clickListener);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BaseViewHolder holder;
//            convertView=inflater.inflate(R.layout.item_custom_spinner, null);
//            holder=new BaseViewHolder(convertView);
            if(convertView==null){
                convertView=inflater.inflate(R.layout.item_custom_spinner, null);
                holder=new BaseViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder=(BaseViewHolder) convertView.getTag();
            }
            if(defaultPosition != position){
                //所选位置
                holder.getView(R.id.relayout_tittle).setBackgroundResource(R.color.grey_300);
                holder.setTextColor(R.id.tv_title,R.color.black);
            }else {
                holder.getView(R.id.relayout_tittle).setBackgroundResource(R.color.green_8c);
                holder.setTextColor(R.id.tv_title,R.color.white);
            }
            holder.setText(R.id.tv_title,getItem(position).toString());
            return convertView;
        }
    }
}
