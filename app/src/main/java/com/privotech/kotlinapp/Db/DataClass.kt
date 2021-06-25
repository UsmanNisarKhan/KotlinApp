package com.privotech.kotlinapp.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "data_class")
class DataClass {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "Title")
    var title = ""

    @ColumnInfo(name = "Type")
    var type = ""

    @ColumnInfo(name = "Data")
    var data = ""

    @ColumnInfo(name = "Time")
    var time = 0L

}