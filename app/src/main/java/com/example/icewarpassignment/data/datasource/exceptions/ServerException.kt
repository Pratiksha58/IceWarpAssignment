package com.example.icewarpassignment.data.datasource.exceptions


class ServerException(error: String?) : Exception(error) {
    override val message: String?
        get() = "Something went wrong. Please try again later."
}