package com.example.icewarpassignment.presentation.view.login

import com.example.icewarpassignment.domain.entity.LoginEntity
import com.example.icewarpassignment.presentation.enums.Status
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginModel(status: Status, var mLoginEntity: LoginEntity?, var error: Throwable?) {
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

        fun success(response: LoginEntity): LoginModel {
            return LoginModel(
                Status.SUCCESS,
                response,
                null
            )
        }

        fun error(error: Throwable): LoginModel {
            return LoginModel(
                Status.ERROR,
                null,
                error
            )
        }
    }
}