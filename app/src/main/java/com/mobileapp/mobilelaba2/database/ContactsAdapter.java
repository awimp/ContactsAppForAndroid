package com.mobileapp.mobilelaba2.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyContactsHolder> {

    private List<Contact> contacts;
    private Context context;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public MyContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_view, parent, false);
        return new MyContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyContactsHolder holder, int position) {
        // Отримання поточного контакту
        Contact contact = contacts.get(position);

        // Встановлення даних контакту в UI елементи
        holder.studentId.setText(String.valueOf(contact.getId()));
        holder.contactNameSurname.setText(contact.getFirstName() + " " + contact.getLastName());
        holder.contactNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> filteredContacts) {
        // Оновлення списку контактів для відображення в RecyclerView
        this.contacts = filteredContacts;
        notifyDataSetChanged();
    }

    public static class MyContactsHolder extends RecyclerView.ViewHolder {
        // Забезпечення наявності елементів, таких як studentId, contactNameSurname, contactNumber
        // у вашому макеті contact_view.xml
        TextView studentId;
        TextView contactNameSurname;
        TextView contactNumber;

        public MyContactsHolder(@NonNull View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.StudentId);
            contactNameSurname = itemView.findViewById(R.id.contactNameSurname);
            contactNumber = itemView.findViewById(R.id.contactNumber);
        }
    }
}
