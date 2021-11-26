package com.app.jokeappv2.ui.saved

import android.content.Context
import android.os.Bundle
import com.app.jokeappv2.base.BaseActivity
import com.app.jokeappv2.db.JokeRepository
import com.app.jokeappv2.db.JokeSaveDb
import com.app.jokeappv2.db.JokeSaveModel
import com.example.jokeappv2.databinding.ActivityJokeSavedBinding
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
class JokeSavedActivity : BaseActivity(), JokeSavedMvpView {
    lateinit var presenter: JokeSavedPresenter
    private lateinit var binding: ActivityJokeSavedBinding
    private var mSection: Section? = null
    private var mAdapter: GroupieAdapter? = null
    private lateinit var jokeSaveModel: JokeSaveModel
    private lateinit var jokeRepository: JokeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = JokeSavedPresenter()
        presenter.attachView(this)
        binding.apply {
            
        }

    }

}