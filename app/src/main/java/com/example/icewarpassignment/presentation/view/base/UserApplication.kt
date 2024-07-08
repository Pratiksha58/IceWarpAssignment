package com.example.icewarpassignment.presentation.view.base

import android.app.Application
import com.example.icewarpassignment.Database
import com.example.icewarpassignment.presentation.di.PostModule
import com.example.icewarpassignment.presentation.di.networkModule
import com.facebook.stetho.Stetho
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class UserApplication : Application(){

    companion object {
        private lateinit var instance: UserApplication
        var database: Database ? = null
        fun getInstance(): UserApplication = instance

    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        instance = this
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@UserApplication)
            modules(
                listOf(
                    PostModule,
                    networkModule
                )
            )
        }
        database = Database(AndroidSqliteDriver(Database.Schema, this@UserApplication,
            "Database.db"))

    }

}