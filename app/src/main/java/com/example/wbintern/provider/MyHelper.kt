package com.example.wbintern.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TEMPTABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, CITY TEXT, TEMPERATURE TEXT)")
        db?.execSQL("INSERT INTO TEMPTABLE(CITY,TEMPERATURE) VALUES ('Москва', '+10')")
        db?.execSQL("INSERT INTO TEMPTABLE(CITY,TEMPERATURE) VALUES ('Симферополь', '+15')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //do nothing
    }

    companion object {
        private const val DB_NAME = "MY_DB"
    }
}