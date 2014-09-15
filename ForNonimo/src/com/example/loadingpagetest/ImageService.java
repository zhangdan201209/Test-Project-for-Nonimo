package com.example.loadingpagetest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageService {

	
	public static Bitmap getImage(String path) throws Exception{
		URL url = new URL(path);
//		Log.i("dz", "Before");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		String code =new Integer(conn.getResponseCode()).toString();
//		Log.i("dz", code);
		if(conn.getResponseCode()==200){
			InputStream inStream = conn.getInputStream();
//			Log.i("dz", "Middle");
			Bitmap bitmap= BitmapFactory.decodeStream(inStream);
//			Log.i("dz", "After");
			return bitmap;
		}
		
		return null;
		
	}
}
