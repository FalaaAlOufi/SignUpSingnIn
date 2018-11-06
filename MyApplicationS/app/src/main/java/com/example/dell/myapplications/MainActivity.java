package com.example.dell.myapplications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Button btnSignOut;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    private static final String TAG = "EmailPassword";


    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

        btnSignOut = (Button) findViewById(R.id.sign_out_button);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                try {
                    auth.signOut();
                    Toast.makeText(MainActivity.this, "User Sign out!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                     startActivity(intent);
                }catch (Exception e) {
                    Log.e(TAG, "onClick: Exception "+e.getMessage(),e );
                }

              //  auth.signOut();
              //  FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
               //     @Override
               //     public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //        FirebaseUser user = firebaseAuth.getCurrentUser();
                //        if ( user == null) {
                          //   startActivity(new Intent(MainActivity.this, LoginActivity.class));
                  //         finish();
                          //  finishAffinity();
                   //     }
                //    }
              //  };
              //  auth.addAuthStateListener(authListener);
              //  Intent intent = new Intent(MainActivity.this, LoginActivity.class);
              //  startActivity(intent);
            }
        });

        findViewById(R.id.change_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 1));
            }
        });

        findViewById(R.id.change_email_button).setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 2));
            }
        });

        findViewById(R.id.delete_user_button).setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 3));
            }
        });
    }

    @Override    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }
}