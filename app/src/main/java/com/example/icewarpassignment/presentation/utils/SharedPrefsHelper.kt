package com.example.icewarpassignment.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.icewarpassignment.presentation.view.base.UserApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

var KEY_AUTH_TOKEN = "keyAuthToken"

object SharedPrefsHelper {

    val preferencesModule = module {
        single(named("settingsPrefs")) { sharedPreferences }
    }

    private var sharedPreferences: SharedPreferences =
        UserApplication.getInstance().getSharedPreferences("", Context.MODE_PRIVATE)

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
    fun setAuthorizationToken(id: String): Boolean {
        return sharedPreferences.edit().putString(KEY_AUTH_TOKEN, id).commit()
    }

    fun getAuthorizationToken(defaultValue: String): String? {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, defaultValue)
    }

}