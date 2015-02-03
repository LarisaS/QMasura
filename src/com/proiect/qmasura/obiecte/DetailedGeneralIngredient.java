package com.proiect.qmasura.obiecte;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class DetailedGeneralIngredient extends GeneralIngredient implements Serializable {
	private ArrayList<UnitatiDeMasura> ums_list=null;
	
	public DetailedGeneralIngredient(){
	}
	
	public void setUms(ArrayList<UnitatiDeMasura> units){
		ums_list=units;
	}
	
	public void addUnit(UnitatiDeMasura unit){
		if(ums_list==null)
			ums_list= new ArrayList<UnitatiDeMasura>();
		ums_list.add(unit);
	}
	
	public ArrayList<UnitatiDeMasura> getUmsList(){
		return ums_list;
	}

	public void display(){
		StringBuilder all_units= new StringBuilder();
		for(UnitatiDeMasura unit:ums_list){
			all_units.append(unit.getId());
			all_units.append("-");
			all_units.append(unit.getName());
			all_units.append("|");
		}
		Log.i("GeneralIngredient", "Ingredint nume_general="+generalName+" picture="+picure+" unitati:"+all_units.toString());
	}


}
