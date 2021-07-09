package com.example.onlinestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinestore.utility.toast.CustomToast;
import com.example.onlinestore.utility.toast.CustomToastMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    NavController navController;
    LayoutInflater customToastInflater;
    View customToastLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //bottom navigation bar functionality
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_controller);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        customToastInflater = getLayoutInflater();
        customToastLayout = customToastInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        CustomToast toast = new CustomToast(this, customToastLayout);

        toast.show(CustomToastMode.INFO, "This is an info message.\nthis is second line.", 1);
        toast.show(CustomToastMode.WARNING, "This is a warning message.", 0);
        toast.show(CustomToastMode.ERROR, "This is an error message.", 0);
        toast.show(CustomToastMode.APPROVE, "This is an approval message.", 0);


    }
}
