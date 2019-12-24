package com.example.computer.smartwastecollection;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bcgdv.asia.lib.fanmenu.FanMenuButtons;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class multiplecollection extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        Mapbox.getInstance(this, getString(R.string.access_token));


        setContentView(R.layout.activity_multiplecollection);


        //menu
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myFAB);
        final FanMenuButtons sub = (FanMenuButtons) findViewById(R.id.myFABSubmenu);
        if (sub != null) {
            sub.setOnFanButtonClickListener(new FanMenuButtons.OnFanClickListener() {
                @Override
                public void onFanButtonClicked(int index) {

                    //its run on behaf of index value
                    if (index==0){
                        Intent acc= new Intent(multiplecollection.this, Account.class);
                        startActivity(acc);
                    }
                    else if(index==1){
                        Intent col= new Intent(multiplecollection.this,mcollection.class);
                        startActivity(col);
                    }
                    else if(index==2){
                        Intent rec = new Intent(multiplecollection.this,mrecord.class);
                        startActivity(rec);
                    }
                    else if (index==3) {
                        Intent  report = new Intent(multiplecollection.this, mreport.class);
                        startActivity(report);
                    }
                    else if (index==4){
                        Intent alert = new Intent(multiplecollection.this,malert.class);
                        startActivity(alert);
                    }
                    else if (index==5){
                        Intent contact = new Intent(multiplecollection.this , Contact.class);
                        startActivity(contact);
                    }
                    else if (index==6) {
                        Intent abt= new Intent(multiplecollection.this , About.class);
                        startActivity(abt);
                    }
                    else if (index==7){
                        Intent how = new Intent( multiplecollection.this , howto.class );
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

        //google maps
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                // Create an Icon object for the marker to use
                Icon icon = IconFactory.getInstance(multiplecollection.this).fromResource(R.drawable.icon1);

                // Add the custom icon marker to the map


                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.744683, 72.779875))
                        .title("Smart Bin 3 Nawababad")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));






                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.747154, 72.786279))
                        .title("Smart Bin 5 Near Wah Medical College")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));


                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.741587, 72.787223))
                        .title("Smart Bin 6 On GT Road")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));


                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.742550, 72.784305))
                        .title("Smart Bin 7  Near Bombay Biryani")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.746397, 72.787062))
                        .title("Smart Bin 2 Near Comsats Wah cantt")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));


                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.740873, 72.788682))
                        .title("Smart Bin 4 Near University Of Wah")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));


                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(33.749830, 72.783146))
                        .title("Smart Bin 1 Near Pof Hospital")
                        .snippet(getString(R.string.draw_custom_marker_options_snippet))
                        .icon(icon));


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
