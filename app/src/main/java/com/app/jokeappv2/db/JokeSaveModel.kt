package com.app.jokeappv2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
@Entity(tableName = "saved_jokes")
data class JokeSaveModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var id: Int = 0,
    @ColumnInfo(name = "setup") var setup: String,
    @ColumnInfo(name = "delivery") var delivery: String,
)