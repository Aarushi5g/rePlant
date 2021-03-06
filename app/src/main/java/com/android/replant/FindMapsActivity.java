package com.android.replant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindMapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    /*
    Reference: Book: 'Android Studio 3.0 Development Essentials' by Neil Smyth
    this book was used as a guide for implementing the Google Maps into this application
     */

    private static final int LOCATION_REQUEST_CODE = 101;
    private GoogleMap mMap;
    private String treeLocation;
    private Button plantHere_btn;
    private Marker mMassey;
    private Marker mTicnock;
    private Marker mPhoenix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //change colour of status bar
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.main));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button button = findViewById(R.id.plantHere_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ConfirmationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //set markers on map
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null)   {
            //Massey Woods Marker
            LatLng masseyWoods = new LatLng(53.2539026, -6.3232363);
            LatLng tickNock = new LatLng(53.2556435,-6.2532698);
            LatLng phoenix = new LatLng(53.3558823,-6.332002);
            mMap.addMarker(new MarkerOptions()
                    .position(masseyWoods)
                    .title("Massey Woods"));
            // Add some markers to the map, and add a data object to each marker.
            mMassey = mMap.addMarker(new MarkerOptions()
                    .position(masseyWoods)
                    .title("Perth"));
            mMassey.setTag(0);

            mTicnock = mMap.addMarker(new MarkerOptions()
                    .position(tickNock)
                    .title("Ticknock Forest"));
            mTicnock.setTag(0);

            mPhoenix = mMap.addMarker(new MarkerOptions()
                    .position(phoenix)
                    .title("Phoenix Park"));
            mPhoenix.setTag(0);

            // Set a listener for marker click.
            mMap.setOnMarkerClickListener(this);

            // set Dublin lat lng
            LatLng dublin = new LatLng(53.3498, -6.2603);
            //move camera to dublin and zoom 10f
            CameraUpdate point = CameraUpdateFactory.newLatLngZoom(dublin, 10f);
            mMap.moveCamera(point);
            mMap.animateCamera(point);
            // User's Location
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (permission == PackageManager.PERMISSION_GRANTED)    {
                // enable my location function if permsion is accepted
                mMap.setMyLocationEnabled(true);
            }   else{
                // Move to default location
                mMap.moveCamera(point);
                mMap.animateCamera(point);
                // Request permission
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_REQUEST_CODE);
            }
        }

    }
    //Request users permission to user their Location.
    //Reference from book.
    protected void requestPermission(String permissionType, int requestCode){
        ActivityCompat.requestPermissions(this, new String[]{permissionType},requestCode);

    }

    //If permisison accepted map is refreshed. If not a pop up message is displayed
    // Reference from book.
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                // if permission is denied
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,
                            "Default location used", Toast.LENGTH_SHORT).show();

                } else {
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(9f));
                }
            }
        }
    }
    /* Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        //When marker is clicked set button to be visible
        plantHere_btn = findViewById(R.id.plantHere_btn);
        plantHere_btn.setVisibility(View.VISIBLE);
        // Retrieve the data from the marker.
        treeLocation = (String) marker.getTitle();
        // Display message with location user has picked
        Toast.makeText(this, "Location selected: " + treeLocation, Toast.LENGTH_SHORT).show();

        return false;
    }
    // Plant here button which takes users to purchase page
    public void toPurchase(View view) {
//        Intent intent = new Intent(MapsActivity.this,PurchaseActivity.class);
//        intent.putExtra("treeLocation", treeLocation);
//        this.startActivity(intent);
    }
}
