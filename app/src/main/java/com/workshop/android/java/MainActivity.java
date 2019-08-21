package com.workshop.android.java;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.workshop.android.java.utilities.Constanta;
import com.workshop.android.java.utilities.SessionManager;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Context context = this;
    private TextView username;
    private CardView menu1, menu2, menu3, menu4;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu1 = (CardView) findViewById(R.id.menu1);
        menu2 = (CardView) findViewById(R.id.menu2);
        menu3 = (CardView) findViewById(R.id.menu3);
        menu4 = (CardView) findViewById(R.id.menu4);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        username = (TextView) findViewById(R.id.username);

        tangkapIntentExtra();
        tampilkanUsernameDariSessionManager();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lakukan proses logout
                //step 1: ganti flag login menjadi false
                SessionManager.setLoginFlag(context, false);

                //step 2: buka screen login
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);

                //step 3: tutup screen main
                finish();
            }
        });

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CameraActivity.class);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EsafirmActivity.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VolleyActivity.class);
                startActivity(intent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DaftarMahasiswaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void tangkapIntentExtra(){
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            //tidak null
            String valueUserName = bundle.getString(Constanta.KEY_EXTRA_USERNAME);
            String valuePassword = bundle.getString(Constanta.KEY_EXTRA_PASSWORD);

            username.setText(" " + valueUserName + " " +valuePassword);
        }
    }

    private void tampilkanUsernameDariSessionManager(){
        String valueUserName = SessionManager.getUserName(context);
        String valuePassword = SessionManager.getPassword(context);

        username.setText(" " + valueUserName + " " +valuePassword);
    }
}
