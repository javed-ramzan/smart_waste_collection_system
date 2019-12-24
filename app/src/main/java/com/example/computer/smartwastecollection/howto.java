package com.example.computer.smartwastecollection;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


public class howto extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);



        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
        /// banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.slide1));
        banners.add(new DrawableBanner(R.drawable.slide2));
        banners.add(new DrawableBanner(R.drawable.slide3));
        banners.add(new DrawableBanner(R.drawable.slide4));
        banners.add(new DrawableBanner(R.drawable.slide5));
        bannerSlider.setBanners(banners);



    }

}