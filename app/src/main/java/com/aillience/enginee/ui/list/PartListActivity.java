package com.aillience.enginee.ui.list;

import android.os.Bundle;
import android.view.View;

import com.aillience.enginee.R;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.ui.list.adapter.PartAdapter;
import com.aillience.enginee.util.MyLog;
import com.yfl.library.recycler.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ===============================
 * 千万不要出现 B U G ，出现就 G G
 * THE BEST CODE IS NO CODE
 * ===============================
 *
 * @author:yfl
 * @date: 2019-12-27
 * @description: 就是一个普通类 数据列表刷新
 * @lastUpdateTime 2019-12-27
 * #更新内容
 * ===============================
 **/
public class PartListActivity extends BaseActivity {

    @BindView(R.id.rv_view)
    MyRecyclerView rvView;

    private List<String> dataList = new ArrayList<>();
    private PartAdapter partAdapter;
    /**
     * 每次最大增加
     */
    private int maxAddCount = 10;
    /**
     * 获取布局ID
     *
     * @return 布局id
     */
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_part;
    }

    /**
     * 初始化布局以及View控件
     *
     * @param savedInstanceState 保存状态
     */
    @Override
    protected void initView(Bundle savedInstanceState) {
        partAdapter = new PartAdapter(this, dataList);
        rvView.setLayoutManager(true,true,1);
        rvView.setAdapter(partAdapter);
    }

    @Override
    protected void injectAction() {

    }

    private void addData(){
        int len = dataList.size();
        for(int i = len;i< len+maxAddCount;i++){
            dataList.add(getResources().getString(R.string.add_content,i+1));
        }
        partAdapter.submitList();
        MyLog.d(String.format(Locale.CHINA,"一共有%d条数据",partAdapter.getItemCount()));
    }

    @OnClick({R.id.iv_return,R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_add:
                addData();
                break;
            default:break;
        }
    }
}
