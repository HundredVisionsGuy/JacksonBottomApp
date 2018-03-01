package org.chsweb.innovationacademy.jacksonbottom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="abc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get list of rules to display on the ListView
        ListView rulesView = (ListView) findViewById(R.id.rules_list_view);
        Resources res = getResources();
        String[] rules = res.getStringArray(R.array.Rules);

        // Display list of rules on ListView (rulesView)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rules);

        rulesView.setAdapter(adapter);

        // Set our Buttons
        final Button button_launch_map = (Button) findViewById(R.id.button_launch_map_activity);
        button_launch_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Launch Map Fragment
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        final Button button_launch_tile_overlay = (Button) findViewById(R.id.button_launch_tile_overlay_activity);
        button_launch_tile_overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch Tile Overlay
                try {
                    Intent intent = new Intent(view.getContext(), TileOverlayDemoActivity.class);
                    startActivity(intent);
                }
                catch (Exception ex) {
                    Log.v(TAG, "We have a problem Houston: " + ex.toString());
                }
            }
        });
        try {
            final Button button_launch_layers_demo = (Button) findViewById(R.id.button_launch_layers_demo_activity);
            button_launch_layers_demo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Launch Layers Demo
                    try {
                        Intent intent = new Intent(v.getContext(), LayersDemoActivity.class);
                        startActivity(intent);
                    } catch (Exception ex) {
                        Log.v(TAG, "We have a problem Houston: " + ex.toString());
                    }
                }
            });
        }
        catch (Exception ex) {
            Log.v(TAG, "launch_layers has a problem" + ex.toString());
        }


        final Context context = this;
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            Log.v(TAG, "gps enabled");
        } catch(Exception ex) {
            Log.v(TAG, "gps fail!!!!!");
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {
            Log.v(TAG, "network enabled failed!");
        }

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(context.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(context.getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

    }

}
