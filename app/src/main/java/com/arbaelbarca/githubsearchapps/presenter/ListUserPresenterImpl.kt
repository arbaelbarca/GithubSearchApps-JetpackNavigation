package com.arbaelbarca.githubsearchapps.presenter

import com.arbaelbarca.githubsearchapps.model.modeluserlist.ModelListUser
import com.arbaelbarca.githubsearchapps.network.NetworkApi
import com.arbaelbarca.githubsearchapps.view.MainContract

import retrofit2.Response
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListUserPresenterImpl(private val mainView: MainContract.MainView) : MainContract.GetNoticeIntractor {

    override fun getNoticeArrayList(onFinishedListener: MainContract.GetNoticeIntractor.OnFinishedListener, textSearch: String) {
        NetworkApi
                .instance
                .api
                .getListUser(textSearch, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Response<ModelListUser>>() {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(modelListUserResponse: Response<ModelListUser>) {
                        if (modelListUserResponse.body() != null && modelListUserResponse.body()!!.items!!.size > 0)
                            onFinishedListener.onFinished(modelListUserResponse.body()!!.items!!)
                        else
                            mainView.error422()

                        if (modelListUserResponse.code() == 403) {
                            mainView.erro403()
                            mainView.hideProgress()
                        } else if (modelListUserResponse.code() == 422) {
                            mainView.error422()
                            mainView.hideProgress()
                        }
                    }
                })

    }
}
