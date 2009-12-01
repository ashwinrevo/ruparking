package com.mapManagement;


import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
/** 
 * This class converts the relevance factor to a matching color.
 * 
 * @version     1.0
 */
public class Mapper extends ItemizedOverlay {
	/**
	 * Decides the color which is to be displayed to the for every location on the map based on its relevance factor.
	 * 
	 * @param overlay is an object of type OverlayItem containing the drawable item
	 * @return mOverlays.size() Integer indicating which color the location should be displayed in 
	 */
	public ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	
	
	public Mapper(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));		// TODO Auto-generated constructor stub
	}
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
		}


}
