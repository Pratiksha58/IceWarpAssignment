package com.example.icewarpassignment.presentation.view.channelList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.domain.entity.ChannelListEntity
import com.example.icewarpassignment.domain.interactor.GetChannelUC
import io.reactivex.observers.DisposableObserver

class ChannelListViewModel(var getChannelUC: GetChannelUC) : ViewModel() {

    var mMutableLiveDataChannelListModel = MutableLiveData<ChannelListModel>()

    fun channelListResponse(): LiveData<ChannelListModel> {
        return mMutableLiveDataChannelListModel
    }

    fun getChannelList(mGetChannelRequest: GetChannelRequest) {

        getChannelUC.execute(object : DisposableObserver<ChannelListEntity>() {

            override fun onNext(response: ChannelListEntity) {
                mMutableLiveDataChannelListModel.value = ChannelListModel.success(response)
            }


            override fun onComplete() {
                Log.d("TAG--> ", "onComplete")

            }

            override fun onError(error: Throwable) {
                Log.d("TAG--> ", "onError" + error.message)
                mMutableLiveDataChannelListModel.value = ChannelListModel.error(error)

            }

        }, mGetChannelRequest)
    }
}