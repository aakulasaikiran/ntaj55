package com.nt.service;

import java.util.Random;

public class RotatorService {
	private String images[]={"1.jpg","2.jpg","3.jpg","4.jpg","5.jpg"};
	private String urls[]={"http://www.raymond.in/",
			                                    "http://www.digjam.co.in/",
			                                    "http://onlyvimal.co.in/",
			                                    " http://www.siyaram.com/",
			                                    "https://www.ramrajcotton.in/"};
	private int counter;
	
	public String getImage(){
		return images[counter];
	}
	
   public String getUrl(){
		return urls[counter];
	}
   
    public void nextAdvertisement(){
    	Random rad=new Random();
    	counter=rad.nextInt(5);
    }
}//class
