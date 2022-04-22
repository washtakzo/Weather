package com.khuwarizmi.weather

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.khuwarizmi.weather.city.City

private const val DATABASE_NAME = "weather.kt"
private const val DATABASE_VERSION = 1
private const val CITY_TABLE_NAME = "city"
private const val CITY_KEY_ID = "id"
private const val CITY_KEY_NAME = "name"

private const val CITY_TABLE_CREATE = """
    CREATE TABLE $CITY_TABLE_NAME (
    $CITY_KEY_ID INTEGER PRIMARY KEY,
    $CITY_KEY_NAME VARCHAR
    )
"""

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(CITY_TABLE_CREATE)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun createCity(city : City) : Boolean {
        val values = ContentValues()
        values.put(CITY_KEY_NAME,city.name)
        val id = writableDatabase.insert(CITY_TABLE_NAME, null, values)
        city.id = id.toInt()
        return id > 0   //The default value of id we defined -1, if it is supperior to 0 then the city has correctly been added
    }

}
