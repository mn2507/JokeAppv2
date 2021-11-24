package com.app.jokeappv2.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.jokeappv2.base.BaseActivity
import com.app.jokeappv2.data.response.Jokes
import com.app.jokeappv2.data.response.JokesResponseBody
import com.app.jokeappv2.ui.groupieitem.JokeListItem
import com.app.jokeappv2.utils.Constant
import com.example.jokeappv2.R
import com.example.jokeappv2.databinding.ActivityJokeDashboardBinding
import com.fasterxml.jackson.databind.ser.Serializers
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import javax.inject.Inject

class JokeDashboardActivity : BaseActivity(), JokeDashboardMvpView {
    lateinit var presenter: JokeDashboardPresenter
    private lateinit var binding: ActivityJokeDashboardBinding
    private var mSection: Section? = null
    private var mAdapter: GroupieAdapter? = null
    private val filteredCategory: MutableList<String> = ArrayList()
    private var isAnySelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = JokeDashboardPresenter();
        presenter.attachView(this)

        setupRecyclerView()

        binding.apply {
            fabRefresh.setOnClickListener {
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }

            tbgCategory.setOnCheckedChangeListener { group, checkedId, isChecked ->
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
                when (checkedId) {
                    R.id.lt_any -> {
                        setSelectedCategoryName(Constant.JokeCategory.ANY, isChecked)
                    }
                    R.id.lt_programming -> {
                        setSelectedCategoryName(Constant.JokeCategory.PROGRAMMING, isChecked)
                    }
                    R.id.lt_misc -> {
                        setSelectedCategoryName(Constant.JokeCategory.MISC, isChecked)
                    }
                    R.id.lt_dark -> {
                        setSelectedCategoryName(Constant.JokeCategory.DARK, isChecked)
                    }
                    R.id.lt_pun -> {
                        setSelectedCategoryName(Constant.JokeCategory.PUN, isChecked)
                    }
                    R.id.lt_spooky -> {
                        setSelectedCategoryName(Constant.JokeCategory.SPOOKY, isChecked)
                    }
                    R.id.lt_christmas -> {
                        setSelectedCategoryName(Constant.JokeCategory.CHRISTMAS, isChecked)
                    }
                }
            }
        }
    }

    private fun setSelectedCategoryName(jokeCategory: String, isSelected: Boolean) {
        if (isSelected) {
            filteredCategory.add(jokeCategory)
        } else {
            filteredCategory.remove(jokeCategory)
        }
        Log.d("test1", filteredCategory.toString())
    }

    private fun setupRecyclerView() {
        mAdapter = GroupieAdapter()
        mSection = Section()

        mAdapter?.add(mSection!!)
        binding.apply {
            rvJokeList.apply {
                val mLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                layoutManager = mLayoutManager
                adapter = mAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onReceiveMultiAnyJoke(jokesList: List<Jokes>) {
        mSection?.clear()
        for (jokes in jokesList) {
            mSection?.add(JokeListItem(jokes))
        }
    }
}