package com.example.icewarpassignment.presentation.di

import android.content.Context
import com.example.icewarpassignment.data.datasource.mapper.UserDataMapper
import com.example.icewarpassignment.data.datasource.network.services.NetworkService
import com.example.icewarpassignment.data.datasource.network.services.RestApi
import com.example.icewarpassignment.data.datasource.network.services.RestApiImpl
import com.example.icewarpassignment.domain.repository.UserRepository
import com.example.icewarpassignment.data.repository.UserDataRepository
import com.example.icewarpassignment.domain.interactor.*
import com.example.icewarpassignment.presentation.utils.Constants.Companion.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        createRetrofit(
            createOkHttpClient()
        )
    }
    single { createNetworkApi(get()) }
    single {
        createRestAPI(
            get(),
            get()
        )
    }
    single {
        createUserRepository(
            get(),
            get()
        )
    }
    single { createLoginUseCase(get()) }
    single { createChannelUseCase(get()) }
}


    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
    fun createOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
//                .header("Api-Version", "v1")
                .header("Content-Type","application/x-www-form-urlencoded")
                .build()

            chain.proceed(request)
        }
            .addNetworkInterceptor(StethoInterceptor())
            .retryOnConnectionFailure(true)
            .callTimeout(
                2, TimeUnit.MINUTES
            )
            .connectTimeout(3000, TimeUnit.SECONDS)
            .writeTimeout(4000, TimeUnit.SECONDS)
            .readTimeout(3000, TimeUnit.SECONDS)

        return httpClient.build()
    }
    fun createNetworkApi(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    fun createRestAPI(mNetworkService: NetworkService, mContext: Context): RestApi {
        return RestApiImpl(
            mNetworkService,
            mContext
        )
    }

    fun createUserRepository(mRestApi: RestApi, mapper: UserDataMapper): UserRepository {
        return UserDataRepository(
            mRestApi,
            mapper
        )
    }
    fun createLoginUseCase(mUserRepository: UserRepository): LoginUC {
        return LoginUC(mUserRepository)
    }
    fun createChannelUseCase(mUserRepository: UserRepository): GetChannelUC {
        return GetChannelUC(mUserRepository)
    }
