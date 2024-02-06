package com.mobileapp.mobilelaba2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private SQLiteDatabase database;

    public ContactRepository(Context context) {
        ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    // Додавання контакту
    public long addContact(String firstName, String lastName, String phoneNumber) {
        ContentValues values = new ContentValues();
        values.put("first_name", firstName);
        values.put("last_name", lastName);
        values.put("phone_number", phoneNumber);

        return database.insert("contacts", null, values);
    }



}
