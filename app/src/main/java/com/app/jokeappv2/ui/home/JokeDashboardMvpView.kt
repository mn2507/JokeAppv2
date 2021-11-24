package com.app.jokeappv2.ui.home

import com.app.jokeappv2.base.MvpView
import com.app.jokeappv2.data.response.Jokes

/**
 * Created by Muthu Narayanan on 23/11/2021.
 */
interface JokeDashboardMvpView : MvpView {
    fun onReceiveMultiAnyJoke(jokesList: List<Jokes>)
}