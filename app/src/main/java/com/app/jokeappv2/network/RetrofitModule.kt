package com.app.jokeappv2.network

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Muthu Narayanan on 19/11/2021.
 */
@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getRetrofitWithBaseUrl(okHttpClient:OkHttpClient, baseUrl:String):Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @ApplicationScope
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient {
        val interceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain):okhttp3.Response {
                val newRequest = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build()
                return chain.proceed(newRequest)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @ApplicationScope
    fun httpLoggingInterceptor():HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    fun getApiInterface(): ApiInterface {
        val retrofitModule = RetrofitModule()
        val okHttpClient = retrofitModule.getOkHttpClient(retrofitModule.httpLoggingInterceptor())
        val retrofit = retrofitModule.getRetrofit(okHttpClient)
        return retrofitModule.getInterface(retrofit)
    }

    fun getApiInterface(baseUrl:String): ApiInterface {
        val retrofitModule = RetrofitModule()
        val okHttpClient = retrofitModule.getOkHttpClient(retrofitModule.httpLoggingInterceptor())
        val retrofit = retrofitModule.getRetrofitWithBaseUrl(okHttpClient, baseUrl)
        return retrofitModule.getInterface(retrofit)
    }
}