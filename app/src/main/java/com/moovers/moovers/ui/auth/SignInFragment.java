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

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    FirebaseAuth mAuth;
    ProgressDialog progressDialog;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth =FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        Button signinButton = view.findViewById(R.id.signin_button);
        final EditText emailEditText = view.findViewById(R.id.email);
        final EditText passwordEditText = view.findViewById(R.id.password);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()){

                    Toast.makeText(getContext(), "Email and Password can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();

                signIn(email, password);


            }
        });
    }

    private  void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Log.d(TAG, "Sign In with Email Sucessful!");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Welcome back "+user.getEmail()+"!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext(), CategoryActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Log.w(TAG, "Sign In with Email failed!");
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
