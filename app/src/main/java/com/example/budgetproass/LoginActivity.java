package com.example.budgetproass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etUsername, etPassword;
    Button btnLogin, btnReset;
    CheckBox chkRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //mapping
        etUsername = (TextInputEditText) findViewById(R.id.etUsername);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnReset = (Button) findViewById(R.id.btnReset);
        chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);
    }
        public void checkLogin(View view){
        String username = etUsername.getText().toString();
        String pass = etPassword.getText().toString();
        if(username.equals("fpt")&& pass.equals("123")){

            Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
            rememberUser(username,pass,chkRememberMe.isChecked());
            finish();

        }else{
            Toast.makeText(this, "Login thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void rememberUser(String username, String pass, boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status ){
            editor.clear();
        }else {
            editor.putString("USERNAME",username);
            editor.putString("PASSWORD",pass);
            editor.putBoolean("REMEMBER",status);

        }
        editor.commit();
    }
    public void ResetForm(View view){
        etUsername.setText("");
        etPassword.setText("");
    }
}