package com.mobileapp.mobilelaba2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Створення таблиці
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT)");

        // Очищення таблиці перед додаванням контактів
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Оновлення бази даних, якщо потрібно
    }

    public List<Contact> getContactsStartingWithT() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " +
                COLUMN_LAST_NAME + " LIKE 'Т%'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Використання getColumnIndexOrThrow для уникнення помилок
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));

                // Перевірка, чи існує стовпець перед отриманням його значення
                int firstNameColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME);
                String firstName = cursor.getString(firstNameColumnIndex);

                int lastNameColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME);
                String lastName = cursor.getString(lastNameColumnIndex);

                int phoneNumberColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_PHONE_NUMBER);
                String phoneNumber = cursor.getString(phoneNumberColumnIndex);

                contacts.add(new Contact(id, firstName, lastName, phoneNumber));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return contacts;
    }


    public long insertContact(String firstName, String lastName, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);

        long insertedId = db.insert(TABLE_CONTACTS, null, values);
        db.close();

        return insertedId;
    }
    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Використовуйте метод delete для видалення всіх рядків з вашої таблиці контактів
        db.delete(TABLE_CONTACTS, null, null);

        db.close();
    }
    public int updateContact(String firstName, String lastName, String newPhoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE_NUMBER, newPhoneNumber);

        String whereClause = COLUMN_FIRST_NAME + " = ? AND " + COLUMN_LAST_NAME + " = ?";
        String[] whereArgs = {firstName, lastName};

        int rowsAffected = db.update(TABLE_CONTACTS, values, whereClause, whereArgs);
        db.close();
        return rowsAffected;
    }
}

/*public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts_database";
    private static final int DATABASE_VERSION = 1;

    // Таблиця та колонки
    private static final String TABLE_CONTACTS = "contacts";
    public static  final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    // Конструктор
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод для створення таблиці при першому запуску
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_CONTACTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT);";
        db.execSQL(createTableQuery);
    }

    // Метод для оновлення таблиці при зміні версії бази даних
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Опціонально реалізуйте оновлення бази даних
    }

    // Метод для додавання контакту до бази даних
    public long insertContact(String firstName, String lastName, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        long insertedId = db.insert(TABLE_CONTACTS, null, values);
        db.close();
        return insertedId;
    }

    // Метод для отримання всіх контактів з бази даних
    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_CONTACTS, null, null, null, null, null, null);
    }
}*/
