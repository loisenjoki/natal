package com.luisa.smartnatal.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "smartnatal";
    // Table Names
    private static final String TABLE_APPOINTMENT = "appointments";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "date";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";

    // Appointment table create statement
    private static final String CREATE_TABLE_APPOINTMENT = "CREATE TABLE "
            + TABLE_APPOINTMENT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
            + " TEXT," + KEY_TIME + " TEXT," + KEY_CREATED_AT
            + " TEXT" + ")";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_APPOINTMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);

        // create new tables
        onCreate(db);
    }

    /*
     * Creating a Appointment
     */
    public long createAppointment(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, date.getName());
        values.put(KEY_TIME, date.getTime());
        values.put(KEY_CREATED_AT, date.getDate());

        // insert row
        long appointment_id = db.insert(TABLE_APPOINTMENT, null, values);

        Log.e("Tag Name", String.valueOf(values));

/*
        // assigning tags to todo
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }*/

        return appointment_id;
    }

    /*
     * get single Appointment
     */


    /*
     * get single appointment
     */
    public List<Date> getAppointment(String thedate) {
        List <Date> allapointments = new ArrayList<Date>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_APPOINTMENT + " WHERE "
                + KEY_CREATED_AT + " = " + thedate;

        //String selectQuery = "SELECT  * FROM " + TABLE_APPOINTMENT;
        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst())
            do{
                Date td = new Date();
                td.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
                td.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                td.setDate(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                allapointments.add(td);
                Log.e(">>>>>>>>",String.valueOf(allapointments.size()));

            }while (c.moveToNext());


        return allapointments;
    }

    /*
     * Updating a appointment
     */
    public int updateAppointment(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, date.getName());
        values.put(KEY_TIME, date.getTime());

        // updating row
        return db.update(TABLE_APPOINTMENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(date.getId()) });
    }

    /*
     * Deleting a Appointment
     */
    public void deleteAppointment(long appointment_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APPOINTMENT, KEY_ID + " = ?",
                new String[] { String.valueOf(appointment_id) });
    }
}
