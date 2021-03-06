package com.example.xiaomage.xingvoices.model;

import android.text.TextUtils;

import com.example.xiaomage.xingvoices.utils.Constants;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * To encapsulate the retrofit .
 */

public class RetrofitClient {

    private static final String TAG = "RetrofitClient";

    private static Retrofit.Builder sBuilder = null;

    private static Lock sLock = new ReentrantLock();

    private RetrofitClient() {
    }

    public static <T> T buildService(Class<T> clz) {
        return getBuilder().build().create(clz);
    }

    public static <T> T buildWeiXinService(Class<T> clz) {
        return getWeiXinBuilder().build().create(clz);
    }

    public static <T> T buildDownloadVoiceService(Class<T> clz){
        return getDownloadVoiceBuilder().build().create(clz);
    }

    public static <T> T buildDownloadWxHeadPicService(Class<T> clz){
        return getDownloadWxHeadPicBuilder().build().create(clz);
    }

    public static Retrofit.Builder getBuilder() {
        return getBuilder(Constants.ContentType.JSON);
    }

    public static Retrofit.Builder getWeiXinBuilder() {
        return getWeiXinBuilder(Constants.ContentType.JSON);
    }

    public static Retrofit.Builder getDownloadVoiceBuilder(){
        return getDownloadVoiceBuilder(Constants.ContentType.JSON);
    }

    public static Retrofit.Builder getDownloadWxHeadPicBuilder(){
        return getDownloadWxHeadPicBuilder(Constants.ContentType.JSON);
    }

    /**
     * Do some job such as bind base url , add network interceptor and sign .
     * Normal request can use {@link #buildService} .
     *
     * @param contentType contentType of this request
     * @return Retrofit.Builder
     */
    public static Retrofit.Builder getBuilder(String contentType) {

        sBuilder = null;

        if (TextUtils.isEmpty(contentType)) {
            contentType = Constants.ContentType.JSON;
        }
        final String finalContentType = contentType;
        sLock.lock();
        try {
            if (null == sBuilder) {
                sBuilder = new Retrofit.Builder();
                sBuilder.baseUrl(Constants.BASE_URL);
                sBuilder.addConverterFactory(GsonConverterFactory.create());
            }
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            okBuilder.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader(Constants.AppSign.K_CONTENT_TYPE, finalContentType)
                            .build();
                    return chain.proceed(request);
                }
            });

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null,new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            },null);

            okBuilder.sslSocketFactory(sslContext.getSocketFactory());

            sBuilder.client(okBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
        return sBuilder;
    }

    public static Retrofit.Builder getWeiXinBuilder(String contentType) {

        sBuilder = null;

        if (TextUtils.isEmpty(contentType)) {
            contentType = Constants.ContentType.JSON;
        }
        final String finalContentType = contentType;
        sLock.lock();
        try {
            if (null == sBuilder) {
                sBuilder = new Retrofit.Builder();
                sBuilder.baseUrl(Constants.WEXIN_BASE_URL);
                sBuilder.addConverterFactory(GsonConverterFactory.create());
            }
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader(Constants.AppSign.K_CONTENT_TYPE, finalContentType)
                            .build();
                    return chain.proceed(request);
                }
            });

            sBuilder.client(okBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
        return sBuilder;
    }

    public static Retrofit.Builder getDownloadVoiceBuilder(String contentType){
        sBuilder = null;

        if (TextUtils.isEmpty(contentType)) {
            contentType = Constants.ContentType.JSON;
        }
        final String finalContentType = contentType;
        sLock.lock();
        try {
            if (null == sBuilder) {
                sBuilder = new Retrofit.Builder();
                sBuilder.baseUrl(Constants.VOICE_URL);
                sBuilder.addConverterFactory(GsonConverterFactory.create());
            }
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            okBuilder.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader(Constants.AppSign.K_CONTENT_TYPE, finalContentType)
                            .build();
                    return chain.proceed(request);
                }
            });

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null,new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            },null);

            okBuilder.sslSocketFactory(sslContext.getSocketFactory());

            sBuilder.client(okBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
        return sBuilder;
    }

    public static Retrofit.Builder getDownloadWxHeadPicBuilder(String contentType){
        sBuilder = null;

        if (TextUtils.isEmpty(contentType)) {
            contentType = Constants.ContentType.JSON;
        }
        final String finalContentType = contentType;
        sLock.lock();
        try {
            if (null == sBuilder) {
                sBuilder = new Retrofit.Builder();
                sBuilder.baseUrl(Constants.WEXIN_HEAD_PIC);
                sBuilder.addConverterFactory(GsonConverterFactory.create());
            }
            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
            okBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader(Constants.AppSign.K_CONTENT_TYPE, finalContentType)
                            .build();
                    return chain.proceed(request);
                }
            });

            sBuilder.client(okBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
        return sBuilder;
    }
}
