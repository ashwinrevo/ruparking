package com.mapManagement;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.phoneInterface.Display;
import com.phoneInterface.R;


import android.location.LocationManager;
import android.location.Location;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

public class Mapper extends MapActivity{
	ZoomControls mZoom;
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	HelloItemizedOverlay itemizedoverlay;
	Location location;
	LocationManager locationManager;
	MapController mc;
	int a;
		
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);      
        
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
        drawable = this.getResources().getDrawable(R.drawable.dot);
        itemizedoverlay = new HelloItemizedOverlay(drawable);
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
    
    
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
        	//Intent myIntent = new Intent(Mapper.this, Mapper.class);
            //Mapper.this.startActivity(myIntent);
        }
    });
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
 }