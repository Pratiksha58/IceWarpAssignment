package com.example.icewarpassignment.domain.interactor

import com.example.icewarpassignment.data.datasource.request.LoginRequest
import com.example.icewarpassignment.domain.entity.LoginEntity
import com.example.icewarpassignment.domain.repository.UserRepository
import io.reactivex.Observable

class LoginUC constructor(val userRepository: UserRepository) :
    UseCase<LoginEntity, LoginRequest>() {

    override fun build(param: LoginRequest): Observable<LoginEntity> {
        return userRepository.doSignIn(param.username, param.password)
    }
}