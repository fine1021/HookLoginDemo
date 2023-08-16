package com.wind.imlib.connect.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by yexiaokang on 2023/8/16
 */
public class SignHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }

    public String getCertificateSHA1Fingerprint() {
        return "";
    }
}
