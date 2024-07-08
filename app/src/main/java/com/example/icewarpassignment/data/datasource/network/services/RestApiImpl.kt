package com.example.icewarpassignment.data.datasource.network.services

import android.app.appsearch.GetSchemaResponse
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.icewarpassignment.data.datasource.exceptions.ExceptionFactory
import com.example.icewarpassignment.data.datasource.exceptions.NetworkUnavailableException
import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.data.datasource.request.LoginRequest
import com.example.icewarpassignment.data.datasource.response.*
import com.go2future.tuvoclient.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import retrofit2.Response

class RestApiImpl constructor(
    var mNetworkService: NetworkService,
    var mContext: Context
) : RestApi {
    var context: Context

    val type = object : TypeToken<ErrorResponse>() {}.type
    val gson = Gson()

    init {
        this.context = mContext
    }

    override fun doLoginAPI(
        email: String,
        password: String
    ): Observable<Response<LoginResponse>> {
        return Observable.create<Response<LoginResponse>> { emitter ->

            if (!isThereInternetConnection()) {
                emitter.onError(NetworkUnavailableException())
                return@create
            }
            val mLoginRequest = LoginRequest()
            mLoginRequest.username = email
            mLoginRequest.password = password
            val sessionEntity: Response<LoginResponse> =
                mNetworkService.doLogin(email,password).execute()

            if (sessionEntity.isSuccessful) {
                if (sessionEntity.body() != null) {
                    emitter.onNext(sessionEntity)

                    emitter.onComplete()
                } else {
                    emitter.onError(UnknownError())
                }
            } else {
                val error = sessionEntity.errorBody()
                Log.e("Error", "" + error)
                val errorResponse: ErrorResponse? =
                    gson.fromJson(sessionEntity.errorBody()!!.charStream(), type)
                emitter.onError(
                    ExceptionFactory.create(
                        sessionEntity.code(),
                        errorResponse?.message
                    )

                )
            }
        }
    }

    override fun getChannel(
        token: String,
        includeUnreadCount: Boolean,
        excludeMembers: Boolean,
        includePermissions: Boolean
    ): Observable<Response<ChannelListResponse>> {
        return Observable.create<Response<ChannelListResponse>> { emitter ->

            if (!isThereInternetConnection()) {
                emitter.onError(NetworkUnavailableException())
                return@create
            }
            val mGetChannelRequest = GetChannelRequest()
                mGetChannelRequest.token = token
                mGetChannelRequest.excludeMembers = excludeMembers
                mGetChannelRequest.includePermissions = includePermissions
                mGetChannelRequest.includeUnreadCount = includeUnreadCount
            val sessionEntity: Response<ChannelListResponse> =
                mNetworkService.getListOfChannel(
                    token, includeUnreadCount, excludeMembers, includePermissions).execute()

            if (sessionEntity.isSuccessful) {
                if (sessionEntity.body() != null) {
                    emitter.onNext(sessionEntity)

                    emitter.onComplete()
                } else {
                    emitter.onError(UnknownError())
                }
            } else {
                val error = sessionEntity.errorBody()
                Log.e("Error", "" + error)
                val errorResponse: ErrorResponse? =
                    gson.fromJson(sessionEntity.errorBody()!!.charStream(), type)
                emitter.onError(
                    ExceptionFactory.create(
                        sessionEntity.code(),
                        errorResponse?.message
                    )

                )
            }
        }

    }

    private fun isThereInternetConnection(): Boolean {
        val cm =
            this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }
}