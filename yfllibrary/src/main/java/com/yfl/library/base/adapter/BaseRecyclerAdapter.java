package com.yfl.library.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yfl.library.R;
import com.yfl.library.base.BaseViewHolder;
import com.yfl.library.listeners.DiffCallBack;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Happy every day.
 * Created by yfl on 2017/9/18 0018
 * explain: RecyclerAdapter基类
 */
@SuppressWarnings("unused")
@SuppressLint("RecyclerView")
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    protected abstract Context getContext();
    protected abstract List<T> getData();
    protected abstract int getLayoutId();
    protected abstract void bindViewHolder(BaseViewHolder holder, T item, int position);
    private viewClick clickEvent;

    private ScheduledThreadPoolExecutor executor;
    private final int CODE_UPDATE = 1;
    private List<T> oldDatas;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_UPDATE) {//取出Result
                DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                diffResult.dispatchUpdatesTo(BaseRecyclerAdapter.this);
            }
        }
    };
    public interface viewClick<T>{
        void onItemClick(View view, T item, int position);
        void onItemLongClick(View view, T item, int position);
    }
    public void setViewClick(viewClick action){
        this.clickEvent=action;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(),parent ,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder,  final int position) {
        //绑定BaseViewHolder的数据显示，监听等
        bindViewHolder(holder,getItem(position),position);
        if(clickEvent!=null){
            final View finalConvertView = holder.getConvertView();
            finalConvertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickEvent.onItemClick(v,getItem(position),position);
                }
            });
            finalConvertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickEvent.onItemLongClick(v,getItem(position),position);
                    return false;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return (getData()!=null)?getData().size():0;
    }
    private T getItem(int position){
        return (getData()==null) ?null:getData().get(position);
    }
    //插入数据
    public void addData(int position,T item){
        getData().add(position,item);
        notifyItemInserted(position);
    }
    //删除数据
    public void deleteData(int position){
        getData().remove(position);
        notifyItemRemoved(position);
    }

    //批量插入数据
    public void addAllData(List<T> items){
        refreshAdapter(items);
    }

    /**
     * 刷新数据
     * @param addData 新增的数据
     */
    private void refreshAdapter(final List<T> addData){
        if(executor == null){
            executor = new ScheduledThreadPoolExecutor(1);
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //放在子线程中计算DiffResult
                oldDatas = getData();
                getData().addAll(addData);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack<>(oldDatas, getData()), true);
                Message message = mHandler.obtainMessage(CODE_UPDATE);
                //obj存放DiffResult
                message.obj = diffResult;
                message.sendToTarget();
            }
        });
    }

    //getResources().getColor() 过时，用此代替
    protected int getNewColor(int colorId){
        return ContextCompat.getColor(getContext(),colorId);
    }

    //fromHtml 过时问题
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected Spanned getFromHtml(String htmlString){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlString,Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(htmlString);
        }
    }

    //取像素-- 14 *2
    protected float sp2px(int size) {
        float scaledDensity = getContext().getResources().getDisplayMetrics().scaledDensity;
        return size * scaledDensity;
    }

    //add by yfl on  将一个字符串中包含的所有另一个字符串中的字符标红
    protected SpannableStringBuilder getNewSpannable(String oldString, String conString){
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(oldString);
        if(!TextUtils.isEmpty(conString)){
            int color = ContextCompat.getColor(getContext(), R.color.btn_logout_normal);
            for (int i = 0;i< oldString.length();i++){
                String aWord = oldString.substring(i,i+1);
                if(!TextUtils.isEmpty(aWord) && conString.contains(aWord)){
                    //指定词中，包含设置字体颜色
                    stringBuilder.setSpan(new ForegroundColorSpan(color),
                            i, i+1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }
            }
        }
        return stringBuilder;
    }
}
