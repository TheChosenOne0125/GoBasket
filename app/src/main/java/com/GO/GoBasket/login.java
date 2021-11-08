    package com.GO.GoBasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.GO.GoBasket.com.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class login extends AppCompatActivity {

        EditText mEmail, mPassword;
        Button mLoginbtn;
        ProgressBar progressBar2;
        TextView mcreateText;
        FirebaseAuth mAuth;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            mEmail = findViewById(R.id.Email);
            mPassword = findViewById(R.id.Password);
            mLoginbtn = findViewById(R.id.loginbtn);
            progressBar2 = findViewById(R.id.progressBar2);
            mAuth = FirebaseAuth.getInstance();
            mcreateText =  findViewById(R.id.createText);


            mLoginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        mEmail.setError("Email is required.");
                        return;

                    }
                    if(TextUtils.isEmpty(password)){
                        mPassword.setError("Password is requaired.");
                        return;
                    }
                    if(password.length() < 6 ){
                        mPassword.setError("Password must be greater or equal to 6 characters");
                        return;

                    }
                    progressBar2.setVisibility(View.VISIBLE);

                    //mAuthenticate the user

                    mAuth.signInWithEmailAndPassword(email,password). addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            }else{
                                Toast.makeText(login.this, "Error ! " + task.getException().getMessage(),  Toast.LENGTH_SHORT).show();
                                progressBar2.setVisibility(View.GONE);
                            }


                        }
                    });




                }
            });


            mcreateText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),register.class));

                }
            });
    }
}