package com.example.icewarpassignment.presentation.view.channelList

import com.example.icewarpassignment.domain.entity.ChannelListEntity
import com.example.icewarpassignment.domain.entity.LoginEntity
import com.example.icewarpassignment.presentation.enums.Status
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelListModel(status: Status, var mChannelListEntity: ChannelListEntity?, var error: Throwable?) {
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("Token")
    @Expose
    var token: String? = null

    var status: Status? = status


    companion object {

        fun success(response: ChannelListEntity): ChannelListModel {
            return ChannelListModel(
                Status.SUCCESS,
                response,
                null
            )
        }

        fun error(error: Throwable): ChannelListModel {
            return ChannelListModel(
                Status.ERROR,
                null,
                error
            )
        }
    }
}