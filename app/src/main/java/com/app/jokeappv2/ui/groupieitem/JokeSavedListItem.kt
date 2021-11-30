package com.app.jokeappv2.ui.groupieitem

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.app.jokeappv2.db.JokeRepository
import com.app.jokeappv2.db.JokeSaveModel
import com.example.jokeappv2.R
import com.example.jokeappv2.databinding.ItemJokeSavedListBinding
import com.xwray.groupie.viewbinding.BindableItem


/**
 * Created by Muthu Narayanan on 29/11/2021.
 */
class JokeSavedListItem(
    private val name: JokeSaveModel,
    private val listener: UpdateListCallback
) : BindableItem<ItemJokeSavedListBinding>() {

    lateinit var mContext: Context
    lateinit var mBinding: ItemJokeSavedListBinding
    private lateinit var jokeSaveModel: JokeSaveModel
    private lateinit var jokeRepository: JokeRepository
    private lateinit var updateListCallback: UpdateListCallback

    override fun bind(viewBinding: ItemJokeSavedListBinding, position: Int) {
        mContext = viewBinding.root.context
        mBinding = viewBinding
        jokeRepository = JokeRepository(mContext)
        updateListCallback = listener

        mBinding.apply {
            tvJokeSetup.text = name.setup
            tvJokeDelivery.text = name.delivery
            tvDelete.setOnClickListener {
                // Delete Joke in database
                jokeSaveModel = JokeSaveModel(id = name.id, setup = name.setup, delivery = name.delivery)
                jokeRepository.deleteSavedJoke(jokeSaveModel)
                // Update list after deleting joke
                updateListCallback.onListUpdated()
                Toast.makeText(
                    mContext,
                    "Joke has been deleted!",
                    Toast.LENGTH_SHORT
                ).show()

            }
            clJoke.setOnClickListener {
                showDialog()
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_joke_saved_list

    override fun initializeViewBinding(view: View): ItemJokeSavedListBinding {
        return ItemJokeSavedListBinding.bind(view)
    }

    private fun showDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)

        // Set dialog title
        builder.setTitle("Edit Joke")

        // Create viewGroup
        val layout = LinearLayout(mContext)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50,10,50,10)

        // Set setup edit text box
        val inputSetup = EditText(mContext)
        inputSetup.setText(name.setup)
        layout.addView(inputSetup)

        // Set delivery edit text box
        val inputDelivery = EditText(mContext)
        inputDelivery.setText(name.delivery)
        layout.addView(inputDelivery)

        // Set dialog layout
        builder.setView(layout)

        // Set up dialog buttons
        builder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, _ ->

            // Get input text from Edittext
            val editedSetupText = inputSetup.text.toString()
            val editedDeliveryText = inputDelivery.text.toString()

            // Update text in database
            jokeSaveModel = JokeSaveModel(id = name.id, setup = editedSetupText, delivery = editedDeliveryText)
            jokeRepository.editJoke(jokeSaveModel)

            dialog.dismiss()

            // Update list after editing joke
            updateListCallback.onListUpdated()

            Toast.makeText(
                mContext,
                "Joke has been updated!",
                Toast.LENGTH_SHORT
            ).show()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })

        builder.show()
    }

    interface UpdateListCallback {
        fun onListUpdated()
    }
}