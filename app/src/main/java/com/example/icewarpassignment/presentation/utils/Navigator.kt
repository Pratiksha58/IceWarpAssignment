package com.example.icewarpassignment.presentation.utils

import android.content.Context
import com.example.icewarpassignment.presentation.view.channelList.ChannelListActivity
import com.example.icewarpassignment.presentation.view.login.LoginActivity

object Navigator {

    fun navigateToLoginActivity(context: Context) {
        context.startActivity(LoginActivity.getCallingIntent(context))
    }
    fun navigateToChannelListActivity(context: Context) {
        context.startActivity(ChannelListActivity.getCallingIntent(context))
    }
}