package com.app.jokeappv2.db

import android.content.Context
import android.os.AsyncTask

/**
 * Created by Muthu Narayanan on 26/11/2021.
 */
class JokeRepository(context: Context) {
    var db: JokeSaveObject = JokeSaveDb.getInstance(context)?.jokeSaveObject()!!

    //Fetch all saved jokes
    fun getAllSavedJokes(): List<JokeSaveModel> {
        return db.getSavedJokes()
    }

    // Save new joke
    fun saveNewJoke(jokeSaveModel: JokeSaveModel) {
        insertAsyncTask(db).execute(jokeSaveModel)
    }

    // Edit joke
    fun editJoke(jokeSaveModel: JokeSaveModel) {
        db.editJoke(jokeSaveModel)
    }

    // Delete joke
    fun deleteSavedJoke(jokeSaveModel: JokeSaveModel) {
        db.deleteSavedJoke(jokeSaveModel)
    }

    private class insertAsyncTask(private val jokeSaveObject: JokeSaveObject) :
        AsyncTask<JokeSaveModel, Void, Void>() {

        override fun doInBackground(vararg params: JokeSaveModel): Void? {
            jokeSaveObject.saveJoke(params[0])
            return null
        }
    }
}