package org.chsweb.innovationacademy.jacksonbottom;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);

        // Add a marker in Jackson Bottom and move the camera
        try {
            LatLng jackson_bottom = new LatLng(45.5007, -122.9903);
            LatLng upland_ponds = new LatLng(45.50023, -122.9895);
            LatLng mason_bee_boxes = new LatLng(45.500105, -122.98923);
            LatLng east_side_trail = new LatLng(45.50075, -122.9894);
            LatLng intersection = new LatLng(45.50082, -122.9894);

            mMap.addMarker(new MarkerOptions().position(jackson_bottom).
                    title(getString(R.string.marker_jackson_main)).
                    snippet("Hey, I'm a snippet. Yo!"));
            mMap.addMarker(new MarkerOptions().position(upland_ponds).
                    title(getString(R.string.marker_upland_ponds)).
                    snippet(getString(R.string.snippet_upland_ponds)));
            mMap.addMarker(new MarkerOptions().position(mason_bee_boxes).
                    title(getString(R.string.marker_bee_boxes)).
                    snippet(getString(R.string.snippet_bee_boxes)));
            mMap.addMarker(new MarkerOptions().position(east_side_trail).
                    title(getString(R.string.east_side_trail)).
                    snippet(getString(R.string.snippet_east_side)));
            mMap.addMarker(new MarkerOptions().position(intersection).
                    title(getString(R.string.intersection)).
                    snippet(getString(R.string.snippet_intersection)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(jackson_bottom));
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = e.getMessage();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
}
