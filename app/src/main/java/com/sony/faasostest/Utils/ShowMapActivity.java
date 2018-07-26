package com.sony.faasostest.Utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sony.faasostest.MainActivity;
import com.sony.faasostest.Model.Address;
import com.sony.faasostest.Model.Geo;
import com.sony.faasostest.R;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ShowMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Disposable disposable2;
    String lat,longitude;
    Toolbar toolbar;
    TextView address;
    ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        toolbar=findViewById(R.id.toolbar);
        address=findViewById(R.id.address);
        home=toolbar.findViewById(R.id.home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Address Location");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowMapActivity.this, MainActivity.class);
// set the new task and clear flags
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        eventListener();
        mapFragment.getMapAsync(this);
    }

    private void eventListener() {
        disposable2=RXBus.getSubject2().subscribeWith(new DisposableObserver<Object>() {
            @Override
            public void onNext(Object o) {
                if(o instanceof Address)
                {
                    address.setText(getString(R.string.address,((Address) o).street,((Address) o).suite,
                            ((Address) o).city,((Address) o).zipcode));

                  Geo geo=((Address) o).geo;
                  lat=geo.lat;
                  longitude=geo.lng;
                  System.out.println(lat+longitude);

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng place = new LatLng(Double.parseDouble(lat),Double.parseDouble(longitude));

        mMap.addMarker(new MarkerOptions().position(place));
        moveToCurrentLocation(place);

    }
    private void moveToCurrentLocation(LatLng currentLocation)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


    }
    @Override
    protected void onDestroy() {
        disposable2.dispose();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
