package net.songsong.funcode

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import model.SQLItemModel

class MemberDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "example.db", null, 4)
{
    private val tableName = "member"

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
    }

    override fun onCreate(db: SQLiteDatabase)
    {
        val sql = "CREATE TABLE if not exists $tableName(id integer PRIMARY KEY autoincrement,name text)"

        db.execSQL(sql)
    }

    fun addName(name: String)
    {
        val values = ContentValues()
        values.put("name", name)
        writableDatabase.insert(tableName, null, values)
    }

    fun getName(): ArrayList<SQLItemModel>
    {
        val cursor = readableDatabase.query(tableName, arrayOf("id", "name"), null, null, null, null, null)

        val members = ArrayList<SQLItemModel>()

        try
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    val name = cursor.getString(cursor.getColumnIndex("name"))

                    val id = cursor.getInt(cursor.getColumnIndex("id"))

                    val item = SQLItemModel(id, name)

                    members.add(item)
                } while (cursor.moveToNext())
            }
        }
        catch (e: Exception)
        {
        }
        finally
        {
            if (cursor != null && !cursor.isClosed)
            {
                cursor.close()
            }
        }

        println("總共有${cursor.count}筆資料")
        return members
    }
}