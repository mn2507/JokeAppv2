package com.app.jokeappv2.ui.groupieitem

import android.content.Context
import android.view.View
import android.widget.Toast
import com.app.jokeappv2.data.response.Jokes
import com.app.jokeappv2.db.JokeRepository
import com.app.jokeappv2.db.JokeSaveModel
import com.example.jokeappv2.R
import com.example.jokeappv2.databinding.ItemJokeListBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Muthu Narayanan on 21/11/2021.
 */
class JokeListItem(
    private val name: Jokes
) : BindableItem<ItemJokeListBinding>() {

    lateinit var mContext: Context
    lateinit var mBinding: ItemJokeListBinding
    private lateinit var jokeSaveModel: JokeSaveModel
    private lateinit var jokeRepository: JokeRepository

    override fun bind(viewBinding: ItemJokeListBinding, position: Int) {
        mContext = viewBinding.root.context
        mBinding = viewBinding
        jokeRepository = JokeRepository(mContext)

        mBinding.apply {
            tvJokeSetup.text = name.setup
            tvJokeDelivery.text = name.delivery
            tvSaveJoke.setOnClickListener {
                // Save Joke to database
                jokeSaveModel = JokeSaveModel(id = name.id!!, setup = name.setup!!, delivery = name.delivery!!)
                jokeRepository.saveNewJoke(jokeSaveModel)
                Toast.makeText(
                    mContext,
                    "Joke has been saved!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_joke_list

    override fun initializeViewBinding(view: View): ItemJokeListBinding {
        return ItemJokeListBinding.bind(view)
    }


}