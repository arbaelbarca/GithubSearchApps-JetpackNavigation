package com.arbaelbarca.githubsearchapps.model.modeluserlist

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class ModelListUser {

    @SerializedName("total_count")
    var totalCount: Int = 0

    @SerializedName("incomplete_results")
    var isIncompleteResults: Boolean = false

    @SerializedName("items")
    var items: ArrayList<ItemsItem>? = null

    override fun toString(): String {
        return "ModelListUser{" +
                "total_count = '" + totalCount + '\''.toString() +
                ",incomplete_results = '" + isIncompleteResults + '\''.toString() +
                ",items = '" + items + '\''.toString() +
                "}"
    }
}
