package com.mertfatih.bookhabit.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.mertfatih.bookhabit.R;
import com.mertfatih.bookhabit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavHostFragment navHostFragment;
    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);
        NavigationUI.setupWithNavController(binding.navView, navController);
        binding.navView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeScreen: {
                int destination = R.id.mainFragment;
                if(isValidDestination(destination)) {
                    NavOptions options = new NavOptions.Builder().setPopUpTo(destination, true).build();
                    navController.navigate(destination, null, options);
                }
            }
            break;
            case R.id.addBook: {
                int destination = R.id.addFragment;
                if(isValidDestination(destination)) {
                    navController.navigate(destination);
                }
            }
            break;
        }
        item.setChecked(true);
        binding.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private boolean isValidDestination(int destination) {
        return destination != navController.getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, binding.drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                return binding.drawerLayout.isDrawerOpen(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }



}