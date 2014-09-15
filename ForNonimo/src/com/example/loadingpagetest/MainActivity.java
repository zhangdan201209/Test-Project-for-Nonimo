package com.example.loadingpagetest;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;


public class MainActivity extends Activity {
	myView mv;
	ArrayList<Person> persons;
	DisplayMetrics dm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		Init init = new Init();
		Thread t = new Thread(init);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
//		Log.v("Oncreate","TRY TO GET");
		
//		
	}

	class Init implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			
			 mv= (myView) findViewById(R.id.mv1);
			 persons =getIntent().getParcelableArrayListExtra("persons");
			 Log.v("Oncreate","TRY TO GET SIZE "+String.valueOf(persons.size()));
			 Log.v("Oncreate","TRY TO GET AGE "+persons.toString());
			 mv.setPersons(persons);
			
//			 Log.v("Oncreate"," Get "+(new Integer(dm.widthPixels)).toString()+" "+(new Integer(dm.heightPixels)).toString());
			// Log.v("Oncreate",new Integer(persons.size()).toString());
			 mv.update( dm.heightPixels,dm.widthPixels);
		}
		
	}

}
