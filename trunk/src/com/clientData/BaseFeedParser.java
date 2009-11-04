package com.clientData;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.developerworks.android.FeedParser;

public abstract class BaseFeedParser implements FeedParser {

	// names of the XML tags
	static final String TIMEFRAME = "timeframe";
	static final  String LONGITUDE = "longitude";
	static final  String LATITUDE = "latitude";
	static final  String INFO = "info";
	static final  String ARRAY = "array";
	static final  String DATAROW = "datarow";
	
	private final URL feedUrl;

	protected BaseFeedParser(String feedUrl){
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}