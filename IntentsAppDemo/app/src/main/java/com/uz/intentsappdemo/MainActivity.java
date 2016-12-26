package com.uz.intentsappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnOpenSecondActivity;
    EditText edtUserName;
    private final static String USER_NAME_KEY="username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenSecondActivity=(Button)findViewById(R.id.btnNext);
        edtUserName=(EditText)findViewById(R.id.edtUserName);

        btnOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUserName.getText().toString().trim().length() > 0) {

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(USER_NAME_KEY, edtUserName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
