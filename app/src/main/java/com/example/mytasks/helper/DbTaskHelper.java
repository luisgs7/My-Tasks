package com.example.mytasks.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbTaskHelper extends SQLiteOpenHelper {

        public static int VERSION = 1;
        public static String NAME_DB = "DB_TASK";
        public static String TABLE_TASK = "task";

    public DbTaskHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASK
                    + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";
        try{
            db.execSQL(sql);
            Log.i("INFO DB", "Erro ao criar a tabela ");
        }catch (Exception e ){
            Log.i("INFO DB", "Erro ao criar a tabela " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
