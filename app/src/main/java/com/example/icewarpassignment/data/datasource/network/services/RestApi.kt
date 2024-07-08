package com.example.icewarpassignment.data.datasource.network.services

import com.example.icewarpassignment.data.datasource.response.ChannelListResponse
import com.example.icewarpassignment.data.datasource.response.LoginResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Field

interface RestApi {
    fun doLoginAPI(
        username: String,
        password:String
    ): Observable<Response<LoginResponse>>

    fun getChannel(
        token:String,
        includeUnreadCount:Boolean,
        excludeMembers:Boolean,
        includePermissions:Boolean
    ): Observable<Response<ChannelListResponse>>
}