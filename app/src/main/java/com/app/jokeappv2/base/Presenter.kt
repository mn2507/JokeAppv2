package com.app.jokeappv2.base

/**
 * Created by Muthu Narayanan on 23/11/2021.
 */

interface Presenter<V : MvpView> {
    fun attachView(mvpView: V)
    fun detachView()
    fun onStop()
    fun onResume()
}
