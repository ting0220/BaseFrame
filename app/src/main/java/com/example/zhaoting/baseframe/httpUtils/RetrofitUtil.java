package com.example.zhaoting.baseframe.httpUtils;


import com.example.zhaoting.baseframe.utils.SharedPManager;
import com.example.zhaoting.baseframe.utils.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhaoting on 16/7/28.
 */
public class RetrofitUtil {
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;


    private boolean DEBUG = true;

    private static class SingletonHolder {
        private static RetrofitUtil instance = new RetrofitUtil();
    }

    public RetrofitUtil() {
        initOkHttp();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();


    }

    private void initOkHttp() {
        //add headers
        Interceptor netWorkInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuild = originalRequest
                        .newBuilder()
                        .addHeader("app_key", Constans.APP_KEY)
                        .addHeader("app_secret", Constans.APP_SECRET)
                        .addHeader("device", Constans.DEVICE)
                        .addHeader("identifier", SharedPManager.getInstance().getDeviceId())
                        .addHeader("app_version", Utils.getInstance().getAppVersionName());
                if (!SharedPManager.getInstance().getUserUniqueKey().equals("")) {
                    requestBuild.addHeader("user_unique_key", SharedPManager.getInstance().getUserUniqueKey());
                }
                if (!SharedPManager.getInstance().getAccessToken().equals("")) {
                    requestBuild.addHeader("access_token",SharedPManager.getInstance().getAccessToken());
                }
                Request request = requestBuild.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder mOkHttpClientBuild = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(netWorkInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS);

        if (isDEBUG()) {
            //logInterceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            mOkHttpClientBuild.addInterceptor(loggingInterceptor);
        }
        mOkHttpClient = mOkHttpClientBuild.build();
    }

    public static RetrofitUtil getInstance() {
        return SingletonHolder.instance;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public boolean isDEBUG() {
        return DEBUG;
    }

    public void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }
}
