package com.example.icewarpassignment.presentation.di

import com.example.icewarpassignment.data.datasource.mapper.UserDataMapper
import com.example.icewarpassignment.presentation.utils.Navigator
import com.example.icewarpassignment.presentation.view.channelList.ChannelListViewModel
import com.example.icewarpassignment.presentation.view.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val PostModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ChannelListViewModel(get()) }


    factory { UserDataMapper() }

    single { Navigator }
}