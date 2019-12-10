package com.moovers.moovers.ui.auth;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moovers.moovers.R;
import com.moovers.moovers.ui.category.CategoryActivity;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;

    ProgressDialog progressDialog;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        Button signUpButton = view.findViewById(R.id.signup_button);

        final EditText emailEditText = view.findViewById(R.id.email);

        final EditText passwordEditText  = view.findViewById(R.id.password);

        final EditText confirmPasswordEditText  = view.findViewById(R.id.confirm_password);


        mAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();


                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getContext(), "email and password can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!confirmPassword.equals(password)){
                    Toast.makeText(getContext(), "Password mismatch", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6){
                    Toast.makeText(getContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressDialog.show();

                signUp(email,password);


            }
        });

    }

    private void signUp (String email, String password){
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Log.d(TAG, "Create user with email sucessful");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Welcome"+user.getEmail(), Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getContext(), CategoryActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}


