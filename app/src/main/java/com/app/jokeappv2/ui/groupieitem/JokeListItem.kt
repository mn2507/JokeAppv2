package com.app.jokeappv2.ui.groupieitem

import android.content.Context
import android.view.View
import com.app.jokeappv2.data.response.Jokes
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

    override fun bind(viewBinding: ItemJokeListBinding, position: Int) {
        mContext = viewBinding.root.context
        mBinding = viewBinding

        mBinding.apply {
            tvJokeSetup.text = name.setup
            tvJokeDelivery.text = name.delivery
        }
    }

    override fun getLayout(): Int = R.layout.item_joke_list

    override fun initializeViewBinding(view: View): ItemJokeListBinding {
        return ItemJokeListBinding.bind(view)
    }


}