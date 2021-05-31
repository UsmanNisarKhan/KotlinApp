package com.privotech.kotlinapp.Db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SetDAO {

    @Insert
    fun insert(scanClass: DataClass?)

    @Query("SELECT * FROM data_class")
    fun getAll(): LiveData<List<DataClass>>

    @Query("Delete From data_class Where id == :id")
    fun delete(id: Int): Int

}