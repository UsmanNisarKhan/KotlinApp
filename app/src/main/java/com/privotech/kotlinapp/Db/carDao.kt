package com.privotech.kotlinapp.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface carDao {

    @Insert
    fun insert(carClass : CarClass)

    @Insert
    fun insertAll(carClass : ArrayList<CarClass>)

    @Query("Select * FROM car_table")
    fun getAll() : LiveData<List<CarClass>>

    @Query("Delete FROM car_table Where id == :id")
    fun delete(id : Int) : Int

}