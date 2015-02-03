package com.proiect.qmasura.obiecte;

import android.util.Log;

public class GeneralIngredient {

	protected String generalName,picure,ums;
	
	public GeneralIngredient(){
	}

	public String getUms() {
		return ums;
	}

	public void setUms(String ums) {
		this.ums = ums;
	}

	public String getGeneralName() {
		return generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public String getPicure() {
		return picure;
	}

	public void setPicure(String picure) {
		this.picure = picure;
	}
	
	
	public void display(){
		Log.i("GeneralIngredient", "Ingredint nume_general="+generalName+" picture="+picure);
	}
	
}
