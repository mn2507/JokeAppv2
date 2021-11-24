package com.app.jokeappv2.base

import rx.subscriptions.CompositeSubscription


/**
 * Created by Muthu Narayanan on 23/11/2021.
 */
open class BasePresenter<T : MvpView> : Presenter<T> {


    private var mCompositeSubscription: CompositeSubscription = CompositeSubscription()
    private var mMvpView: T? = null

    override fun attachView(mvpView: T) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null
        mCompositeSubscription.unsubscribe()
        mCompositeSubscription.clear()
    }

    fun getMvpView():T?{
        return mMvpView
    }


    override fun onStop() {
    }

    override fun onResume() {
    }

}