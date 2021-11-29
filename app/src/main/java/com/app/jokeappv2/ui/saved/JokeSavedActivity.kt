package com.app.jokeappv2.ui.saved

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.jokeappv2.base.BaseActivity
import com.app.jokeappv2.db.JokeRepository
import com.app.jokeappv2.db.JokeSaveModel
import com.app.jokeappv2.ui.groupieitem.JokeSavedListItem
import com.example.jokeappv2.databinding.ActivityJokeSavedBinding
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
class JokeSavedActivity : BaseActivity(), JokeSavedMvpView, JokeSavedListItem.UpdateListCallback {
    lateinit var presenter: JokeSavedPresenter
    private lateinit var binding: ActivityJokeSavedBinding
    private var mSection: Section? = null
    private var mAdapter: GroupieAdapter? = null
    private lateinit var jokeRepository: JokeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = JokeSavedPresenter()
        presenter.attachView(this)
        jokeRepository = JokeRepository(this)

        setupRecyclerView()
        getSavedJokes()

    }

    private fun setupRecyclerView() {
        mAdapter = GroupieAdapter()
        mSection = Section()

        mAdapter?.add(mSection!!)
        binding.apply {
            rvSavedJokeList.apply {
                val mLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                layoutManager = mLayoutManager
                adapter = mAdapter
            }
        }
    }

    private fun getSavedJokes() {
        mSection?.clear()
        val jokeListItems = arrayListOf<JokeSaveModel>()
        jokeListItems.addAll(jokeRepository.getAllSavedJokes())

        for (savedJokes in jokeListItems) {
            Log.d("test", savedJokes.id.toString())
            mSection?.add(JokeSavedListItem(savedJokes, this))
        }
    }

    override fun onListUpdated() {
        getSavedJokes()
    }

}