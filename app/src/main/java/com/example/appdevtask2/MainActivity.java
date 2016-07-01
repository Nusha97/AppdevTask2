package com.example.appdevtask2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    public int currentimageindex = 0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;


    private int[] IMAGE_IDS = {
            R.drawable.images, R.drawable.cliffs, R.drawable.trees,
            R.drawable.streams
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("payphone");
        categories.add("demons");
        categories.add("itstime");
        categories.add("afterhours");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
        //final Button button = (Button) findViewById(R.id.slideshow);

            public void Slideshow (View view){
                display();

            }

        /**
         * Helper method to start the animation on the splash screen
         */
        private void AnimateandSlideShow() {

            slidingimage = (ImageView) findViewById(R.id.image1);
            slidingimage.setImageResource(IMAGE_IDS[currentimageindex % IMAGE_IDS.length]);

            currentimageindex++;

            Animation rotateimage = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);


            slidingimage.startAnimation(rotateimage);


        }

    public void Play(View view){

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.payphone);
        mediaPlayer.start();

    }

    public  void Stop(View view){

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.payphone);
        mediaPlayer.pause();
    }
    public void display(){
    final Handler mHandler = new Handler();
    // Create runnable for posting
    final Runnable mUpdateResults = new Runnable() {

        public void run() {

            AnimateandSlideShow();
        }

    };

        int delay = 1000; // delay for 1 sec.

        int period = 5000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);
    }


}


