package com.example.loadingpagetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class myView extends View{
	 int width ;
     int height;
     Context context;
     AttributeSet attrs;
     List<String> colors;
     List<myTab> tabs;
     ArrayList<Person> persons;
	public myView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.persons=persons;
		width=1;
		height=1;
		
		
	}
	public void setPersons( ArrayList<Person> persons){
		this.persons=persons;
	}
	private void init(){

//			 Log.v("Oncreate"," Init");
		 try {
			 colors=xmlParser.parseXML((MainActivity)context);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		tabs = new ArrayList<myTab>();
		   for(int i =10;i<height*30;i=i+210){
	    	   myTab tab = new myTab(context);
	    	   tab.setTab(10, i, width-20,200);
	    	   Random r = new Random();
		        int position = r.nextInt(colors.size());     
		        int color =Color.parseColor(colors.get(position));
	    	   tab.setColor(color);
	    	  
	    	   tabs.add(tab);	    	  
	       }
		   Log.v("DAN"," TABS SIZE "+(new Integer(persons.size()).toString()));
		   
		   for(int i =0;i<persons.size();i++){
			   Log.v("Oncreate","SS "+String.valueOf(persons.get(i).getAge()));
			   tabs.get(i).setPerson(persons.get(i));
		   }
//		   Log.v("DAN"," TABS SIZE "+(new Integer(tabs.size()).toString()));
	}
	  protected void onDraw(Canvas canvas) {
	       // TODO Auto-generated method stub
	       super.onDraw(canvas);
	       canvas.drawColor(Color.GRAY);
//	       Log.v("DAN"," DRAW");
	    
	      for(myTab t:tabs){
	    	  t.draw(canvas);
	      }
	    
	    }
	  public void update(int height,int width){
		  this.height=height;
		  this.width=width;
		  init();
		  
	  }
	  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(width, height*5);
		}
	
}
