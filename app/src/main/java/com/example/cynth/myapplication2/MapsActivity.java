package com.example.cynth.myapplication2;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerDragListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    double latitud, longitud;
    private Marker marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        MainActivity mA = new MainActivity();

       // mA.ubicacionTelefono();

        latitud = MainActivity.latitud;
        longitud = MainActivity.longitud;

        // Add a marker in Sydney and move the camera
        final LatLng ubActual = new LatLng(latitud, longitud);
        Marker mk = googleMap.addMarker(new MarkerOptions()
                .position(ubActual)
                .title("marcador movible")
                .draggable(true));

        mMap.addMarker(new MarkerOptions().position(ubActual).title("ubicacion del telefono"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubActual, 20));

        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);

        FloatingActionButton Fb = (FloatingActionButton) findViewById(R.id.fAB);

        Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();

                marcador = googleMap.addMarker(new MarkerOptions().position(ubActual).draggable(true));
                

            }
        });


    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        if(marker.equals(marcador)){

            Toast.makeText(this, "" + latitud + longitud, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if(marker.equals(marcador)){

            latitud = marker.getPosition().latitude;
            longitud = marker.getPosition().longitude;
            Toast.makeText(this, "" + latitud + longitud, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(marcador)){

            Toast.makeText(this, "" + latitud + longitud, Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
