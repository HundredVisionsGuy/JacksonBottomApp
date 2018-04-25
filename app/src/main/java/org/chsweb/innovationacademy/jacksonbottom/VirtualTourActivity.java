package org.chsweb.innovationacademy.jacksonbottom;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Demonstrates the different base layers of a map.
 */
public class VirtualTourActivity extends AppCompatActivity
        implements GoogleMap.OnMarkerClickListener, OnItemSelectedListener, OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap mMap;

    private String marker_title, marker_description;
    private TextView text_marker_title, text_marker_description;
    private WebView webView;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mShowPermissionDeniedDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_tour);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        text_marker_title = (TextView) findViewById(R.id.text_marker_title);

        // initialize webView
        webView = (WebView) findViewById(R.id.wv_marker_description);

        // Display directions in webView
        String wv_text = getResources().getString(R.string.wv_marker_description);
        webView.loadData(wv_text, "text/html", null);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(20.0f);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);
/*
    To reference marker click listeners, see
    https://github.com/googlemaps/android-samples/blob/master/ApiDemos/java/app/src/main/java/com/example/mapdemo/MarkerDemoActivity.java

 */
        try {
            LatLng jackson_bottom = new LatLng(45.5007, -122.9903);
            LatLng upland_ponds = new LatLng(45.50023, -122.9895);
            LatLng mason_bee_boxes = new LatLng(45.500105, -122.98923);
            LatLng overlook_fence = new LatLng(45.49982,-122.9866);
            // TODO: add remaining LatLngs

            mMap.addMarker(new MarkerOptions().position(jackson_bottom).
                    title(getString(R.string.marker_jackson_main)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(new MarkerOptions().position(upland_ponds).
                    title(getString(R.string.marker_upland_ponds)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(new MarkerOptions().position(mason_bee_boxes).
                    title(getString(R.string.marker_bee_boxes)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.addMarker(new MarkerOptions().position(overlook_fence).
                    title(getString(R.string.marker_overlook_fence)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            // TODO: add remaining markers

            mMap.moveCamera(CameraUpdateFactory.newLatLng(jackson_bottom));



        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = e.getMessage();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mShowPermissionDeniedDialog) {
            PermissionUtils.PermissionDeniedDialog
                    .newInstance(false).show(getSupportFragmentManager(), "dialog");
            mShowPermissionDeniedDialog = false;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateMapType();
    }

    private void updateMapType() {
        // No toast because this can also be called by the Android framework in onResume() at which
        // point mMap may not be ready yet.
        if (mMap == null) {
            return;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing.
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        try {
            if (marker.getTitle().equals(getString(R.string.marker_jackson_main))) {
                // Set the title
                text_marker_title.setText(R.string.marker_jackson_main);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.marker_jackson_main) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            } else if (marker.getTitle().equals(getString(R.string.marker_bee_boxes))) {
                // Set the title
                text_marker_title.setText(R.string.marker_bee_boxes);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.snippet_upland_ponds) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            }  else if (marker.getTitle().equals(getString(R.string.marker_upland_ponds))) {
                // Set the title
                text_marker_title.setText(R.string.marker_upland_ponds);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.snippet_upland_ponds) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            }
            else {
                // Set the title
                text_marker_title.setText(R.string.text_marker_title_error);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.text_marker_description_error) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            }
        }
        catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = e.getMessage();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /*
        We'll clean this up in a bit. For reference, go to
        https://github.com/googlemaps/android-samples/blob/master/ApiDemos/java/app/src/main/java/com/example/mapdemo/MarkerDemoActivity.java*/
    @Override
    public boolean onMarkerClick(final Marker marker) {
        Context context = getApplicationContext();
        String msg = "we have a marker click from " + marker.getTitle().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
        try {
            if (marker.getTitle().equals(getString(R.string.marker_jackson_main))) {
                // Set the title
                text_marker_title.setText(R.string.marker_jackson_main);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.marker_jackson_main) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            } else if (marker.getTitle().equals(getString(R.string.marker_bee_boxes))) {
                // Set the title
                text_marker_title.setText(R.string.marker_bee_boxes);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.snippet_bee_boxes) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            } else if (marker.getTitle().equals(getString(R.string.marker_upland_ponds))) {
                // Set the title
                text_marker_title.setText(R.string.marker_upland_ponds);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.snippet_upland_ponds) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            }
            else {
                // Set the title
                text_marker_title.setText(R.string.text_marker_title_error);

                // Set the text as HTML
                String wv_text = "<html><body>" + getString(R.string.text_marker_description_error) + "</body></html>";
                //text_marker_description.setText(R.string.textview_welcome_main);
                webView.loadData(wv_text, "text/html", null);
            }
        }
        catch (Exception e) {
            context = getApplicationContext();
            CharSequence text = e.getMessage();
            duration = Toast.LENGTH_LONG;
            toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return true;
    }
}