package com.app.jokeappv2.base

import androidx.appcompat.app.AppCompatActivity
import com.app.jokeappv2.utils.FullScreenProgressDialog

/**
 * Created by Muthu Narayanan on 23/11/2021.
 */
 abstract class BaseActivity : MvpView, AppCompatActivity() {
    override fun showProgressDialog() {
        FullScreenProgressDialog.show(this)
    }

    override fun hideProgressDialog() {
        FullScreenProgressDialog.hideProgressDialog()
    }

}