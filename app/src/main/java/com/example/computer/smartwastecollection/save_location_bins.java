package com.example.computer.smartwastecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class save_location_bins extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    private Button btnsave;
    private Button btnproceed;
    private EditText editTextName;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location_bins);
        btnproceed=(Button)findViewById(R.id.btnproceed);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextLatitude=(EditText)findViewById(R.id.editTextLatitude);
        editTextLongitude=(EditText)findViewById(R.id.editTextLongitude);
        btnsave=(Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(save_location_bins.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }
    private void saveUserInformation(){
        String name =editTextName.getText().toString().trim();
        double latitude= Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude= Double.parseDouble(editTextLongitude.getText().toString().trim());
        UserInformation userInformation=new UserInformation(name,latitude,longitude);
        mDatabase.child("Users").setValue(userInformation);
        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View view) {
        if(view==btnproceed){
            finish();
        }
        if (view==btnsave){
            saveUserInformation();
            editTextName.getText().clear();
            editTextLatitude.getText().clear();
            editTextLongitude.getText().clear();
        }
    }
}