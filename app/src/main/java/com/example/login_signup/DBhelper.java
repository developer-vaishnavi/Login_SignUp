package com.example.login_signup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public DBhelper(Context context) {
        super(context, "Login.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDb) {
    MyDb.execSQL("create Table users(username  TEXT primary key,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int oldVersion, int newVersion) {
        MyDb.execSQL("drop Table if  exists users");
    }
    public boolean insertData(String username,String email, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = MyDb.insert("users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean checkUsername(String username){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkEmail(String username,String email){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ? and email=?",new String[]{username,email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkPass(String username, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where username = ? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public Boolean updatePass(String user, String pass) {
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("password",pass);
       long result = MyDb.update("users",contentValues,"username = ?", new String[]{user});
        if(result==-1)
            return false;
        else
            return true;
    }
}
