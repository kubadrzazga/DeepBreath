package com.example.deepbreath;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.deepbreath.fetchData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static HashMap<Integer, LatLng> coordinate = new HashMap<>();
    private static HashMap<Integer,String> Params = new HashMap<>();
    private static HashMap<Integer,ArrayList<Integer>> Sensors = new HashMap<>();

    public static void setCoordinate(HashMap<Integer,LatLng> H){coordinate = H;}
    public static void setParams(HashMap<Integer,String> H){Params = H;}
    public static void setSensors(HashMap<Integer,ArrayList<Integer>> H){Sensors = H;}


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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(Map.Entry<Integer, LatLng> entry : coordinate.entrySet()) {
            Integer key = entry.getKey();
            LatLng value = entry.getValue();
            String temp="";
            for (Integer IntI: Objects.requireNonNull(Sensors.get(key))) {
                temp+=IntI.toString();
            }
            mMap.addMarker(new MarkerOptions().position(value).title(key.toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.cloud)).snippet(temp));


        }
        LatLng Poland= new LatLng(52.069272,19.480250);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Poland));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));

    }

}
