package com.proiect.qmasura.obiecte;

import java.io.Serializable;

import android.util.Log;

public class IngredientLipsa implements Serializable {

	private String name,cantitate;
	private int id;
	
	
	public IngredientLipsa()
	{}
	public IngredientLipsa(int id, String name,String cantitate)
	{
		this.id=id;
		this.name=name;
		this.cantitate=cantitate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCantitate() {
		return cantitate;
	}
	public void setCantitate(String cantitate) {
		this.cantitate = cantitate;
	}
	
	public void display()
	{
		Log.i("Ingredient Lipsa","Ingredient "+id+" "+name+" "+cantitate);
		
	}
}
