package com.example.computer.smartwastecollection;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;




import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.content.Context;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;


public class HomeScreen extends AppCompatActivity {


    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Previous versions of Firebase
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_home_screen);




        RelativeLayout relative = (RelativeLayout) findViewById(R.id.home);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        relative.startAnimation(myAnim);



        TextView wlcm= (TextView) findViewById(R.id.welcome);


        TextView mtc= (TextView) findViewById(R.id.mtextcollection);
        TextView mtrecord= (TextView) findViewById(R.id.mtextrecord);
        TextView mta= (TextView) findViewById(R.id.mtextalert);
        TextView mtreport= (TextView) findViewById(R.id.mtextreport);
        TextView mtcontact= (TextView) findViewById(R.id.mtextcontact);
        TextView mtd= (TextView) findViewById(R.id.mtextdp);
        Typeface drivernote = Typeface.createFromAsset(getAssets(),  "fonts/SEASRN.ttf");
        wlcm.setTypeface(drivernote);
        mtc.setTypeface(drivernote);
        mtrecord.setTypeface(drivernote);
        mta.setTypeface(drivernote);
        mtreport.setTypeface(drivernote);
        mtcontact.setTypeface(drivernote);
        mtd.setTypeface(drivernote);



        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
       /// banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.slider_1));
        banners.add(new DrawableBanner(R.drawable.slider_2));
        banners.add(new DrawableBanner(R.drawable.slider_3));
        banners.add(new DrawableBanner(R.drawable.slider_4));
        banners.add(new DrawableBanner(R.drawable.slider_8));
        banners.add(new DrawableBanner(R.drawable.slider_9));
        bannerSlider.setBanners(banners);





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


    public void collection(View view) {
        Intent i=new Intent(HomeScreen.this,mcollection.class);
        startActivity(i);
    }

    public void report(View view) {
        Intent i=new Intent(HomeScreen.this,mreport.class);
        startActivity(i);
    }

    public void record(View view) {
        Intent i=new Intent(HomeScreen.this,mrecord.class);
        startActivity(i);
    }

    public void alert(View view) {

        Intent i=new Intent(HomeScreen.this,malert.class);
        startActivity(i);


    }

    public void contact(View view) {
        Intent i=new Intent(HomeScreen.this,Contact.class);
        startActivity(i);
    }

    public void profile(View view) {
        Intent i=new Intent(HomeScreen.this,Account.class);
        startActivity(i);
    }

    public void howto(View view) {
        Intent i=new Intent(HomeScreen.this,howto.class);
        startActivity(i);
    }

    public void about(View view) {
        Intent i=new Intent(HomeScreen.this,About.class);
        startActivity(i);
    }

}
