package com.example.icewarpassignment.data.datasource.network.services

import com.example.icewarpassignment.data.datasource.request.LoginRequest
import com.example.icewarpassignment.data.datasource.response.ChannelListResponse
import com.example.icewarpassignment.data.datasource.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

//https://mofa.onice.io/teamchatapi/iwauthentication.login.plain
interface NetworkService {
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("teamchatapi/iwauthentication.login.plain")
    fun doLogin(@Field("username") username:String,@Field("password") password:String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("teamchatapi/channels.list")
    fun getListOfChannel(@Field("token") token:String,
                         @Field("include_unread_count") includeUnreadCount:Boolean,
                         @Field("exclude_members") excludeMembers:Boolean,
                         @Field("include_unread_count") includePermissions:Boolean)
        : Call<ChannelListResponse>

}