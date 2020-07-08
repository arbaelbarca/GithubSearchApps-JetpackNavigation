package com.arbaelbarca.githubsearchapps.model.modeluserlist

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

class ItemsItem : Parcelable {

    @SerializedName("gists_url")
    var gistsUrl: String? = null

    @SerializedName("repos_url")
    var reposUrl: String? = null

    @SerializedName("following_url")
    var followingUrl: String? = null

    @SerializedName("starred_url")
    var starredUrl: String? = null

    @SerializedName("login")
    var login: String? = null

    @SerializedName("followers_url")
    var followersUrl: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null

    @SerializedName("score")
    var score: Double = 0.toDouble()

    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("events_url")
    var eventsUrl: String? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null

    @SerializedName("site_admin")
    var isSiteAdmin: Boolean = false

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("gravatar_id")
    var gravatarId: String? = null

    @SerializedName("node_id")
    var nodeId: String? = null

    @SerializedName("organizations_url")
    var organizationsUrl: String? = null

    constructor() {

    }

    private constructor(`in`: Parcel) {
        gistsUrl = `in`.readString()
        reposUrl = `in`.readString()
        followingUrl = `in`.readString()
        starredUrl = `in`.readString()
        login = `in`.readString()
        followersUrl = `in`.readString()
        type = `in`.readString()
        url = `in`.readString()
        subscriptionsUrl = `in`.readString()
        score = `in`.readDouble()
        receivedEventsUrl = `in`.readString()
        avatarUrl = `in`.readString()
        eventsUrl = `in`.readString()
        htmlUrl = `in`.readString()
        isSiteAdmin = `in`.readByte().toInt() != 0
        id = `in`.readInt()
        gravatarId = `in`.readString()
        nodeId = `in`.readString()
        organizationsUrl = `in`.readString()
    }

    override fun toString(): String {
        return "ItemsItem{" +
                "gists_url = '" + gistsUrl + '\''.toString() +
                ",repos_url = '" + reposUrl + '\''.toString() +
                ",following_url = '" + followingUrl + '\''.toString() +
                ",starred_url = '" + starredUrl + '\''.toString() +
                ",login = '" + login + '\''.toString() +
                ",followers_url = '" + followersUrl + '\''.toString() +
                ",type = '" + type + '\''.toString() +
                ",url = '" + url + '\''.toString() +
                ",subscriptions_url = '" + subscriptionsUrl + '\''.toString() +
                ",score = '" + score + '\''.toString() +
                ",received_events_url = '" + receivedEventsUrl + '\''.toString() +
                ",avatar_url = '" + avatarUrl + '\''.toString() +
                ",events_url = '" + eventsUrl + '\''.toString() +
                ",html_url = '" + htmlUrl + '\''.toString() +
                ",site_admin = '" + isSiteAdmin + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",gravatar_id = '" + gravatarId + '\''.toString() +
                ",node_id = '" + nodeId + '\''.toString() +
                ",organizations_url = '" + organizationsUrl + '\''.toString() +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(gistsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(starredUrl)
        parcel.writeString(login)
        parcel.writeString(followersUrl)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(subscriptionsUrl)
        parcel.writeDouble(score)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(avatarUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(htmlUrl)
        parcel.writeByte((if (isSiteAdmin) 1 else 0).toByte())
        parcel.writeInt(id)
        parcel.writeString(gravatarId)
        parcel.writeString(nodeId)
        parcel.writeString(organizationsUrl)
    }

    companion object CREATOR : Parcelable.Creator<ItemsItem> {
        override fun createFromParcel(parcel: Parcel): ItemsItem {
            return ItemsItem(parcel)
        }

        override fun newArray(size: Int): Array<ItemsItem?> {
            return arrayOfNulls(size)
        }
    }


}
