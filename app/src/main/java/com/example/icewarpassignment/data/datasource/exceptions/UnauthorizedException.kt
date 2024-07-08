package com.example.icewarpassignment.data.datasource.exceptions


class UnauthorizedException(error: String?) : Exception(error){
    override val message: String
        get() = "Session expired. Please login again"
}