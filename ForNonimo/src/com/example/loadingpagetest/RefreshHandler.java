package com.example.loadingpagetest;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class RefreshHandler extends Handler{
	
	private LoadingView mv;

	public RefreshHandler(LoadingView mv) {
		// TODO Auto-generated constructor stub
		this.mv = mv;
	}

	public void handleMessage(Message msg){
		
		if(msg.what==0x101){
//			Log.v("Dan","Receive Message");
			mv.onDetachedFromWindow();
		}
		
	}

	public void setData(Person person) {
		// TODO Auto-generated method stub
//		Log.v("Dan","Send Message");
		//mv.setPerson(person);
	}
}
