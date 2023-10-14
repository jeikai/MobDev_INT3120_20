package com.example.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView address;
    Button getLocation;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        address = findViewById(R.id.address);
        getLocation = findViewById(R.id.btn_location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
    }

    private void getLastLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> addressList = null;
                            try {
                                addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                address.setText("Address: " + addressList.get(0).getAddressLine(0));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                });
    }

}