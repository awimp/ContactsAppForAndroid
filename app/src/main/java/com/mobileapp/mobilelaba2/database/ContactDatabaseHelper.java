
package com.mobileapp.mobilelaba2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public ContactDatabaseHelper(Context context) {
        super(context, "contacts.db", (SQLiteDatabase.CursorFactory)null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT)";
        db.execSQL(createTableQuery);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        this.onCreate(db);
    }
}
