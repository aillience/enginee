package com.aillience.enginee.net;

import android.support.annotation.NonNull;

import com.aillience.enginee.app.MyApp;

import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.model.bean.JokeBean;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.model.entity.JokeEntity;
import com.aillience.enginee.util.MyLog;
import com.aillience.enginee.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: Retrofit管理器 这里是借鉴一位前辈的经验做出的改装。
 * @author yfl
 */
@SuppressWarnings("unused")
public class RetrofitManager {
    /**
     * 接口服务对象
     */
    private ApiService apiService;
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    private static volatile OkHttpClient sOkHttpClient;

    public RetrofitManager(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        apiService=retrofit.create(ApiService.class);
    }
    public ApiService getApiService(){
        return apiService;
    }
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(MyApp.getAppContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor).build();
                }
            }
        }
        return sOkHttpClient;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                MyLog.d("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @SuppressWarnings("ConstantConditions")
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            MyLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            MyLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
///            MyLog.i(String.format(Locale.getDefault(),"response is next:%n %s",response.headers().get("Warning")));
            return response;
        }
    };
    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    private String getCacheControl() {
        return NetUtil.isNetworkAvailable() ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    /**
     * 登录接口
     * @param objectMap 条件
     * @return UserBean对象
     */
    public Observable<UserBean> getLogin(Map<String,Object> objectMap){
        return apiService.Login(getCacheControl(),objectMap);
    }

    /**
     * 快递查询接口
     * @param stringMap 条件
     * @return ExpressBean对象
     */
    public Observable<ExpressBean> getExpress(Map<String,String> stringMap){
        return apiService.Express(getCacheControl(),stringMap);
    }

    /**
     * 笑话接口
     * @return JokeBean对象
     */
    public Observable<JokeBean> getJoke(){
        return apiService.GetJokeBean(getCacheControl());
    }
    public Observable<List<JokeEntity>> getJokeList(){
        return apiService.GetJokeList(getCacheControl());
    }
}
