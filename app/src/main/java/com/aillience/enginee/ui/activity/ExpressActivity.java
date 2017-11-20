package com.aillience.enginee.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.yfl.library.alphatabs.AlphaTabsIndicator;
import com.yfl.library.widget.ClearEditText;
import com.aillience.enginee.R;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.presenter.impl.ExpressPresenterImpl;
import com.aillience.enginee.mvp.view.IExpressView;
import com.aillience.enginee.ui.adapter.frag.ExpressFragmentAdapter;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.ui.fragment.FragmentExpressList;
import com.aillience.enginee.ui.fragment.FragmentExpressMultItem;
import com.aillience.enginee.ui.fragment.FragmentExpressRecycle;
import com.aillience.enginee.util.MLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Happy every day.
 * Created by yfl on 2017/9/13 0013
 * explain: 快递查询
 */

public class ExpressActivity extends BaseActivity implements IExpressView, RequestCallBack<ExpressBean> {
    @BindView(R.id.cdt_express_number)
    ClearEditText cdtExpressNumber;
    @BindView(R.id.Sp_express_type)
    Spinner SpExpressType;
    @BindView(R.id.btn_express_search)
    Button btnExpressSearch;
    @BindView(R.id.vp_express)
    ViewPager vpExpress;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    @Inject
    ExpressPresenterImpl expressPresenterImpl;


    protected ExpressFragmentAdapter expressFragmentAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    private ExpressBean expressBean = new ExpressBean();
    private FragmentExpressList fragmentExpressList;
    private FragmentExpressRecycle fragmentExpressRecycle;
    private FragmentExpressMultItem fragmentExpressMultItem;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_express;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initSpinner();
        initAlphaIndicator();
    }

    private void initSpinner() {
//        String[] types = this.getResources().getStringArray(R.array.expressTypeName);
        SpExpressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mToast.show(position + "当前位置为:" + SpExpressType.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initFragment() {
        fragmentExpressList = FragmentExpressList.newInstance();
        fragmentExpressRecycle = FragmentExpressRecycle.newInstance();
        fragmentExpressMultItem = FragmentExpressMultItem.newInstance();
        fragmentList.add(fragmentExpressList);
        fragmentList.add(fragmentExpressRecycle);
        fragmentList.add(fragmentExpressMultItem);
    }

    private void initViewPager() {
        initFragment();
        expressFragmentAdapter = new ExpressFragmentAdapter(getSupportFragmentManager()) {
            @Override
            protected List<Fragment> getFragmentList() {
                return fragmentList;
            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
                } else if (1 == position) {
                    alphaIndicator.getCurrentItemView().removeShow();
                } else if (2 == position) {
                    alphaIndicator.removeAllBadge();
                }
            }
        };
        vpExpress.setAdapter(expressFragmentAdapter);
        vpExpress.addOnPageChangeListener(expressFragmentAdapter);
    }

    private void initAlphaIndicator() {
        initViewPager();
        alphaIndicator.setViewPager(vpExpress);
        alphaIndicator.getTabView(0).showNumber(6);
        alphaIndicator.getTabView(1).showNumber(128);
        alphaIndicator.getTabView(2).showNumber(88);
//        alphaIndicator.getTabView(3).showPoint();
    }

    @Override
    protected void injectAction() {
        if (mActivityComponent != null) {
            mActivityComponent.inject(this);
            String nn = mActivityComponent.getActivity().getLocalClassName();
            MLog.d("包名" + nn);
        }
    }

    @Override
    protected boolean handleCallback(Message msg) {
        switch (msg.what){
            case 1:
                refreshAdapter();
                break;
        }
        return true;
    }

    @Override
    public String getExpressNumber() {
//        return cdtExpressNumber.getText().toString().trim();
        return "450920803566";
    }

    @OnClick(R.id.btn_express_search)
    public void onViewClicked() {
//        mToast.show("点击查询");
        Map<String, String> map = new HashMap<>();
        map.put("type", "zhongtong");
        map.put("postid", getExpressNumber());
        expressPresenterImpl.SearchExpress(this, map);
    }

    @Override
    public void beforeRequest() {
        MLog.i("开始请求啦");
    }

    @Override
    public void success(ExpressBean data) {
        MLog.i("接口返回Message：" + data.getMessage());
        if (data.getData().size() > 0) {
            mToast.show("已成功获取信息");
            expressBean.setData(data.getData());
            MLog.i("刷新list：" + data.getData().size());
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void onError(String errorMsg) {
        mToast.show(errorMsg);
    }
    private void refreshAdapter(){
        if(fragmentExpressList!=null){
            fragmentExpressList.setEntityList(expressBean.getData());
            fragmentExpressList.notifyDataSetChanged();
        }
        if(fragmentExpressRecycle!=null){
            fragmentExpressRecycle.setEntityList(expressBean.getData());
            fragmentExpressRecycle.notifyDataSetChanged();
        }
        if(fragmentExpressMultItem!=null){
            fragmentExpressMultItem.setEntityList(expressBean.getData());
            fragmentExpressMultItem.notifyDataSetChanged();
        }
    }

}
