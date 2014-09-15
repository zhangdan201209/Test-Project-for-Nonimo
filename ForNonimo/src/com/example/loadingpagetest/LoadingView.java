package com.example.loadingpagetest;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class LoadingView extends ImageView implements Runnable
{
        private boolean isStop = false;
        
        private int[] imageIds;
        private int index = 0;
        private int length = 1;
        RefreshHandler myHandler;
   	 	jsonService jService;
   	 	Context context;
   	 	Intent mainIntent;
   	 	public LoadingView(Context context)
        {
                this(context, null);
               
        }

        public LoadingView(Context context, AttributeSet attrs)
        {  	    super(context, attrs);
        		this.context=context;
        	 	myHandler = new RefreshHandler(this);
        	 	mainIntent = new Intent(context,MainActivity.class);  
        	 	jService = new jsonService(getResources().getString(R.string.urlServer),myHandler,mainIntent);
             
        }
        
        public void setImageIds(int[] imageId)
        {
                this.imageIds = imageId;
                if(imageIds != null && imageIds.length > 0)
                {
                        length = imageIds.length;
                }
        }
        
                @Override
        protected void onDetachedFromWindow()
        {
                // TODO Auto-generated method stub
                super.onDetachedFromWindow();
                isStop = true;
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
                // TODO Auto-generated method stub
                super.onDraw(canvas);
                if(imageIds != null && imageIds.length > 0)
                {
                        this.setImageResource(imageIds[index]);
                }
        }
        
        @Override
        public void run()
        {
                while(!isStop)
                {		long t1=System.currentTimeMillis();
                        index = ++index % length;
                        postInvalidate();
                        try
                        {
                                Thread.sleep(400);
                        }
                        catch (InterruptedException e)
                        {
                                e.printStackTrace();
                        }
                        long t2=System.currentTimeMillis();
                        Log.v("DAN","LoadindViewPerLoop "+String.valueOf(t2-t1));
                }
              
                ((LoadingActivity) context).startActivity(mainIntent);  
                ((LoadingActivity) context).finish();  
               
        }
        
        public void startAnim()
        {
                new Thread(this).start();
                new Thread(jService).start();
        }

}