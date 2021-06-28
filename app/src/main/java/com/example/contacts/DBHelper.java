package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUMBER = "number";

    DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_NUMBER + " TEXT);";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

    void addContact(String name, String number) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NUMBER, number);

        long result = database.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Toast.makeText(context,"Adding contact failed!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Contact successfully added!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllContacts() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if (database != null) {
            cursor = database.rawQuery(query, null);
        }

        return cursor;
    }

    void updateContact(String id, String name, String number) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_NUMBER, number);

        long result = database.update(TABLE_NAME, contentValues, "id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Update failed!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Update was successful!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteContact(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        long result = database.delete(TABLE_NAME, "id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Deletion failed!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Deletion was successful!", Toast.LENGTH_SHORT).show();
        }
    }
}
