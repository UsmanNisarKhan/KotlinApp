package com.privotech.kotlinapp.Db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "car_table")
class CarClass {

    @PrimaryKey(autoGenerate = true)
     var id = 0

    @ColumnInfo(name = "Tyres")
     var tyres = ""

    @ColumnInfo(name = "Doors")
    var doors = ""

    @ColumnInfo(name = "Power")
    var power = ""

}

//data class CarClass (
//
//    @PrimaryKey() val id: Int,
//    @ColumnInfo(name = "Tyres") val tyres: String?,
//    @ColumnInfo(name = "Doors") val doors: String?,
//    @ColumnInfo(name = "Power") val power: String?
//)