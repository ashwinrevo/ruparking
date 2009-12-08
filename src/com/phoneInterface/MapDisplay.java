package com.phoneInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.clientData.SensorData;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.mapManagement.Mapper;
import com.navigationManagement.Navigator;
import com.phoneInterface.R;
/**
 * This class communicates with the clientData package and display.java to get relevant data to be displayed.
 * The coordinates fetched from the Sensordata are overlayed as drawables on the map
 * It belongs to the phone interface subsystem which is the interface between 
 * the user and the application.
 *
 * @version 1.0
 */
public class MapDisplay extends MapActivity{

	List<Overlay> mapOverlays;
	Drawable drawable1, drawable3;
	Drawable drawable2,drawable4;
	Mapper itemizedOverlay1;
	Mapper itemizedOverlay2;
	Mapper itemizedOverlay3;
	Mapper itemizedOverlay4;
	LinearLayout linearLayout;
	public static MapView mapView;
	ZoomControls mZoom;
	OverlayItem overlayitem;
	MapController mc;
	public ArrayList<GeoPoint> listOfGeopoints=new ArrayList<GeoPoint>();
	Location location;
	LocationManager locationManager;
	ArrayList<String> color_initial=new ArrayList<String>();
	ArrayList<String> latitude_initial=new ArrayList<String>();
	ArrayList<String> longitude_initial=new ArrayList<String>();
	public ArrayList<Integer> color=new ArrayList<Integer>();
	public ArrayList<Integer> latitude=new ArrayList<Integer>();
	public ArrayList<Integer> longitude=new ArrayList<Integer>();
	int a;
	public static GeoPoint dest_p, srcGeoPoint;


	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map); 

		SensorData coor_info=new SensorData();
		coor_info.initialize();

		color_initial=coor_info.read;
		latitude_initial=coor_info.latitude_coor;
		longitude_initial=coor_info.longitude_coor;

		for (int i=0; i<color_initial.size()-1; i++){
			int temp_color=Integer.parseInt(color_initial.get(i));
			int temp_latitude=Integer.parseInt(latitude_initial.get(i));
			int temp_longitude=Integer.parseInt(longitude_initial.get(i));

			//some code has to come to create the color matrix
			if (temp_color==255){
				int[] sensor_values={100,75};
				Random generator= new Random();
				int randomIndex = generator.nextInt(2);
				color.add(sensor_values[randomIndex]);
			}
			else{
				int[] sensor_values={50,25};
				Random generator= new Random();
				int randomIndex = generator.nextInt(2);
				color.add(sensor_values[randomIndex]);
			}

			latitude.add((temp_latitude));
			longitude.add((temp_longitude));
		}

		for (int i=0;i<color_initial.size()-1;i++){
			listOfGeopoints.add(new GeoPoint(latitude.get(i), longitude.get(i)));
		}



		OverlayItem overlayitem = new OverlayItem(com.phoneInterface.Display.point, "", "");
		linearLayout = (LinearLayout) findViewById(R.id.zoomview);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setStreetView(true);
		mc=mapView.getController();
		mc.animateTo(com.phoneInterface.Display.point);
		int zl = 17;
		a=zl;
		mc.setZoom(a);
		mZoom = (ZoomControls) mapView.getZoomControls();
		linearLayout.addView(mZoom);
		mapOverlays = mapView.getOverlays();

		drawable1 = this.getResources().getDrawable(R.drawable.green);
		itemizedOverlay1 = new Mapper(drawable1);

		drawable2 = this.getResources().getDrawable(R.drawable.red);
		itemizedOverlay2 = new Mapper(drawable2);

		for (int index=0;index<listOfGeopoints.size();index++){
			if (color.get(index)==100){
				overlayitem = new OverlayItem(listOfGeopoints.get(index), "", "");
				itemizedOverlay1.addOverlay(overlayitem);}

			else {
				overlayitem = new OverlayItem(listOfGeopoints.get(index), "", "");
				itemizedOverlay2.addOverlay(overlayitem);}
		}

		mapOverlays.add(itemizedOverlay1);
		mapOverlays.add(itemizedOverlay2);


		// This is the "Change View" Button
		Button chng_view = (Button) findViewById(R.id.Button03);
		chng_view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (a == 17) {
					int zl = 18;
					a=zl;
				} else 
				{if (a == 18) {
					int zl = 14;
					a=zl;
				} else 
				{
					int zl = 17;
					a=zl;
				} 
				} 
				mc.setZoom(a);
			}
		});

		Button updateB = (Button) findViewById(R.id.Button05);
		  updateB.setOnClickListener(new View.OnClickListener() {
		      public void onClick(View view) {
		    	  Intent myIntent2 = new Intent (MapDisplay.this, MapDisplay.class);
		    	  MapDisplay.this.startActivity(myIntent2);  
	//	    	  Update up_date = new Update();
		          }
		  
		  });

		//This is the "Navigate" Button
		Button nav = (Button) findViewById(R.id.Button04);
		nav.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				mapView.getOverlays().clear();
				LocationManager lm1 = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
				Location loc1 = lm1.getLastKnownLocation("gps"); 
				double latpoint=loc1.getLatitude();
				double longpoint=loc1.getLongitude();
				int lat=(int)(latpoint*1E6);
				int lon=(int)(longpoint*1E6);
				srcGeoPoint=new GeoPoint(lat,lon);
				Navigator nav_map=new Navigator();
				nav_map.draw_route();
				//Intent myIntent1 = new Intent(MapDisplay.this, Navigator.class);
				//MapDisplay.this.startActivity(myIntent1);     
			}

		});
		
		mapView.setOnTouchListener(new MapView.OnTouchListener() { 
            @Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
            	dest_p = mapView.getProjection().fromPixels(
    					(int) event.getX(),
    					(int) event.getY());
            	Toast.makeText(getBaseContext(), 
                        dest_p.getLatitudeE6() / 1E6 + "," + 
                        dest_p.getLongitudeE6() /1E6 , 
                        Toast.LENGTH_SHORT).show();
				return false;
			} 
        });
	}


	//@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}







}
