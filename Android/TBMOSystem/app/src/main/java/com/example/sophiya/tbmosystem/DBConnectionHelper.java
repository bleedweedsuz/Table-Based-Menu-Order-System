package com.example.sophiya.tbmosystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sujan Thapa on 23/02/2016.
 */
public class DBConnectionHelper extends SQLiteOpenHelper{
    public DBConnectionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ItemList(SN INTEGER PRIMARY KEY AUTOINCREMENT,ID INTEGER NOT NULL,NAME TEXT NOT NULL,COMMENT TEXT NOT NULL,TOKEN TEXT NOT NULL,ISORDER INTEGER NOT NULL,PRICE DOUBLE NOT NULL);");
        db.execSQL("CREATE TABLE CItemList(SN INTEGER PRIMARY KEY AUTOINCREMENT,ITEMID INTEGER NOT NULL,ITEMNAME TEXT NOT NULL,NOTE TEXT NOT NULL,TOKEN TEXT NOT NULL,TIME TEXT NOT NULL,TABLENO TEXT NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Nothing to upgrade
    }
}
