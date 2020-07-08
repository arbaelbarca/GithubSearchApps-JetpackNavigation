package com.arbaelbarca.githubsearchapps.presenter

import com.arbaelbarca.githubsearchapps.model.modeluserlist.ItemsItem
import com.arbaelbarca.githubsearchapps.view.MainContract

import java.util.ArrayList

class ListUserPresenter(private var mainView: MainContract.MainView?, private val getNoticeIntractor: MainContract.GetNoticeIntractor) : MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    override fun onDestroy() {
        mainView = null
    }

    override fun requestFromDataServer() {
        getNoticeIntractor.getNoticeArrayList(this, "")

    }


    override fun refreshData(textSearch: String) {
        getNoticeIntractor.getNoticeArrayList(this, textSearch)

    }

    override fun onFinished(modelListUsers: ArrayList<ItemsItem>) {
        if (mainView != null) {
            mainView!!.setDataToRecyclerView(modelListUsers)
            mainView!!.hideProgress()
        }


    }

    override fun onFailure(t: Throwable) {
        if (mainView != null)
            mainView!!.onResponseFailure(t)
    }
}
