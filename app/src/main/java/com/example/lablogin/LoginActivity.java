package com.example.lablogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= etUsername.getText().toString();
                String password= etPassword.getText().toString();
                login(username, password);
            }
        });
    }

    private void login(String username, String password)
    {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null)
                {
                    Log.e(TAG, "Issue with login");
                    Toast.makeText(LoginActivity.this, "Verifiez votre connexion Internet", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }

                goMainActivity();
            }
        });
    }

    private void goMainActivity()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
