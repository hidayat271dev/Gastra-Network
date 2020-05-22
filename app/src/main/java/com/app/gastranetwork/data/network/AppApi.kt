package com.app.gastranetwork.data.network

import android.content.Context
import android.util.Log
import com.app.gastranetwork.util.AppConstants
import com.ashokvarma.gander.GanderInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class AppApi {

    companion object {
        var instance = AppApi()
    }

    fun getRequest(baseUrl: String = "", withBaseURL: Boolean = true, withTokenAuth: Boolean = false, withDebugProgress: Boolean = false, context: Context? = null): Retrofit? {
        return getRetrofitBuilder(baseUrl = baseUrl, withBaseURL = withBaseURL, withTokenAuth = withTokenAuth, withDebugProgress = withDebugProgress, context = context)?.build()
    }

    private fun getBaseUrl(): String {
        return AppConstants.APP_BASE_URL
    }

    private fun getRetrofitBuilder(baseUrl: String = "", withBaseURL: Boolean = false, withTokenAuth: Boolean = false, withDebugProgress: Boolean = false, context: Context? = null): Retrofit.Builder? {
        try {
            val retrofitBuilder = Retrofit.Builder()
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.addHeader("Content-Type", "application/json")
                if (withTokenAuth) {
                    builder.addHeader("x-access-token", "")
                }
                val request = builder.build()
                chain.proceed(request)
            }

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor)

            if (withDebugProgress) {
                context?.let {
                    httpClient.addInterceptor(
                        GanderInterceptor(it)
                            .showNotification(true)
                            .maxContentLength(250000L)
                            .retainDataFor(GanderInterceptor.Period.FOREVER)
                            .redactHeader("Authorization")
                    )
                }
            }

            httpClient.connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)

            retrofitBuilder.client(httpClient.build())

            if(withBaseURL) {
                retrofitBuilder.baseUrl(getBaseUrl())
            } else {
                retrofitBuilder.baseUrl(baseUrl)
            }

            retrofitBuilder.addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(
                    Schedulers.io()
                )
            )
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
            return retrofitBuilder
        } catch (exception: Throwable) {
            Log.e("AppApi exception", exception.message)
            return null
        } catch (exception: SocketTimeoutException) {
            Log.e("AppApi exception", exception.message)
            return null
        }
    }

}
