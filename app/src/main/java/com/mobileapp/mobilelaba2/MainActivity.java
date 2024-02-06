package com.mobileapp.mobilelaba2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mobileapp.mobilelaba2.database.DatabaseHelper;
import com.mobileapp.mobilelaba2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        dbHelper.deleteAllContacts();

        //long newContactId1 = dbHelper.insertContact("Ім'я1", "Тимчук", "+380671234567");
        long newContactId2 = dbHelper.insertContact("Ім'я2", "Тарасенко", "+380501234567");
        long newContactId3 = dbHelper.insertContact("Ім'я3", "Тидоренко", "+380631234567");
        long newContactId4 = dbHelper.insertContact("Ім'я4", "Іванов", "+380681234567");
        long newContactId5 = dbHelper.insertContact("Ім'я5", "Ткачук", "+380731234567");
        long newContactId6 = dbHelper.insertContact("Ім'я6", "Петренко", "+380441234567");
        long newContactId7 = dbHelper.insertContact("Ім'я7", "Коваленко", "+380671111111");
        //long newContactId = dbHelper.insertContact("Ім'я8", "Тигигиг", "+380501234567");

        // Закриття DatabaseHelper
        dbHelper.close();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

}
