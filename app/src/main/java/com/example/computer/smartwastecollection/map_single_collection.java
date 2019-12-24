package com.example.computer.smartwastecollection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bcgdv.asia.lib.fanmenu.FanMenuButtons;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.tallygo.tallygoandroid.activities.turnbyturn.TGTurnByTurnActivity;
import com.tallygo.tallygoandroid.fragments.navigation.base.TGBaseTurnByTurnFragment;
import com.tallygo.tallygoandroid.sdk.navigation.TGRouteRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.pawelkleczkowski.customgauge.CustomGauge;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;




public class map_single_collection extends AppCompatActivity  {


    private TextView garbage;
    private Firebase mRef;
    private CustomGauge gauge2;


    int i;
    private TextView text1;
    private TextView text2;




    NiftyDialogBuilder materialDesignAnimatedDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Previous versions of Firebase
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_map_single_collection);


        LinearLayout smc = (LinearLayout) findViewById(R.id.ll);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        smc.startAnimation(myAnim);


        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this);



       // ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar1); // initiate the progress bar
        //simpleProgressBar.setMax(100); // 100 maximum value for the progress value
       // simpleProgressBar.setProgress(50); // 50 default progress value for the progress bar




        //guage set values

       // gauge2.setEndValue(700);

       // text2.setText(Integer.toString(gauge2.getValue()));
       // text2.setText(Integer.toString(gauge2.getValue()));


//        gauge2.setValue(200);
       // text2.setText(Integer.toString(gauge2.getEndValue()));




      //retrive data from firebase
        garbage = (TextView) findViewById(R.id.progress_circle_text1);




        mRef = new Firebase("https://smart-waste-collection-e2735.firebaseio.com/number");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                String value= dataSnapshot.getValue().toString();



                Double finalValue=Double.parseDouble(value);
                Double val;
                val = (1-(finalValue/29))*100;

                if(finalValue <= 29 && finalValue >= 0  )
                {

                    // int valu = (500 / value)*100;

                    ProgressBar simpleProgressBar=(ProgressBar)findViewById(R.id.progressBar1); // initiate the progress bar
                    simpleProgressBar.setMax(100); // 100 maximum value for the progress value
                    Double finalValue1=Double.parseDouble(value);
                    Double val1;
                    val1 = (1-(finalValue1/29))*100;
                    simpleProgressBar.setProgress((int) Double.parseDouble(String.valueOf(val1))); // 50 default progress value for the progress bar


                    garbage.setText(String.valueOf(val));
                    Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/digit.ttf");
                    garbage.setTypeface(custom_font);



                }
                else {

                    //do nothing
                }


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
                        Intent acc= new Intent(map_single_collection.this, Account.class);
                        startActivity(acc);
                    }
                    else if(index==1){
                        Intent col= new Intent(map_single_collection.this,mcollection.class);
                        startActivity(col);
                    }
                    else if(index==2){
                        Intent rec = new Intent(map_single_collection.this,mrecord.class);
                        startActivity(rec);
                    }
                    else if (index==3) {
                        Intent  report = new Intent(map_single_collection.this, mreport.class);
                        startActivity(report);
                    }
                    else if (index==4){
                        Intent alert = new Intent(map_single_collection.this,malert.class);
                        startActivity(alert);
                    }
                    else if (index==5){
                        Intent contact = new Intent(map_single_collection.this , Contact.class);
                        startActivity(contact);
                    }
                    else if (index==6) {
                        Intent abt= new Intent(map_single_collection.this , About.class);
                        startActivity(abt);
                    }
                    else if (index==7){
                        Intent how = new Intent( map_single_collection.this , howto.class );
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


    private void startTurnByTurn() {
        //you can use this line to get your current location if you would like
        // if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        //   // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        // return;
        //  }
        // LatLng currentLocation = TGUtils.getRealCurrentCoordinate(getBaseContext());

        // LatLng currentLocation = new LatLng(33.742006, 72.782900); //Hotel Del Coronado
        // LatLng destinationCoordinate = new LatLng(33.745284,72.786472); //SD Zoo



        LatLng currentLocation = new LatLng(32.6809, -117.1784); //Hotel Del Coronado
        LatLng destinationCoordinate = new LatLng(32.7306181,-117.1462286); //SD Zoo

        List<LatLng> waypoints = new ArrayList<>();
        waypoints.add(currentLocation);
        waypoints.add(destinationCoordinate);

        //current date
        Date date = new Date();

        //create the request with the date/time supplied as the departure time
        TGRouteRequest routeRequest = new TGRouteRequest(waypoints, date,
                TGRouteRequest.TGRouteRequestType.DEPARTURE_TIME);

        //create options to pass to the turn-by-turn activity
        TGBaseTurnByTurnFragment.Options options = new TGBaseTurnByTurnFragment.Options();
        options.putRouteRequest(routeRequest);

        //we simulate the route, so you don't have to drive to test it
        options.putSimulated(true);

        //also try this if you would like to control how the simulation moves
        //options.putClickToUpdate(true);

        Intent intent = new Intent(this, TGTurnByTurnActivity.class);
        intent.putExtra(TGBaseTurnByTurnFragment.Options.TG_BASE_TURN_BY_TURN_OPTIONS_KEY, options);
        startActivity(intent);


    }


    public void animatedDialog1(View view) {
        materialDesignAnimatedDialog
                .withTitle("Bin 1")
                .withMessage("Location:Comsats Wah cantt. \nClick on start button if you want to collect the garbage.")
                .withDialogColor("#00cccc")
                .withButton1Text("Start")
                .withButton2Text("Cancel")
                .withDuration(700)
                .withEffect(Effectstype.Fall)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //tallugo api
                        startTurnByTurn();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDesignAnimatedDialog.cancel();
                    }
                })
                .show();


    }



    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
