package com.example.loadingpagetest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class LoadingActivity extends Activity {
	
private LoadingView main_imageview;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        long t1=System.currentTimeMillis();
        setContentView(R.layout.main);
        main_imageview = (LoadingView)findViewById(R.id.main_imageview);
        initLoadingImages();
        
        new Thread()
        {
            @Override
            public void run()
            {
                main_imageview.startAnim();
            }
        }.start();
        long t2=System.currentTimeMillis();
        Log.v("DAN","LoadingActivity "+String.valueOf(t2-t1));
    }
    
    private void initLoadingImages()
    {
        int[] imageIds = new int[6];
        imageIds[0] = R.drawable.loader_frame_1;
        imageIds[1] = R.drawable.loader_frame_2;
        imageIds[2] = R.drawable.loader_frame_3;
        imageIds[3] = R.drawable.loader_frame_4;
        imageIds[4] = R.drawable.loader_frame_5;
        imageIds[5] = R.drawable.loader_frame_6;
        
        main_imageview.setImageIds(imageIds);
    }
    public class switichHandler extends Handler{
    	
    	private LoadingActivity mv;

    	public switichHandler(LoadingActivity mv) {
    		// TODO Auto-generated constructor stub
    		this.mv = mv;
    	}

    	public void handleMessage(Message msg){
    		
    		if(msg.what==0x101){
    			 Intent mainIntent = new Intent(LoadingActivity.this,  
                         MainActivity.class);  
    			 LoadingActivity.this.startActivity(mainIntent);  
    			 LoadingActivity.this.finish();  
    		}
    		
    	}

    	public void setData(Person person) {
    		// TODO Auto-generated method stub
//    		Log.v("Dan","Send Message");
    		//mv.setPerson(person);
    	}
    }
}


