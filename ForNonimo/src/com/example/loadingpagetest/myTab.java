package com.example.loadingpagetest;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

public class myTab extends View{
		Rect tab;
		Rect image;
		Rect text;
		int padding;
		int color;
		Person person;
		float height;
		int textWidth;
	  	public myTab(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	    
	    public void setTab(int x,int y,int width,int height){
	    	padding =5;
	    	y=y+padding;
	    	tab= new Rect(x,y,x+width,y+height);
	    	image=new Rect(x+padding,y+padding,x+width/3-padding,y+height);
	    	int textX=x+width/3+padding;
	    	
	    	text = new Rect(textX+padding,y+padding,x+width-padding,y+height);
	    	textWidth=width*2/3;
	    	
	    }
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
	        Paint paint = new Paint();
//	        Log.v("DAN","DRAW TAB");
	        canvas.drawRect(tab, paint);
	        paint.setColor(color);
	        canvas.drawRect(image, paint);
	        paint.setColor(color);
	        canvas.drawRect(text, paint);
	        drawPerson(canvas);
	    }
	    
	    private void drawPerson(Canvas canvas){
	    	if(person!=null){
	    		 Paint paint = new Paint();
	    		 TextPaint tPaint = new TextPaint();
	    		 tPaint.setTextSize(20.0f);
	    		 tPaint.setTypeface(Typeface.DEFAULT_BOLD);
	    		
	    		 canvas.drawBitmap(person.getImg(), null, image, paint);
	    		 StaticLayout layout = new StaticLayout(person.toString(), tPaint, 
	    				 textWidth, Alignment.ALIGN_NORMAL, (float) 1.0,(float) 0.0, true);    		 
	    		 canvas.translate(text.left+5*padding, text.top+3*padding);
	    		 layout.draw(canvas);
	    		 canvas.translate(-text.left-5*padding, -text.top-3*padding);
	    	}
	    	
	    }
	    
	    

		public void setColor(int color2) {
			// TODO Auto-generated method stub
			this.color=color2;
		}



		public void setPerson(Person person2) {
			// TODO Auto-generated method stub
			this.person = person2;
			 Log.v("Oncreate",String.valueOf(person.getAge()));
			 Log.v("Oncreate",person.getImgUrl());
			 Bitmap oldImage = person.getImg();
    		 float width = oldImage.getWidth();
    		 float height = oldImage.getHeight();
    		 float scaleWidth =image.width()/ width;
    		 float scaleHeight = image.height()/height;
    			
    		 Matrix matrix = new Matrix();  
    		 matrix.postScale(scaleWidth, scaleHeight);  
    		 Bitmap newbmp = Bitmap.createBitmap(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(),  
    		            matrix, true);  
    		 person.setImg(newbmp);
//    		 Log.v("Oncreate",String.valueOf(person.getImg().getWidth())+"  "+String.valueOf(person.getImg().getHeight()));
    		 
		}
}
