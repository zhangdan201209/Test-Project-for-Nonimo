package com.example.loadingpagetest;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Service;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.util.Xml;

public class jsonService  implements Runnable{

	private String path;
	private RefreshHandler myHandler;
	private InputStream inStream;
	private ArrayList<Person> persons;
	private JSONArray  jsonArray;
	private Intent intent;
	public jsonService(String path, RefreshHandler myHandler,Intent intent){
		this.path = path;
		this.myHandler = myHandler;
		this.intent = intent;
		
	}
	private  void initConnection() throws Exception{
		URL url = new URL(path);
//		Log.i("dz", "Before");
//		Log.i("Dan","Before"+path);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		long t1=System.currentTimeMillis();
		conn.setRequestMethod("GET");
		conn.connect();
		//String code =String.valueOf(conn.getResponseCode());
//		Log.i("dz", code);
		Thread.sleep(400);
		if(conn.getResponseCode()==200){
			inStream = conn.getInputStream();
			Log.i("dz", "Middle");
			
		}
		long t2=System.currentTimeMillis();
        Log.v("TT","JSON INIT OPEN-RECEIVE "+String.valueOf(t2-t1));
		persons= new ArrayList<Person>();
		byte[] data = StreamTool.read(inStream);
		String json = new String(data);
		jsonArray = new JSONArray(json);
	}
	public  int getSize(){
		return jsonArray.length();
	}
	public  void getOnePerson(int i) throws Exception {
		
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int id= jsonObject.getInt("id");
			int age= jsonObject.getInt("age");
			String title=jsonObject.getString("title");
			String sex=jsonObject.getString("sex");
			String imgUrl=jsonObject.getString("img");
//			Log.v("dz", sex);
			Person person= new Person(id,age,title,imgUrl,sex);
			persons.add(person);
			
}
	
	public void setData(){
		Bundle mBundle = new Bundle(); 
		Log.v("DAN", "BEGIN PAR");
		
		mBundle.putParcelableArrayList("persons",persons);
		Log.v("DAN","SS "+String.valueOf(persons.get(1).getAge()));
		Log.v("DAN", "AFTER PAR");
		
		intent.putExtras(mBundle);
//		intent.putParcelableArrayListExtra("persons",persons);
	}
	public void run() {
		// TODO Auto-generated method stub
		try {
			try {
				long t1=System.currentTimeMillis();
				initConnection();
				long t2=System.currentTimeMillis();
                Log.v("DAN","JSON INIT "+String.valueOf(t2-t1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long t1=System.currentTimeMillis();
			for(int i=1;i<getSize();i++){
				
				getOnePerson(i);
				Thread.sleep(200);
			}
			long t2=System.currentTimeMillis();
            Log.v("DAN","JSON GET "+String.valueOf(t2-t1));
			Message m = new Message();
			setData();
			m.what=0x101;
			myHandler.sendMessage(m);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
}
