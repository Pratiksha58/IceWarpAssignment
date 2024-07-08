package com.example.icewarpassignment.data.datasource.exceptions

class ExceptionFactory {

    companion object {

        fun create(code: Int, message: String?): Exception {
            when (code) {
                401 -> {
                    return UnauthorizedException(message)
                }
                500 -> {
                    return ServerException(message)
                }
                else -> return ServerException(message)
            }
        }
    }
}
