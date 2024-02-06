package com.mobileapp.mobilelaba2.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.List;

import com.mobileapp.mobilelaba2.database.Contact;
import com.mobileapp.mobilelaba2.database.DatabaseHelper;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Ініціалізація бази даних
        databaseHelper = new DatabaseHelper(getActivity());

        // Отримання контактів, фамілія яких починається з "Т"
        List<Contact> contacts = getContactsStartingWithT();

        // Налаштування RecyclerView та адаптера
        recyclerView = view.findViewById(R.id.recyclerViewContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsAdapter = new ContactsAdapter(getActivity(), contacts);
        recyclerView.setAdapter(contactsAdapter);

        return view;
    }

    // Забезпечте закриття бази даних у методі onDestroyView або onDestroy

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    private List<Contact> getContactsStartingWithT() {
        // Отримання контактів з бази даних, де фамілія починається з "Т"
        return databaseHelper.getContactsStartingWithT();
    }
}

    /*private RecyclerView recyclerView;
    private ContactsAdapter contactsAdapter;

    public NotificationsFragment() {
        // Порожній конструктор обов'язковий
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Pass the context (in this case, getActivity()) when calling getContactsStartingWithT
        List<Contact> contacts = getContactsStartingWithT(getActivity());

        // Налаштування RecyclerView та адаптера
        recyclerView = view.findViewById(R.id.recyclerViewContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactsAdapter = new ContactsAdapter(getActivity(), contacts);
        recyclerView.setAdapter(contactsAdapter);

        return view;
    }

    public List<Contact> getContactsStartingWithT(Context context) {
        List<Contact> contactsStartingWithT = new ArrayList<>();

        // Створення інстанції DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        // Отримання всіх контактів з бази даних
        Cursor cursor = dbHelper.getAllContacts();

        // Перевірка наявності контактів
        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
            int firstNameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_FIRST_NAME);
            int lastNameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LAST_NAME);
            int phoneNumberColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE_NUMBER);

            do {
                // Перевірка індексів колонок
                if (idColumnIndex != -1 && firstNameColumnIndex != -1 && lastNameColumnIndex != -1 && phoneNumberColumnIndex != -1) {
                    // Отримання даних з курсора
                    long id = cursor.getLong(idColumnIndex);
                    String firstName = cursor.getString(firstNameColumnIndex);
                    String lastName = cursor.getString(lastNameColumnIndex);
                    String phoneNumber = cursor.getString(phoneNumberColumnIndex);

                    // Створення об'єкта Contact
                    Contact contact = new Contact(id, firstName, lastName, phoneNumber);

                    // Додавання контакту до списку
                    contactsStartingWithT.add(contact);
                } else {
                    // Handle the case where one or more columns are not present
                }
            } while (cursor.moveToNext());
        }

        // Закриття курсора та DatabaseHelper
        if (cursor != null) {
            cursor.close();
        }
        dbHelper.close();

        return contactsStartingWithT;
    }*/


