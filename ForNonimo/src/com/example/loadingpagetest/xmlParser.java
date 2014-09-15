package com.example.loadingpagetest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.Log;
import android.util.Xml;

public class xmlParser {

	
	

	public static List<String> parseXML(Activity acitivity) throws XmlPullParserException {
		StringBuilder sb = new StringBuilder("");  
		Resources r = acitivity.getResources();  
        XmlPullParser xrp = r.getXml(R.xml.colors); 
        List<String> colors= null;
        String c=null;
//        Log.v("DAN", "Start PARSE");
		try{
			while(xrp.getEventType()!= XmlResourceParser.END_DOCUMENT){
				
				switch(xrp.getEventType()){
				
				case XmlResourceParser.START_DOCUMENT:
					colors = new ArrayList<String>();
					break;
				
				case XmlResourceParser.START_TAG:
//					Log.v("DAN", "Name "+xrp.getName());
					if(xrp.getName().equals("color")){
						 c = xrp.nextText();
//						Log.v("DAN", "Record "+c);
						
						xrp.next();
						
					}
				
					break;
				case XmlResourceParser.END_TAG:
					if(xrp.getName().equals("color")){
						colors.add(c);
						c=null;			
						}
					break;
				
				}
				xrp.next();
				}				
			return colors;			
		}catch(Exception e){
			e.printStackTrace();
		};
		return null;
		//textView1.setText("Done");
		
}
	
}
