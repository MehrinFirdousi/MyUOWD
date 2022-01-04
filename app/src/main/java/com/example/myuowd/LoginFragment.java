package com.example.myuowd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class LoginFragment extends Fragment {

    private final String testUsername = "1234567", testPassword = "password";
    String inputUsername, inputPassword;
    EditText usernameField, passwordField;
    MaterialButton signInButton;
    TextView errorMessage;
    SharedPreferences sp;
    ProgressBar spinner;

    public LoginFragment(SharedPreferences sp) {
        this.sp = sp;
    }

    public static LoginFragment newInstance(SharedPreferences sp) {
        LoginFragment fragment = new LoginFragment(sp);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signInButton = view.findViewById(R.id.signInButton);
        usernameField = view.findViewById(R.id.username);
        passwordField = view.findViewById(R.id.password);
        errorMessage = view.findViewById(R.id.errorMessage);
        spinner = (ProgressBar)view.findViewById(R.id.progressSpinner);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorMessage.setVisibility(View.GONE);
                inputUsername = usernameField.getText().toString();
                inputPassword = passwordField.getText().toString();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    errorMessage.setText(R.string.empty_login_fields_error);
                    errorMessage.setVisibility(View.VISIBLE);
                }
                else if (validateUser(inputUsername, inputPassword))
                    login();
                else {
                    errorMessage.setText(R.string.incorrect_login_credentials_error);
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    private boolean validateUser (String inputUsername, String inputPassword) {
        if (inputUsername.equals(testUsername) && inputPassword.equals(testPassword))
            return true;
        return false;
    }

    public void login () {
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        sp.edit().putBoolean("logged", true).apply();
        getActivity().finish();
    }

}