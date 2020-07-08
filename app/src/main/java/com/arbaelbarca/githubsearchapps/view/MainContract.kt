package com.arbaelbarca.githubsearchapps.view

import com.arbaelbarca.githubsearchapps.model.modeluserlist.ItemsItem

import java.util.ArrayList

interface MainContract {
    interface presenter {
        fun onDestroy()

        fun requestFromDataServer()

        fun refreshData(textSearch: String)
    }

    interface MainView {

        fun showProgress()

        fun hideProgress()

        fun erro403()

        fun error422()

        fun setDataToRecyclerView(modelListUsers: ArrayList<ItemsItem>)

        fun onResponseFailure(throwable: Throwable)

    }

    interface GetNoticeIntractor {

        interface OnFinishedListener {
            fun onFinished(modelListUsers: ArrayList<ItemsItem>)

            fun onFailure(t: Throwable)
        }

        fun getNoticeArrayList(onFinishedListener: OnFinishedListener, textSearch: String)

    }
}
