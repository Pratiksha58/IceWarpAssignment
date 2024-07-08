package com.example.icewarpassignment.data.datasource.exceptions

class NetworkUnavailableException : Throwable() {

    override val message: String?
        get() = "Please check your network connectivity"
}