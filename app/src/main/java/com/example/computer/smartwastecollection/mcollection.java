package com.example.computer.smartwastecollection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcgdv.asia.lib.fanmenu.FanMenuButtons;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class mcollection extends AppCompatActivity {


    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Previous versions of Firebase
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_mcollection);

        TextView textView2 = (TextView) findViewById(R.id.textView2) ;


        RelativeLayout mc = (RelativeLayout) findViewById(R.id.menucollection);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        mc.startAnimation(myAnim);

        Typeface method = Typeface.createFromAsset(getAssets(),  "fonts/SEASRN.ttf");
        textView2.setTypeface(method);








        mRef = new Firebase("https://smart-waste-collection-e2735.firebaseio.com/number");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                String value= dataSnapshot.getValue().toString();



                Double finalValue=Double.parseDouble(value);
                Double val;
                val = (1-(finalValue/29))*100;



                if (finalValue >= 80){
                    showNotification();
                }
                else {
                    //do nothing
                }




            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //menu
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myFAB);
        final FanMenuButtons sub = (FanMenuButtons) findViewById(R.id.myFABSubmenu);
        if (sub != null) {
            sub.setOnFanButtonClickListener(new FanMenuButtons.OnFanClickListener() {
                @Override
                public void onFanButtonClicked(int index) {

                    //its run on behaf of index value
                    if (index==0){
                        Intent acc= new Intent(mcollection.this, Account.class);
                        startActivity(acc);
                    }
                    else if(index==1){
                        Intent col= new Intent(mcollection.this,mcollection.class);
                        startActivity(col);
                    }
                    else if(index==2){
                        Intent rec = new Intent(mcollection.this,mrecord.class);
                        startActivity(rec);
                    }
                    else if (index==3) {
                        Intent  report = new Intent(mcollection.this, mreport.class);
                        startActivity(report);
                    }
                    else if (index==4){
                        Intent alert = new Intent(mcollection.this,malert.class);
                        startActivity(alert);
                    }
                    else if (index==5){
                        Intent contact = new Intent(mcollection.this , Contact.class);
                        startActivity(contact);
                    }
                    else if (index==6) {
                        Intent abt= new Intent(mcollection.this , About.class);
                        startActivity(abt);
                    }
                    else if (index==7){
                        Intent how = new Intent( mcollection.this , howto.class );
                        startActivity(how);
                    }

                }
            });

            if (fab != null) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sub.toggleShow();
                    }
                });
            }
        }


    }

    public void singlecollection(View view) {
        Intent j=new Intent(mcollection.this,map_single_collection.class);
        startActivity(j);
    }

    public void multicollection(View view) {
        Intent i=new Intent(mcollection.this,multiplecollection.class);
        startActivity(i);

    }


    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, NotifyMessage.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Smart Waste Collection")
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Smart Waste Collection")
                .setContentText("Waste reach the High Level please collect waste immediately")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
