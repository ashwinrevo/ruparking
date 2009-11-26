
package com.phoneInterface;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ZoomControls;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.mapManagement.Painter;
import com.phoneInterface.R;

public class MapDisplay extends MapActivity{
       List<Overlay> mapOverlays;
   Drawable drawable1;
   Drawable drawable2;
   Painter itemizedOverlay;
   Painter itemizedOverlay2;
   LinearLayout linearLayout;
   MapView mapView;
   ZoomControls mZoom;
   OverlayItem overlayitem;
   MapController mc;
   ArrayList<GeoPoint> listOfGeopoints=new ArrayList<GeoPoint>();
   Location location;
       LocationManager locationManager;
   ArrayList<String> color_initial=new ArrayList<String>();
   ArrayList<String> latitude_initial=new ArrayList<String>();
   ArrayList<String> longitude_initial=new ArrayList<String>();

   ArrayList<Integer> color=new ArrayList<Integer>();
   ArrayList<Integer> latitude=new ArrayList<Integer>();
   ArrayList<Integer> longitude=new ArrayList<Integer>();

       int a;

       public void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.map);

               color_initial=com.clientData.clientData.read;
               latitude_initial=com.clientData.clientData.latitude_coor;
               longitude_initial=com.clientData.clientData.longitude_coor;

               for (int i=0; i<color_initial.size(); i++){
                       double temp_color=Double.parseDouble(color_initial.get(i));
                       double temp_latitude=Double.parseDouble(latitude_initial.get(i));
                       double temp_longitude=Double.parseDouble(longitude_initial.get(i));
                       color.add((int)(temp_color*1E6));
                       latitude.add((int)(temp_latitude*1E6));
                       longitude.add((int)(temp_longitude*1E6));
               }


               for (int i=0;i<1000;i=i+10){
                       listOfGeopoints.add(new GeoPoint(latitude.get(i), longitude.get(i)));
               }



               OverlayItem overlayitem = new
OverlayItem(com.phoneInterface.Display.point, "", "");
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
       itemizedOverlay = new Painter(drawable1);

       drawable2 = this.getResources().getDrawable(R.drawable.red);
       itemizedOverlay2 = new Painter(drawable2);


       for (int index=0;index< listOfGeopoints.size();index++){
               if (color.get(index)==255){
                       overlayitem = new
OverlayItem(listOfGeopoints.get(index), "", "");
                       itemizedOverlay.addOverlay(overlayitem);}
               else{
                       overlayitem = new
OverlayItem(listOfGeopoints.get(index), "", "");
               itemizedOverlay2.addOverlay(overlayitem);}
               }
     mapOverlays.add(itemizedOverlay);
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
                               //Intent myIntent = new Intent(MapDisplay.this, MapDisplay.class);
                               //MapDisplay.this.startActivity(myIntent);
                       }
               });
       }
       @Override
       protected boolean isRouteDisplayed() {
               // TODO Auto-generated method stub
               return false;
       }
}