package com.iac.joinme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    String eventName;
    TextView T,C;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle B = getIntent().getExtras();
        if(B!=null){
            T = (TextView)findViewById(R.id.eventTitle);
            T.setText(B.getString("Event"));
            C = (TextView)findViewById(R.id.smallCatTitle);
            C.setText(B.getString("Categorie"));
            eventName=B.getString("Event");
        }else{
            Log.e("Error With Intent","No Extras");
        }
        Button photos = (Button) findViewById(R.id.photos);
        Button videos = (Button) findViewById(R.id.videos);
        Button other_button = (Button) findViewById(R.id.other);

        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhotosActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(MainActivity.this, VideosActivity.class);
                  MainActivity.this.startActivity(intent);
              }
          });

        other_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng current = new LatLng(-34,151);
        mMap.addMarker(new MarkerOptions().position(current).title("Current position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
    }
}
