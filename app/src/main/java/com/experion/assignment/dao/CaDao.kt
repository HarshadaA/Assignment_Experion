package com.experion.assignment.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.experion.assignment.models.ResponseModel

@Dao
interface CaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsData(newsList: ResponseModel)

    @Query("SELECT * FROM news_data")
    fun getAllNews(): ResponseModel
}