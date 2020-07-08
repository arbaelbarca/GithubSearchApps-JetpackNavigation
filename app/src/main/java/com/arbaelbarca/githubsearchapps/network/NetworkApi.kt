package com.arbaelbarca.githubsearchapps.network

import com.arbaelbarca.githubsearchapps.BuildConfig

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkApi : Interceptor {
    lateinit var api: ApiServices
    private var credentials: String = ""

    private constructor() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_GITHUB)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        api = retrofit.create(ApiServices::class.java)

    }


    private constructor(user: String, password: String) {
        this.credentials = Credentials.basic(user, password)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }

    companion object {
        private var networkApi: NetworkApi? = null


        private fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(NetworkApi(BuildConfig.OAUTH_USERNAME, BuildConfig.OAUTH_PASSWORD))
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()
        }

        private val loggingInterceptor: HttpLoggingInterceptor
            get() {
                val interceptor = HttpLoggingInterceptor()
                return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            }

        val instance: NetworkApi
            get() {
                if (networkApi == null)
                    networkApi = NetworkApi()
                return networkApi as NetworkApi
            }
    }
}
