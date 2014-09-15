package com.example.loadingpagetest;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Person implements Parcelable{
	private int id;

	private int age;
	private String title;
	private Bitmap img;
	private String sex;
	private String imgUrl;
	
	@Override
	public String toString() {
		return "Id: " + id + "\r\n\nName:" + title
				+ "\r\n\nAge:" + age + "\r\n\nSex:" + sex;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Bitmap getImg() {
		return img;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Person(int id, int age, String title, String imgUrl, String sex) throws Exception {
		super();
		this.id = id;
		this.age = age;
		this.title = title;
		this.imgUrl = imgUrl;
		this.sex = sex;
		this.img = ImageService.getImage(imgUrl);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeInt(age);
		dest.writeString(title);
		//img.writeToParcel(dest, 0);
		dest.writeString(imgUrl);
		dest.writeString(sex);
		 Log.v("DAN","TRY TO WRITE title "+title);
	}
	  public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>() {  
		  
	        @Override  
	        public Person createFromParcel(Parcel source) {  
	            // TODO Auto-generated method stub  
	            try {
	            	Person p = new Person(source.readInt(),source.readInt(),source.readString(),source.readString(),source.readString());
	            	 Log.v("DAN","TRY TO READ p "+p.toString());
					return p;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					 Log.v("DAN","TRY TO READ ERROR "+e.getMessage());
					e.printStackTrace();
				}
				return null;  
	        }  
	  
	        @Override  
	        public Person[] newArray(int size) {  
	            // TODO Auto-generated method stub  
	            return new Person[size];  
	        }  
	          
	    }; 

}
