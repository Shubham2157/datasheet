package com.example.datasheettest

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext
import java.util.ArrayList
import kotlin.coroutines.coroutineContext

val DATABASE_NAME = "MY DB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_MEMBERSHIP = "membership"
val COL_OPENING_LOAN = "openingLoan"
val COL_REPAYMENT = "repayment"



class Database_handler(var context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL( "CREATE TABLE "+TABLE_NAME+ "("+
                COL_MEMBERSHIP+" INTEGER,"+
                COL_NAME+" VARCHAR(256),"+
                COL_REPAYMENT+" INTEGER,"+
                COL_OPENING_LOAN+" INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates
    }

    fun insertdData(user: User) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_MEMBERSHIP, user.membership)
        cv.put(COL_NAME, user.name)
        cv.put(COL_OPENING_LOAN, user.openingLoan)
        cv.put(COL_REPAYMENT, user.repayment)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }


    fun readData() : MutableList<User>
    {
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst())
        {
            do {
                var user = User()
                user.membership = result.getString(result.getColumnIndex(COL_MEMBERSHIP)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.openingLoan = result.getString(result.getColumnIndex(COL_OPENING_LOAN)).toInt()
                user.repayment = result.getString(result.getColumnIndex(COL_REPAYMENT)).toInt()
                list.add(user)
            }while (result.moveToNext())
        }


        result.close()
        db.close()
        return list
    }

    fun updateData()
    {
        val db = this.writableDatabase

        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if (result.moveToFirst())
        {

            do {

                var cv = ContentValues()
                cv.put(COL_MEMBERSHIP,(result.getInt(result.getColumnIndex(COL_MEMBERSHIP)) + 1))
                cv.put(COL_OPENING_LOAN,(result.getInt(result.getColumnIndex(COL_OPENING_LOAN)) + 1))
                cv.put(COL_REPAYMENT,(result.getInt(result.getColumnIndex(COL_REPAYMENT)) + 1))


                db.update(TABLE_NAME,cv, COL_MEMBERSHIP + "=? AND " + COL_NAME + "=? AND " + COL_OPENING_LOAN + "=? AND " + COL_REPAYMENT + "=?",
                    arrayOf(result.getString(result.getColumnIndex(COL_MEMBERSHIP)),
                            result.getString(result.getColumnIndex(COL_NAME)),
                            result.getString(result.getColumnIndex(COL_OPENING_LOAN)),
                            result.getString(result.getColumnIndex(COL_REPAYMENT))))

            }while (result.moveToNext())
        }


        result.close()
        db.close()
    }


    fun  deletedata(){
        val db =this.writableDatabase

        db.delete(TABLE_NAME, null, null)
    }


}










