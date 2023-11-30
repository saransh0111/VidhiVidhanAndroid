package com.teams.vidhividhan.data.di

import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.codenicely.bebroker.utils.network.ResponseHandler


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.teams.vidhividhan.Urls
import com.teams.vidhividhan.baseApplication.BaseApplication.Companion.context

import com.teams.vidhividhan.data.network.MarketApi
import com.teams.vidhividhan.data.network.ProfileApi
import com.teams.vidhividhan.data.prefrences.SharedPrefs

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    factory { AuthInterceptor(get()) }
    factory { NetworkInterceptor(get()) }
    factory { provideOkHttpClient(get(), get()) }

    single(named("apiModule")) { provideBaseRetrofit(get()) }
    single(named("googleModule")) { provideGoogleBaseRetrofit(get()) }
    single { ResponseHandler() }
    single { provideBaseRetrofit(get()).create(MarketApi::class.java) }
    single { provideBaseRetrofit(get()).create(ProfileApi::class.java) }
}


/**
 * Use the [providerGSON] to provide GSON
 * @return GSONBuilder*/
fun providerGSON(): Gson =
    GsonBuilder()
        .setLenient()
        .serializeNulls()
        .create()

/**
 * Use the [provideInterceptor] to provide a HttpLoggingInterceptor
 * @return HttpLoggingInterceptor*/
fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return interceptor
}


class AuthInterceptor(sharedPrefs: SharedPrefs) :
    Interceptor {

    private var sharedPrefsLocal: SharedPrefs = sharedPrefs

    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        var url = req.url

        Log.d("urlAttacked1", url.toString());

        val accessToken = sharedPrefsLocal.accessToken
//        val otpToken = sharedPrefsLocal.otpToken

        Log.d("urlAttacked2", url.toString());


        if (accessToken != "") {
            Log.d("tokenAya", accessToken)
            req = req.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .url(url).build()
        }

        val response = chain.proceed(req)
        try {
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}

class NetworkInterceptor(sharedPrefs: SharedPrefs) :
    Interceptor {

    private var sharedPrefsLocal: SharedPrefs = sharedPrefs
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request());
        val otpToken = response.headers["otp-token"]

        val accessToken = response.headers["access-token"]

        if (otpToken != null) {
//                sharedPrefsLocal.otpToken =  otpToken
        }

        if (accessToken != null) {

            sharedPrefsLocal.accessToken = accessToken

        }
        return response
    }

}

/**
 * Use the [provide Client] to provide a OkHttpClient
 * @return OkHttpClient*/
fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    networkInterceptor: NetworkInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(provideInterceptor())
        .addInterceptor(
            ChuckerInterceptor.Builder(context!!)
                .collector(ChuckerCollector(context!!))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(networkInterceptor)
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5, TimeUnit.MINUTES)
//        .authenticator(TokenAuthenticator())
        .build()

//            .addInterceptor(ChuckerInterceptor(context!!))

/**
 * Use the [provideBaseRetrofit] to provide a Retrofit with WITH_BASE_URL instance
 * @return Retrofit*/
fun provideBaseRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Urls.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(providerGSON()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

fun provideGoogleBaseRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(Urls.G_URL_BASE)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(providerGSON()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

