package com.project.miradio;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin;
    private EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnLogin= findViewById(R.id.btn_login);
        et_name= findViewById(R.id.et_name);
       btnLogin.setOnClickListener(this);
    }

    private void goToHome(){
        if(!et_name.getText().toString().equals("")){
            Bundle extras = new Bundle();
            extras.putString("name",et_name.getText().toString());
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtras(extras);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                goToHome();
                break;
        }
    }
}