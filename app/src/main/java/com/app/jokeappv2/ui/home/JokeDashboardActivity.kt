package com.app.jokeappv2.ui.home

import android.os.Bundle
import android.util.Log
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
//            for (category in applicationContext.resources.getStringArray(R.array.joke_categories)) {
//                cbCategories
//            }
            cbCategoryAny.setOnCheckedChangeListener { buttonView, isChecked ->
                isAnySelected = isChecked
                setSelectedCategoryName(
                    Constant.JokeCategory.ANY,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategoryProgramming.setOnCheckedChangeListener { buttonView, isChecked ->
                setSelectedCategoryName(
                    Constant.JokeCategory.PROGRAMMING,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategoryMisc.setOnCheckedChangeListener { buttonView, isChecked ->

                setSelectedCategoryName(
                    Constant.JokeCategory.MISC,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategoryDark.setOnCheckedChangeListener { buttonView, isChecked ->

                setSelectedCategoryName(
                    Constant.JokeCategory.DARK,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategoryPun.setOnCheckedChangeListener { buttonView, isChecked ->

                setSelectedCategoryName(
                    Constant.JokeCategory.PUN,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategorySpooky.setOnCheckedChangeListener { buttonView, isChecked ->

                setSelectedCategoryName(
                    Constant.JokeCategory.SPOOKY,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
            }
            cbCategoryChristmas.setOnCheckedChangeListener { buttonView, isChecked ->

                setSelectedCategoryName(
                    Constant.JokeCategory.CHRISTMAS,
                    isChecked
                )
                presenter.getMultiAnyJoke(filteredCategory, 5, Constant.JokeType.TWOPART)
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