package com.app.jokeappv2.ui.add

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.app.jokeappv2.base.BaseActivity
import com.app.jokeappv2.db.JokeRepository
import com.app.jokeappv2.db.JokeSaveModel
import com.app.jokeappv2.ui.saved.JokeSavedActivity
import com.example.jokeappv2.databinding.ActivityJokeAddBinding

/**
 * Created by Muthu Narayanan on 29/11/2021.
 */
class JokeAddActivity : BaseActivity(), JokeAddMvpView {
    private lateinit var binding: ActivityJokeAddBinding
    private lateinit var jokeSaveModel: JokeSaveModel
    private lateinit var jokeRepository: JokeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityJokeAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        jokeRepository = JokeRepository(this)

        binding.apply {
            btnSave.setOnClickListener {
                val setup: String = etJokeSetup.text.toString().trim()
                val delivery: String = etJokeDelivery.text.toString().trim()
//                Log.d("setup", "$setup $delivery")

                if (setup.isNotEmpty() && delivery.isNotEmpty()) {
                    jokeSaveModel = JokeSaveModel(setup = setup, delivery = delivery)
                    jokeRepository.saveNewJoke(jokeSaveModel)
                    Toast.makeText(
                        applicationContext,
                        "Joke has added!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please provide a value.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            btnViewJoke.setOnClickListener {
                startActivity(Intent(this@JokeAddActivity, JokeSavedActivity::class.java))
            }
        }
    }
}