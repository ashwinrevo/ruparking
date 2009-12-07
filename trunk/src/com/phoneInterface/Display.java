package com.phoneInterface;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
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

/**
 * This class displays  a text field for the user to provide the destination address to the system.
 * The text field takes an input of type String. The input from the user is the parameter sent to the MapDisplay
 * It belongs to the phone interface subsystem which is the interface between 
 * the user and the application.
 * 
 * @version 1.0
 */
//This class handles the display functions required to display contents on the screen.

public class Display extends Activity {
	public static GeoPoint point, p1, p2;
	EditText addressfield;
	
	public Display(){
		
	}
		
    /* Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	final Geocoder geoCoder = new Geocoder(this, Locale.getDefault()); 
   
// This is the Submit button used to confirm the text entered in the address field
    	
        Button submit = (Button) findViewById(R.id.Button01);
        submit.setOnClickListener(new View.OnClickListener() {
          
        	public void onClick(View view) {
            	try {
            			addressfield=(EditText)findViewById(R.id.entry);
            		   List<Address> addresses = geoCoder.getFromLocationName(addressfield.getText().toString(),5);
                       if (addresses.size() > 0) {
                            p1 = new GeoPoint(
                                   (int) (addresses.get(0).getLatitude() * 1E6), 
                                   (int) (addresses.get(0).getLongitude() * 1E6));                          
                       }    
                              } catch (IOException e) {
                       e.printStackTrace();
                   }               
                point=p1;
                Intent myIntent = new Intent(Display.this, MapDisplay.class);
                Display.this.startActivity(myIntent);
            }
        });

// This is the "Park at Current Location" button used to obtain coordinates of the current location
       
        Button cur_loc = (Button) findViewById(R.id.Button02);
        cur_loc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
                Location loc = lm.getLastKnownLocation("gps"); 
                double latpoint=loc.getLatitude();
                double longpoint=loc.getLongitude();
                int lat=(int)(latpoint*1E6);
                int lon=(int)(longpoint*1E6);
                p2 = new GeoPoint(lat, lon);
                point=p2;
                Intent myIntent = new Intent(Display.this, MapDisplay.class);
                Display.this.startActivity(myIntent);
            }
        });

        
    }
}