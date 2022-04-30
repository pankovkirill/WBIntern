package com.example.wbintern.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {

    companion object {
        private const val PROVIDER_NAME = "wbintern.provider"
        private const val URL = "content://$PROVIDER_NAME/TEMPTABLE"
        val CONTENT_URI: Uri = Uri.parse(URL)

        private const val TABLE_NAME = "TEMPTABLE"

        const val ID = "_id"
        const val CITY = "CITY"
        const val TEMPERATURE = "TEMPERATURE"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = MyHelper(context)
        db = helper.writableDatabase
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        db.insert(TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val count = db.update(TABLE_NAME, values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val count = db.delete(TABLE_NAME, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        return db.query(TABLE_NAME, p1, p2, p3, null, null, p4)
    }

    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/vnd.example.temptable"
    }
}