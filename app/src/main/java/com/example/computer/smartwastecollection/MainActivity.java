package com.example.computer.smartwastecollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void driver(View view) {
            Intent d=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(d);
    }

    public void admin(View view) {
            Intent a=new Intent(MainActivity.this,firebase_console.class);
            startActivity(a);
    }
}
