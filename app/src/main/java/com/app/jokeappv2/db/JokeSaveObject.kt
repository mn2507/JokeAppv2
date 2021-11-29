package com.app.jokeappv2.db

import androidx.room.*

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
@Dao
interface JokeSaveObject {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveJoke(jokeSaveModel: JokeSaveModel)

    @Query("select * from saved_jokes where setup=:setup and delivery=:delivery")
    fun checkJokes(setup: String, delivery: String): String

//    @Query("select * from saved_jokes where _id=:id")
//    fun fetchJokes(id: Int): JokeSaveHandler

    @Query("select * from saved_jokes")
    fun getSavedJokes(): List<JokeSaveModel>

    @Update
    fun editJoke(jokeSaveModel: JokeSaveModel)

    @Delete
    fun deleteSavedJoke(jokeSaveModel: JokeSaveModel): Int
}