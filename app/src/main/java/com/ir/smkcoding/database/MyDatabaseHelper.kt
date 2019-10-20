package com.ir.smkcoding.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ir.smkcoding.model.ResultsItem
import org.jetbrains.anko.db.*

class MyDatabaseHelper (context: Context) : ManagedSQLiteOpenHelper(context, "database_movie", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db?.createTable(ResultsItem.TABLE_FAVORITE,
            true,
        ResultsItem.COLUMN_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ResultsItem.COLUMN_TITLE to TEXT,
            ResultsItem.COLUMN_POSTERPATH to TEXT,
            ResultsItem.COLUMN_RATING to REAL,
            ResultsItem.COLUMN_DESCRIPTION to TEXT
        )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db?.dropTable(ResultsItem.TABLE_FAVORITE)
        onCreate(db)
    }

}

val Context.database : MyDatabaseHelper
    get() = MyDatabaseHelper(applicationContext)