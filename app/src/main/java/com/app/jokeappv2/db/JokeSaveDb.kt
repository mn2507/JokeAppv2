package com.app.jokeappv2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
@Database(entities = [JokeSaveModel::class], version = 1, exportSchema = false)
@TypeConverters(DbConverter::class)
abstract class JokeSaveDb : RoomDatabase() {

    abstract fun jokeSaveObject(): JokeSaveObject

    companion object {
        private var instance: JokeSaveDb? = null

        fun getInstance(context: Context): JokeSaveDb? {
            if (instance == null) {
                synchronized(JokeSaveDb::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JokeSaveDb::class.java, "saved_jokes.db"
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}