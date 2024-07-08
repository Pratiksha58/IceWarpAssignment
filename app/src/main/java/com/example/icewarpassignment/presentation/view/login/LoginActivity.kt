package com.example.icewarpassignment.presentation.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.Observer
import com.example.icewarpassignment.R
import com.example.icewarpassignment.data.datasource.request.LoginRequest
import com.example.icewarpassignment.presentation.enums.Status
import com.example.icewarpassignment.presentation.utils.Navigator
import com.example.icewarpassignment.presentation.view.base.BaseActivity
import com.example.icewarpassignment.presentation.view.base.UserApplication
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private lateinit var mLoginRequest: LoginRequest
    private val mLoginViewModel: LoginViewModel by viewModel()

    companion object {
        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }


    override fun init() {
        btn_login.setOnClickListener {
                if (isValidate())
                    callLoginApi()
        }
    }

    private fun isValidate(): Boolean {
        if (et_username.text.toString().isEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(et_username.text.toString()).matches()){
            showToastMessage("Please enter user name as valid email")
            return false
        }
        if (et_password.text.toString().isEmpty() && et_password.text.toString().length >= 6) {
            showToastMessage("Please Enter Valid Password")
            return false
        }
        if (et_host.text.toString().isEmpty()) {
            showToastMessage("Please enter host")
            return false
        }
        return true
    }
    private fun callLoginApi() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        loginRequest(username, password)
    }

    private fun loginRequest(username: String, password: String) {
        mLoginRequest = LoginRequest()
        mLoginRequest.username = username
        mLoginRequest.password = password
        mLoginViewModel.onLoginClicked(mLoginRequest)

        observeLoginResponse()

    }
    private fun observeLoginResponse() {
        mLoginViewModel.loginResponse().observe(this, Observer { loginResponse ->
            when (loginResponse.status) {
                Status.SUCCESS -> {
                    val token = loginResponse.mLoginEntity?.token
                    val email = loginResponse.mLoginEntity?.email
                    if (token != null && email != null) {
                        runOnUiThread {
                            UserApplication.database?.iceWarpDBQueries?.insertUser(1,token, email)
                        }
                        Navigator.navigateToChannelListActivity(this)

                    }
                }
                Status.ERROR -> {
                    //error
                    Log.e("Error", loginResponse.error?.message.toString())
                }

                else -> {}
            }
        })
    }
}