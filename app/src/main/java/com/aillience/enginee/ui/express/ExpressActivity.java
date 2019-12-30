package com.aillience.enginee.ui.express;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.aillience.enginee.util.BasicParameters;
import com.aillience.enginee.util.MyLog;
import com.yfl.library.alphatabs.AlphaTabsIndicator;
import com.yfl.library.widget.ClearEditText;
import com.aillience.enginee.R;
import com.aillience.enginee.listener.RequestCallBack;
import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.presenter.impl.ExpressPresenterImpl;
import com.aillience.enginee.mvp.view.IExpressView;
import com.aillience.enginee.ui.express.adapter.frag.ExpressFragmentAdapter;
import com.aillience.enginee.ui.base.BaseActivity;
import com.aillience.enginee.ui.express.fragment.FragmentExpressList;
import com.aillience.enginee.ui.express.fragment.FragmentExpressMultItem;
import com.aillience.enginee.ui.express.fragment.FragmentExpressRecycle;

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
 * @author yfl
 */

public class ExpressActivity extends BaseActivity implements IExpressView, RequestCallBack<ExpressBean> {
    @BindView(R.id.cdt_express_number)
    ClearEditText cdtExpressNumber;
    @BindView(R.id.Sp_express_type)
    Spinner spExpressType;
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
    protected int getContentViewLayoutId() {
        return R.layout.activity_express;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initSpinner();
        initAlphaIndicator();
    }

    private void initSpinner() {
///        String[] types = this.getResources().getStringArray(R.array.expressTypeName);
        spExpressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mToast.show(position + "当前位置为:" + spExpressType.getSelectedItem().toString());
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
                if (BasicParameters.INT_default == position) {
                    alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
                } else if (BasicParameters.INT_one == position) {
                    alphaIndicator.getCurrentItemView().removeShow();
                } else if (position == BasicParameters.INT_two) {
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
            MyLog.d("包名" + nn);
        }
    }

    @Override
    protected boolean handleCallback(Message msg) {
        switch (msg.what){
            case 1:
                refreshAdapter();
                break;
            default:break;
        }
        return true;
    }

    @Override
    public String getExpressNumber() {
        return cdtExpressNumber.getText().toString().trim();
    }

    @OnClick(R.id.btn_express_search)
    public void onViewClicked() {
        Map<String, String> map = new HashMap<>(2);
        map.put("type", "zhongtong");
        map.put("postid", getExpressNumber());
        expressPresenterImpl.searchExpress(this, map);
    }

    @Override
    public void beforeRequest() {
        MyLog.i("开始请求啦");
    }

    @Override
    public void success(ExpressBean data) {
        MyLog.i("接口返回Message：" + data.getMessage());
        if (data.getData().size() > 0) {
            mToast.show("已成功获取信息");
            expressBean.setData(data.getData());
            MyLog.i("刷新list：" + data.getData().size());
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
