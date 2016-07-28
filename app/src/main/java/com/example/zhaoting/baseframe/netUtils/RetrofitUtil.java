package com.example.zhaoting.baseframe.netUtils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhaoting on 16/7/28.
 */
public class RetrofitUtil {
    private Retrofit mRetrofit;


    private boolean DEBUG = false;

    private static class SingletonHolder {
        private static RetrofitUtil instance = new RetrofitUtil();
    }

    private RetrofitUtil() {
        //add headers
        Interceptor netWorkInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuild = originalRequest
                        .newBuilder()
                        .addHeader("app_key", Constans.app_key)
                        .addHeader("app_secret", Constans.app_secret)
                        .addHeader("device", Constans.device)
                        .addHeader("identifier", Constans.identifier)
                        .addHeader("app_version", Constans.app_version);
                if (Constans.user_unique_key != null) {
                    requestBuild.addHeader("user_unique_key", Constans.user_unique_key);
                }
                if (Constans.access_token != null) {
                    requestBuild.addHeader("access_token", Constans.access_token);
                }
                Request request = requestBuild.build();
                return chain.proceed(request);
            }
        };

        //logInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder mOkHttpClientBuild = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(netWorkInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS);
        if (isDEBUG()) {
            mOkHttpClientBuild.addInterceptor(loggingInterceptor);
        }

        OkHttpClient mOkHttpClient = mOkHttpClientBuild.build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

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
