package com.example.firstresponderlocation;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;
//import com.example.location_finder2.R;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements LocationListener{
    //private TextView textView;
    private LocationManager locationManager;
    private Button mSendData;
    //private Firebase mRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        //mRef = new Firebase("https://emslocation-c72e3.firebaseio.com/");
        mSendData = findViewById(R.id.button);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //textView = (TextView)findViewById(R.id.id_textview);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
    }

    @Override
    public void onLocationChanged(Location location){
        final double longitude = location.getLongitude();
        final double latitude = location.getLatitude();
        //textView.setText("Longitude" + longitude + "\n" + "Latitude" + latitude);
        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Firebase mRefChild = mRef.child("latitude");
                //mSendData.setValue(latitude);
                mDatabase.child("latitude").setValue(latitude);
                mDatabase.child("longitude").setValue(longitude);
            }
        });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}