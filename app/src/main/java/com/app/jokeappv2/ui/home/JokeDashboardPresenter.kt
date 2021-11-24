package com.app.jokeappv2.ui.home

import android.util.Log
import com.app.jokeappv2.base.BasePresenter
import com.app.jokeappv2.data.response.JokeResponseBody
import com.app.jokeappv2.data.response.JokesResponseBody
import com.app.jokeappv2.network.ApiInterface
import com.app.jokeappv2.network.RetrofitModule
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Muthu Narayanan on 23/11/2021.
 */
class JokeDashboardPresenter : BasePresenter<JokeDashboardMvpView>() {

    private var mApiInterface: ApiInterface
    private var retrofitModule = RetrofitModule()

    init {
        mApiInterface = retrofitModule.getApiInterface()
    }

    fun getMultiAnyJoke(jokeCategory: List<String>, jokeAmount: Int, jokeType: String) {
        getMvpView()?.showProgressDialog()

       var convertedJokeCategory = ""
        jokeCategory.forEachIndexed { index, s ->
            if (index == jokeCategory.size - 1) {
                convertedJokeCategory += s
            } else {
                convertedJokeCategory = "$convertedJokeCategory$s,"
            }
        }

        mApiInterface.multiAnyJoke(convertedJokeCategory, jokeAmount, jokeType)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<JokesResponseBody>() {
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {
                    getMvpView()?.hideProgressDialog()
                }

                override fun onNext(responseBody: JokesResponseBody?) {
                    getMvpView()?.hideProgressDialog()
                    Log.d("test", responseBody.toString())
                    if (responseBody?.jokes != null) {
                        getMvpView()?.onReceiveMultiAnyJoke(responseBody.jokes)
                    }
                }
            })
    }

    fun getSingleAnyJoke() {
        mApiInterface.singleAnyJoke()!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<JokeResponseBody>() {
                override fun onCompleted() {
                }

                override fun onError(e: Throwable?) {

                }

                override fun onNext(responseBody: JokeResponseBody?) {
                    Log.d("test", responseBody.toString())
                }

            })
    }
}