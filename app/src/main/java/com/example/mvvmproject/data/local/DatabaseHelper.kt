package com.example.mvvmproject.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_USERNAME TEXT UNIQUE NOT NULL, " +
                "$COLUMN_PASSWORD TEXT NOT NULL)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(username: String, hashedPassword: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, hashedPassword)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    // Function to get a user's hashed password
    fun getUserPassword(username: String): String? {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT $COLUMN_PASSWORD FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ?",
            arrayOf(username)
        )
        val hashedPassword = if (cursor.moveToFirst()) cursor.getString(0) else null
        cursor.close()
        return hashedPassword
    }

    // Function to delete a user
    fun deleteUser(username: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USERS, "$COLUMN_USERNAME=?", arrayOf(username))
    }
}