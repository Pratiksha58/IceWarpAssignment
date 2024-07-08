package com.example.icewarpassignment.data.datasource.request

class GetChannelRequest {
    var token: String = ""
    var includeUnreadCount:Boolean = true
    var excludeMembers:Boolean = true
    var includePermissions:Boolean = false
}