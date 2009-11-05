package com.clientData;

import java.util.ArrayList;
import java.util.List;

import org.developerworks.android.Message;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

public class AndroidSaxFeedParser extends BaseFeedParser {
	
	static final String RSS = "rss";
	public AndroidSaxFeedParser(String feedUrl) {
		super(feedUrl);
	}

	public List<Message> parse() {
		final Message currentMessage = new Message();
		RootElement root = new RootElement(RSS);
		final List<Message> messages = new ArrayList<Message>();
		Element array = root.getChild(ARRAY);
		Element datarow = array.getChild(DATAROW);
		datarow.setEndElementListener(new EndElementListener(){
			public void end() {
				messages.add(currentMessage.copy());
			}
		});
		array.getChild(INFO).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setInfo(body);
			}
		});
		array.getChild(LATITUDE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setLatitude(body);
			}
		});
		array.getChild(LONGITUDE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setLongitude(body);
			}
		});
		array.getChild(TIMEFRAME).setEndTextElementListener(new EndTextElementListener(){
			public void end(String body) {
				currentMessage.setTimeframe(body);
			}
		});
		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return messages;
	}
}
