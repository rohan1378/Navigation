package com.example.navigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Graph";
    public static final String TABLE_NAME = "Edges";
    public static final String COL_1 = "Id";

    public static final String COL_2 = "Source";
    public static final String COL_3 = "Destination";
    public static final String COL_4 = "Weight";
    public static final String COL_5 = "Direction";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 +" TEXT ," +
                COL_3 +" TEXT ," +
                COL_4 +" REAL ," +
                COL_5 +" TEXT)";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TRIGGER IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param Source
     * @param Destination
     * @return
     */
    public Cursor getItemDirection(String Source, String Destination){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_5 + " FROM " + TABLE_NAME +
                " WHERE " + COL_2 + " = '" + Source + "' AND "
                + COL_3 + " = '" + Destination + "'";
        Cursor data = db.rawQuery(query, null);
        System.out.println(data.getColumnName(0));
        return data;
    }


    public boolean addData(String Source, String Destination, Double weight, String Direction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Source);
        contentValues.put(COL_3, Destination);
        contentValues.put(COL_4, weight);
        contentValues.put(COL_5, Direction);

        Log.d(TAG, "addData: Adding " + Source + "," + Destination + "," + weight + "," + Destination + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            Log.d(TAG, "added");

            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_2 +
                " = '" + newName + "' WHERE " + COL_1 + " = '" + id + "'" +
                " AND " + COL_2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_1 + " = '" + id + "'" +
                " AND " + COL_2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}