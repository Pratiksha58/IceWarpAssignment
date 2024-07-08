package com.example.icewarpassignment.presentation.view.channelList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.lifecycle.Observer
import com.example.icewarpassignment.R
import com.example.icewarpassignment.data.datasource.request.GetChannelRequest
import com.example.icewarpassignment.domain.entity.ChannelE
import com.example.icewarpassignment.presentation.enums.Status
import com.example.icewarpassignment.presentation.view.base.BaseActivity
import com.example.icewarpassignment.presentation.view.base.UserApplication
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChannelListActivity : BaseActivity() {

    val mChannelListViewModel: ChannelListViewModel by viewModel()

    var mLists: ArrayList<ChannelE> = ArrayList()
    var mListsGroup: ArrayList<String> = ArrayList()
    var mSubLists1: ArrayList<String> = ArrayList()
    var mSubLists2: ArrayList<String> = ArrayList()
    var mSubLists3: ArrayList<String> = ArrayList()

    private var expandableListView: ExpandableListView? = null
    private var adapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null
    var data : String = ""

    companion object {
        fun getCallingIntent(context: Context): Intent {
            val intent = Intent(context, ChannelListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_list)

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        UserApplication.database?.iceWarpDBQueries?.deleteUser()
        finish()
        return true
    }
    override fun init() {
        runOnUiThread {
            data  =  UserApplication.database?.iceWarpDBQueries?.getData()?.executeAsOne().toString()
            Log.d("Success", data)
        }
        if (data.isNotEmpty()){
            callChannelListApi(data)
        }
    }

    private fun callChannelListApi(data: String) {
        val mGetChannelRequest = GetChannelRequest()

        mGetChannelRequest.token = data
        mGetChannelRequest.includeUnreadCount = true
        mGetChannelRequest.excludeMembers = true
        mGetChannelRequest.includePermissions = false
        mChannelListViewModel.getChannelList(mGetChannelRequest)
        observeChannelListResponse()
    }

    private fun observeChannelListResponse() {
        mChannelListViewModel.channelListResponse().observe(this, Observer { channelListResponse ->
            when (channelListResponse.status) {
                Status.SUCCESS -> {
                    mLists =
                        channelListResponse.mChannelListEntity!!.data!! as ArrayList<ChannelE>
                    createLists(mLists)
                }
                Status.ERROR -> {
                    //error
                    Log.e("Error", channelListResponse.error?.message.toString())
                }
                else -> {}
            }
        })

    }

    private fun createLists(mLists: ArrayList<ChannelE>) {
        mListsGroup.clear()
        for (i in 0 until mLists.size as Int) {
            for (j in i + 1 until mLists.size as Int) {
                if (!(mLists.get(i).groupFolderName.equals(mLists.get(j).groupFolderName))){
                    mSubLists1.add(mLists.get(i).groupFolderName.toString())
                } else{
                    if (mLists.get(j).groupFolderName !in mListsGroup){
                        mListsGroup.add(mLists.get(j).groupFolderName.toString())
                    }
                }
            }
        }
        createSubList(mListsGroup)
    }

    private fun createSubList(mListsGroup: ArrayList<String>) {
        mSubLists1.clear()
        for (i in 0 until mLists.size as Int) {
            if (mListsGroup.get(0).equals(mLists.get(i).groupFolderName)){
                mSubLists1.add(mLists.get(i).name.toString())
            } else if (mListsGroup.get(1).equals(mLists.get(i).groupFolderName)){
                mSubLists2.add(mLists.get(i).name.toString())
            } else{
                mSubLists3.add(mLists.get(i).name.toString())
            }
        }
        getExpandableList()
    }



    private fun getExpandableList(){
        val expandableListDetail = HashMap<String, List<String>>()
        expandableListDetail[mListsGroup.get(0)] = mSubLists1
        expandableListDetail[mListsGroup.get(1)] = mSubLists2
        expandableListDetail[mListsGroup.get(2)] = mSubLists3

        expandableListView = findViewById(R.id.expendableList)
          if (expandableListView != null) {
              val listData = expandableListDetail
              titleList = ArrayList(listData.keys)
              adapter = CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
              expandableListView!!.setAdapter(adapter)
          }
     }

}