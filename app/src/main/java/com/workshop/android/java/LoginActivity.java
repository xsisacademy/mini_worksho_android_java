package com.workshop.android.java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.workshop.android.java.utilities.Constanta;
import com.workshop.android.java.utilities.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private Context context = this;

    private EditText inputUsername, inputPassword;
    private CheckBox checkBoxRemember;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inisialisasi
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        //tambahkan action listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deklarasikan aksinya
                validasiInput();
            }
        });

        checkRemember();
    }

    private void checkRemember(){
        if(SessionManager.cekRemember(context)){
            //artinya checkbox di klik
            inputUsername.setText(SessionManager.getUserName(context));
            inputPassword.setText(SessionManager.getPassword(context));

            checkBoxRemember.setChecked(true);
        }
        else{
            //none
        }
    }

    private void validasiInput(){
        String valueUsername = inputUsername.getText().toString().trim();
        String valuePassword = inputPassword.getText().toString().trim();

        if(valueUsername.equalsIgnoreCase("")){
            Toast.makeText(context, "Username masih kosong!", Toast.LENGTH_SHORT).show();
        }
        else if(valuePassword.length() == 0){
            Toast.makeText(context, "Password masih kosong!", Toast.LENGTH_SHORT).show();
        }
        else{
            //validasi sukses
            boolean remember = checkBoxRemember.isChecked();

            //simpan data login
            SessionManager.simpanDataLogin(context, valueUsername, valuePassword, remember);

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(Constanta.KEY_EXTRA_USERNAME, valueUsername);
            intent.putExtra(Constanta.KEY_EXTRA_PASSWORD, valuePassword);

            startActivity(intent);

            finish();
        }
    }
}
