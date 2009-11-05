package com.clientData;

import org.developerworks.android.FeedParser;
import org.developerworks.android.ParserType;

public abstract class FeedParserFactory {
	static String feedUrl = "http://www.winlab.rutgers.edu/~vrajeshv/clientData.xml";
	
	public static FeedParser getParser(){
		return getParser(ParserType.ANDROID_SAX);
	}
	
	public static FeedParser getParser(ParserType type){
		switch (type){
		
			case ANDROID_SAX:
				return new AndroidSaxFeedParser(feedUrl);

			default: return null;
		}
	}
}
