package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText txt_email,txt_password,txt_cPassword;
    Button signup_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();

        init();
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email=txt_email.getText().toString().trim();
            String password=txt_password.getText().toString().trim();
            String cPassword=txt_cPassword.getText().toString().trim();

            if (email.isEmpty()){
                Toast.makeText(Signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            }
                if (password.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (cPassword.isEmpty()){
                    Toast.makeText(Signup.this, "Please Enter Confirm Password ", Toast.LENGTH_SHORT).show();
                }
                if (password.length()>6){
                    Toast.makeText(Signup.this, "Password Too short", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(cPassword)){
                   auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()){
                              Intent signupIntent=new Intent(getApplicationContext(),MainActivity.class);
                              startActivity(signupIntent);
                              Toast.makeText(Signup.this, "Signup Copt", Toast.LENGTH_SHORT).show();
                              finish();
                          }
                       }
                   });
                }
            }
        });
    }
    public void init(){
        txt_email=findViewById(R.id.et_email);
        txt_password=findViewById(R.id.et_password);
        txt_cPassword=findViewById(R.id.et_cPassword);
        signup_btn=findViewById(R.id.btn_signup);
    }
}