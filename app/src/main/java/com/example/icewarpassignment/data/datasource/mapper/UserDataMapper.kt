package com.example.icewarpassignment.data.datasource.mapper

import com.example.icewarpassignment.data.datasource.response.*
import com.example.icewarpassignment.domain.entity.*
import retrofit2.Response
import java.util.ArrayList

class UserDataMapper {

    fun mapLogin(loginResponse: Response<LoginResponse>): LoginEntity {
        val mloginResponse: LoginResponse? = loginResponse.body()

        val loginEntity = LoginEntity()

        loginEntity.ok = mloginResponse?.ok
        loginEntity.host = mloginResponse?.host
        loginEntity.token = mloginResponse?.token
        loginEntity.email = mloginResponse?.email
        loginEntity.authorized = mloginResponse?.authorized

        return loginEntity
    }

    fun mapChannelList(channelListResponse: Response<ChannelListResponse>):ChannelListEntity{
        val mChannelListResponse: ChannelListResponse? = channelListResponse.body()
        val channelListEntity = ChannelListEntity()

        val mChannel = mChannelListResponse?.data
        val mChannelEntity = ArrayList<ChannelE>()

        for (i in 0..mChannel?.size!! - 1) {
            val mChannelData = ChannelE()
            mChannelData.id = mChannel.get(i).id
            mChannelData.groupFolderName = mChannel.get(i).groupFolderName
            mChannelData.name = mChannel.get(i).name
            mChannelData.groupEmail = mChannel.get(i).groupEmail

            mChannelEntity.add(mChannelData)
        }
        channelListEntity.data = mChannelEntity
        channelListEntity.ok = mChannelListResponse.ok
        return channelListEntity
    }
}