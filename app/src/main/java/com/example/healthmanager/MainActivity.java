package com.example.healthmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.healthmanager.databinding.ActivityMainBinding;
import com.example.healthmanager.ui.AdviceFragment;
import com.example.healthmanager.ui.HomeFragment;
import com.example.healthmanager.ui.LoginFragment;
import com.example.healthmanager.ui.RecordsFragment;
import com.example.healthmanager.ui.RewardFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

     private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Check if the user is authenticated using Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            // If not signed in, hide the BottomNavigationView and show the login screen
            binding.bottomNavigation.setVisibility(View.GONE);
            loadFragment(new LoginFragment());
        } else {
            // If signed in, show the BottomNavigationView and load the home screen
            binding.bottomNavigation.setVisibility(View.VISIBLE);
            loadFragment(new HomeFragment());

            // Set up bottom navigation listener
            binding.bottomNavigation.setOnItemSelectedListener(item -> {
                Fragment selectedFragment = null;
                int itemId = item.getItemId();

                // Handle different menu item selections
                if (itemId == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.nav_records) {
                    selectedFragment = new RecordsFragment();
                } else if (itemId == R.id.nav_advice) {
                    selectedFragment = new AdviceFragment();
                } else if (itemId == R.id.nav_rewards) {
                    selectedFragment = new RewardFragment();
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                    return true;
                }
                return false;
            });
        }
    }

    // Helper method to load fragments
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }
}
