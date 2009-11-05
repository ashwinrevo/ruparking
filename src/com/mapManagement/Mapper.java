package com.mapManagement;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.LocationManager;
import android.location.Location;
import android.location.LocationProvider;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

public class Mapper extends MapActivity{
	ZoomControls mZoom;
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	HelloItemizedOverlay itemizedoverlay;
	MapController mc;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        OverlayItem overlayitem = new OverlayItem(com.example.addressform.AddressForm.point, "", "");
        linearLayout = (LinearLayout) findViewById(R.id.zoomview);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setStreetView(true);
        mc=mapView.getController();
        mc.animateTo(com.example.addressform.AddressForm.point);
        mc.setZoom(14);
        mZoom = (ZoomControls) mapView.getZoomControls();
        linearLayout.addView(mZoom);
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.green);
        itemizedoverlay = new HelloItemizedOverlay(drawable);
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
 }