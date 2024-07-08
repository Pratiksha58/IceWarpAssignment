package com.example.icewarpassignment.data.datasource.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LoginResponse:Serializable {
    @SerializedName("authorized")
    @Expose
    var authorized: Boolean? = null

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("host")
    @Expose
    var host: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("ok")
    @Expose
    var ok: Boolean? = null
}