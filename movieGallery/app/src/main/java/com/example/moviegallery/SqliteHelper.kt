package com.example.moviegallery

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version){
    override fun onCreate(db: SQLiteDatabase?) {
        val create = "create table movie(" +
                "no integer primary key, " +
                "title text, " +
                "image text)"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertMovie(movie: Movie){
        val values = ContentValues()
        values.put("title", movie.title)
        values.put("image", movie.image)

        val wd = writableDatabase
        wd.insert("movie", null, values)
        wd.close()
    }

    @SuppressLint("Range")
    fun selectMovie(): MutableList<Movie>{
        val list = mutableListOf<Movie>()

        val select = "select * from movie"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        while(cursor.moveToNext()){
            val no = cursor.getInt(cursor.getColumnIndex("no"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val datetime = cursor.getString(cursor.getColumnIndex("image"))
            list.add(Movie(no, title, datetime))
        }
        cursor.close()
        rd.close()

        return list
    }

    fun deleteMovie(no : Int){
        val delete = "delete from movie where no = ${no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }


}