package com.mobileapp.mobilelaba2.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;
import com.mobileapp.mobilelaba2.database.Contact;


import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyContactsHolder> {

    private List<Contact> contacts;
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.inflater = LayoutInflater.from(context);
        this.contacts = contacts;
    }

    @Override
    public MyContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contact_view, parent, false);
        return new MyContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(MyContactsHolder holder, int position) {
        Contact currentContact = contacts.get(position);
        holder.contactNameSurname.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
        holder.contactNumber.setText("Contact Number: " + currentContact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    // Вкладений клас для представлення елементів списку
    static class MyContactsHolder extends RecyclerView.ViewHolder {
        TextView contactNameSurname;
        TextView contactNumber;

        MyContactsHolder(View itemView) {
            super(itemView);
            contactNameSurname = itemView.findViewById(R.id.contactNameSurname);
            contactNumber = itemView.findViewById(R.id.contactNumber);
        }
    }
}
