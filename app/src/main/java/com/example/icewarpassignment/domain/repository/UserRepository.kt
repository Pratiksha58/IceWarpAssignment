package com.example.icewarpassignment.domain.repository

import com.example.icewarpassignment.domain.entity.ChannelListEntity
import com.example.icewarpassignment.domain.entity.LoginEntity
import io.reactivex.Observable

interface UserRepository {
    fun doSignIn(
        username:String,
        password:String
    ): Observable<LoginEntity>

    fun getChannelList(token:String,
                       includeUnreadCount:Boolean,
                       excludeMembers:Boolean,
                       includePermissions:Boolean):Observable<ChannelListEntity>
}