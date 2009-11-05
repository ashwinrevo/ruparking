package com.phoneInterface;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.mapManagement.Mapper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Display extends Activity {
	public static GeoPoint point, p1, p2;
	EditText ad;
	
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	final Geocoder geoCoder = new Geocoder(this, Locale.getDefault()); 
   

        Button next1 = (Button) findViewById(R.id.Button01);
        next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	try {
            			ad=(EditText)findViewById(R.id.entry);
            		   List<Address> addresses = geoCoder.getFromLocationName(ad.getText().toString(),5);
                       if (addresses.size() > 0) {
                            p1 = new GeoPoint(
                                   (int) (addresses.get(0).getLatitude() * 1E6), 
                                   (int) (addresses.get(0).getLongitude() * 1E6));                          
                       }    
                              } catch (IOException e) {
                       e.printStackTrace();
                   }               
                point=p1;
                Intent myIntent = new Intent(Display.this, Mapper.class);
                Display.this.startActivity(myIntent);
            }
        });
        
        Button next2 = (Button) findViewById(R.id.Button02);
        next2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
                Location loc = lm.getLastKnownLocation("gps"); 
                
                double latpoint=loc.getLatitude();
                double longpoint=loc.getLongitude();
                int lat=(int)(latpoint*1E6);
                int lon=(int)(longpoint*1E6);

                p2 = new GeoPoint(lat, lon);
                point=p2;
                Intent myIntent = new Intent(Display.this, Mapper.class);
                Display.this.startActivity(myIntent);
            }
        });
        
    }
}