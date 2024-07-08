package com.example.icewarpassignment.data.datasource.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ChannelListResponse:Serializable {

    @SerializedName("ok")
    @Expose
    var ok: Boolean? = null

    @SerializedName("channels")
    @Expose
    var data: List<Channel>? = null
}

class Channel {


    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("created")
    @Expose
    var created: Int? = null

    @SerializedName("creator")
    @Expose
    var creator: String? = null

    @SerializedName("is_archived")
    @Expose
    var isArchived: Boolean? = null

    @SerializedName("is_member")
    @Expose
    var isMember: Boolean? = null

    @SerializedName("group_email")
    @Expose
    var groupEmail: String? = null

    @SerializedName("group_folder_name")
    @Expose
    var groupFolderName: String? = null

    @SerializedName("is_active")
    @Expose
    var isActive: Boolean? = null

    @SerializedName("is_auto_followed")
    @Expose
    var isAutoFollowed: Boolean? = null

    @SerializedName("is_notifications")
    @Expose
    var isNotifications: Boolean? = null

    @SerializedName("last_seen")
    @Expose
    var lastSeen: String? = null

    @SerializedName("latest")
    @Expose
    var latest: Int? = null

    @SerializedName("unread_count")
    @Expose
    var unreadCount: Int? = null

    @SerializedName("thread_unread_count")
    @Expose
    var threadUnreadCount: Int? = null

    @SerializedName("members")
    @Expose
    var members: List<Any>? = null

    @SerializedName("permissions")
    @Expose
    var permissions: Permissions? = null

}

class Permissions {
    @SerializedName("items_read")
    @Expose
    var itemsRead: Boolean? = null

    @SerializedName("items_write")
    @Expose
    var itemsWrite: Boolean? = null

    @SerializedName("items_modify")
    @Expose
    var itemsModify: Boolean? = null

    @SerializedName("items_delete")
    @Expose
    var itemsDelete: Boolean? = null

    @SerializedName("items_edit_documents")
    @Expose
    var itemsEditDocuments: Boolean? = null

    @SerializedName("folder_read")
    @Expose
    var folderRead: Boolean? = null

    @SerializedName("folder_write")
    @Expose
    var folderWrite: Boolean? = null

    @SerializedName("folder_rename")
    @Expose
    var folderRename: Boolean? = null

    @SerializedName("folder_delete")
    @Expose
    var folderDelete: Boolean? = null

    @SerializedName("administration_invite")
    @Expose
    var administrationInvite: Boolean? = null

    @SerializedName("administration_kick")
    @Expose
    var administrationKick: Boolean? = null

    @SerializedName("administration_administer")
    @Expose
    var administrationAdminister: Boolean? = null

}
