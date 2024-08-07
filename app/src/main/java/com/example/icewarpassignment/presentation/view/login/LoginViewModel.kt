package com.example.icewarpassignment.presentation.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.icewarpassignment.data.datasource.request.LoginRequest
import com.example.icewarpassignment.domain.entity.LoginEntity
import com.example.icewarpassignment.domain.interactor.LoginUC
import io.reactivex.observers.DisposableObserver

class LoginViewModel(var loginUC: LoginUC) : ViewModel() {

    var mMutableLiveDataLoginModel = MutableLiveData<LoginModel>()

    fun loginResponse(): LiveData<LoginModel> {
        return mMutableLiveDataLoginModel
    }

    fun onLoginClicked(mLoginRequest: LoginRequest) {

        loginUC.execute(object : DisposableObserver<LoginEntity>() {

            override fun onNext(response: LoginEntity) {
                mMutableLiveDataLoginModel.value = LoginModel.success(response)
            }


            override fun onComplete() {
                Log.d("TAG--> ", "onComplete")

            }

            override fun onError(error: Throwable) {
                Log.d("TAG--> ", "onError" + error.message)
                mMutableLiveDataLoginModel.value = LoginModel.error(error)

            }

        }, mLoginRequest)
    }
}