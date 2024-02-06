package com.mobileapp.mobilelaba2.ui.notifications;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.mobilelaba2.database.Contact;

import com.mobileapp.mobilelaba2.R;

public class MyContactsHolder extends RecyclerView.ViewHolder {
    TextView contactNameSurname;
    TextView contactNumber;

    public MyContactsHolder(View itemView) {
        super(itemView);
        contactNameSurname = itemView.findViewById(R.id.contactNameSurname);
        contactNumber = itemView.findViewById(R.id.contactNumber);
    }
}
