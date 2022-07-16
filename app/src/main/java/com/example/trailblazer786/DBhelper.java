package com.example.trailblazer786;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
     public DBhelper(Context context) {
        super(context,"Userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
      DB.execSQL("create Table Userdetails(name TEXT primary key,email TEXT,password TEXT,institute TEXT,profession TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
     DB.execSQL("drop Table if exists Userdetails");
    }
    public boolean insertuserdata(String name,String email,String password,String institute, String profession){
      SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues ContentValues =new ContentValues();
        ContentValues.put("name",name);
        ContentValues.put("email",email);
        ContentValues.put("password",password);
        ContentValues.put("institute",institute);
        ContentValues.put("profession",profession);
        long result=DB.insert("Userdetails",null,ContentValues);
        if(result==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean updateuserdata(String name,String email,String password,String institute, String profession) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("email", email);
        ContentValues.put("password", password);
        ContentValues.put("institute", institute);
        ContentValues.put("profession", profession);
        Cursor Cursor=DB.rawQuery("Select * from Userdetails where name=?",new String[] {name});
        if(Cursor.getCount()>0){
            long result = DB.update("Userdetails",ContentValues,"name=?",new String[] {name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            return false;
        }
    }

  public Boolean checkemail(String EMAIL){
         SQLiteDatabase DB=this.getWritableDatabase();
         Cursor cursor=DB.rawQuery("Select * from Userdetails where email=?",new String[]{EMAIL});
         if(cursor.getCount()>0){
             return  true;
         }
         else{
             return false;
         }
  }

  public Boolean checkemailPassword(String email,String password){
         SQLiteDatabase DB=this.getWritableDatabase();
         Cursor cursor=DB.rawQuery("Select * from Userdetails where email=? and password=?",new String[]{email,password});
         if(cursor.getCount()>0){
             return true;
         }
         else{
             return  false;
         }
  }



    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor Cursor=DB.rawQuery("Select * from Userdetails",null);
        return Cursor;
        }




}
