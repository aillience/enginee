package com.aillience.enginee.app;

import android.app.Activity;

import java.util.Stack;

/**
 * Happy every day.
 * Created by yfl on 2017/9/7
 * explain: Activity管理类
 */

public class MyActivityManager {
    private static MyActivityManager activityManager;
    private Stack<Activity> activityStack;
    private MyActivityManager(){
        //构造器私有，通过newInstance获取
    }
    static MyActivityManager newInstance(){
        if(activityManager==null){
            synchronized (MyActivityManager.class){
                activityManager=new MyActivityManager();
            }
        }
        return activityManager;
    }
    /**
     * 添加一个activity
     */
    public void addOneActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<>();
        }
        if(!activityStack.contains(activity)){
            activityStack.add(activity);
        }
    }
    /**
     *移除指定activity
     */
    public void removeOneActivity(Activity activity){
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }
    /**
     * 结束所有
     */
    private void finishAllActivity(){
        for(Activity activity:activityStack){
            if(activity!=null){
                activity.finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 退出应用程序
     */
    public void exitApp() {
        try {
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
