package com.aillience.enginee.net;



import com.aillience.enginee.mvp.model.bean.ExpressBean;
import com.aillience.enginee.mvp.model.bean.JokeBean;
import com.aillience.enginee.mvp.model.bean.UserBean;
import com.aillience.enginee.mvp.model.entity.JokeEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Happy every day.
 * Created by yfl on 2017/9/6 0006
 * explain: 网络接口服务
 */

interface ApiService {
    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto( @Header("Cache-Control") String cacheControl,
            @Url String photoPath);

    @GET("api/APP1.0.aspx?method={method}&userName={userName}&pwd={pwd}")
    Observable<UserBean> Login(@Path("method") String method, @Path("userName") String userName,
                                  @Path("pwd") int pwd);

    @GET("api/APP1.0.aspx?")
    Observable<UserBean> Login(@Query("method") String method, @Query("userName") String userName,
                               @Query("pwd") String pwd);
    @POST("customer/login")
    Observable<UserBean> Login(@Header("Cache-Control") String cacheControl,@QueryMap Map<String,Object> map);
    @GET("query?")
    Observable<ExpressBean> Express(@Header("Cache-Control") String cacheControl, @QueryMap Map<String,String> map);
    @GET("xiaohua.json")
    Observable<JokeBean> GetJokeBean(@Header("Cache-Control") String cacheControl);
    @GET("xiaohua.json")
    Observable<List<JokeEntity>> GetJokeList(@Header("Cache-Control") String cacheControl);
}
