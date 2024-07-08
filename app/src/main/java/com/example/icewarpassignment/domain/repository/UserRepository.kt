package com.example.icewarpassignment.domain.repository

import androidx.annotation.WorkerThread
import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.domain.entity.*
import com.example.icewarpassignment.presentation.view.base.UserApplication
import io.reactivex.Observable
import okhttp3.MultipartBody

interface UserRepository {
    fun doSignIn(
        username:String,
        password:String
    ): Observable<LoginEntity>

    fun getChannelList(token:String,
                       includeUnreadCount:Boolean,
                       excludeMembers:Boolean,
                       includePermissions:Boolean):Observable<ChannelListEntity>


//    @WorkerThread
//    suspend fun insert(mLanguageList :List<LanguageDataE>?){
//        val languageDao = UserApplication.database?.languageDao()
//        languageDao?.insert(mLanguageList)
//    }

    /* @WorkerThread
        suspend fun getLanguageData(labelKey:String): List<LanguageDataE>? {
            val languageDao = UserApplication.database?.languageDao()
           return languageDao?.getLanguageDataByKey(labelKey)
        }

    @WorkerThread
    suspend fun getLanguageDataByKeys(labelKeys: Array<String>): List<LanguageDataE>? {
        val languageDao = UserApplication.database?.languageDao()
        return languageDao?.getLanguageDataByKeys(labelKeys)
    }*/
}