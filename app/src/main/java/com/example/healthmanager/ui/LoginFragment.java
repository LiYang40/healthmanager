package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthmanager.R;
import com.example.healthmanager.data.remote.FirebaseHelper;
import com.example.healthmanager.data.remote.FirebaseHelper.FirebaseCallback;
import com.example.healthmanager.model.HealthRecord;

public class LoginFragment extends Fragment {

    private EditText emailEditText, passwordEditText;
    private Button signUpButton, signInButton;

    private FirebaseHelper firebaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize UI components
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signUpButton = view.findViewById(R.id.signUpButton);
        signInButton = view.findViewById(R.id.signInButton);

        // Initialize FirebaseHelper
        firebaseHelper = new FirebaseHelper();

        // Set click listeners
        signUpButton.setOnClickListener(v -> signUp());
        signInButton.setOnClickListener(v -> signIn());

        return view;
    }

    private void signUp() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
        } else {
            firebaseHelper.signUp(email, password, new FirebaseCallback() {
                @Override
                public void onAuthSuccess() {
                    Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                    // Show BottomNavigationView
                    requireActivity().findViewById(R.id.bottomNavigation).setVisibility(View.VISIBLE);

                    // Navigate to HomeFragment
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new HomeFragment())
                            .commit();
                }


                @Override
                public void onAuthError(String errorMessage) {
                    Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDataLoaded(HealthRecord record) {}

                @Override
                public void onDataError(String errorMessage) {}
            });
        }
    }

    private void signIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
        } else {
            firebaseHelper.signIn(email, password, new FirebaseCallback() {
                @Override
                public void onAuthSuccess() {
                    Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                    // Navigate to HomeFragment
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new HomeFragment())
                            .commit();
                }

                @Override
                public void onAuthError(String errorMessage) {
                    Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onDataLoaded(HealthRecord record) {}

                @Override
                public void onDataError(String errorMessage) {}
            });
        }
    }
}
