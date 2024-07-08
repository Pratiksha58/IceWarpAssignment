package com.example.icewarpassignment.data.repository

import com.example.icewarpassignment.data.datasource.mapper.UserDataMapper
import com.example.icewarpassignment.data.datasource.network.services.RestApi
import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.domain.entity.ChannelListEntity
import com.example.icewarpassignment.domain.entity.LoginEntity
import com.example.icewarpassignment.domain.repository.UserRepository
import io.reactivex.Observable

class UserDataRepository constructor(
    var mRestApi: RestApi,
    var mUserDataMapper: UserDataMapper
) : UserRepository {
    override fun doSignIn(
        username: String,
        password: String
    ): Observable<LoginEntity> {
        return mRestApi.doLoginAPI(username, password)
            .map(mUserDataMapper::mapLogin)
    }

    override fun getChannelList(
        token: String,
        includeUnreadCount: Boolean,
        excludeMembers: Boolean,
        includePermissions: Boolean
    ): Observable<ChannelListEntity> {
        return mRestApi.getChannel(
            token, includeUnreadCount, excludeMembers, includePermissions).map(mUserDataMapper::mapChannelList)
    }


}
