package com.proiect.qmasura.obiecte;

import java.io.Serializable;

import org.json.JSONObject;

import android.util.Log;

public class Ingredient implements Serializable {
	/*
	 * "id":1,"name":"mere","general_name":"mere","category_id":6,"buc_quantity":150.0,"picture":null
	 * */
	private String general_name,poza,name;
	private int id;
	private  float cantitate;
	private int um_id=0;
	private String um="";
	
	public Ingredient(String nume,String um,int um_id)
	{
		this.general_name=nume;
		this.poza=null;
		this.um=um;
		this.um_id=um_id;
		cantitate=0;
		id=0;
	}
	public Ingredient()
	{
	}
	/*public Ingredient(JSONObject ingr)
	{
	
	}*/
	
	
	public String getGeneral_name() {
		return general_name;
	}
	public void setGeneral_name(String general_name) {
		this.general_name = general_name;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getPoza() {
		return poza;
	}
	public void setPoza(String poza) {
		this.poza = poza;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUm_id() {
		return um_id;
	}
	public void setUm_id(int um_id) {
		this.um_id = um_id;
	}
	public String getUm() {
		return um;
	}
	public void setUm(String um) {
		this.um = um;
	}
	public String  getCantitateCuUnitati()
	{
		return cantitate+" "+um;		
	}
	
	
	public float getCantitate()
	{
		return this.cantitate;
		
	}
	
	public void setCantitate(float cantit)
	{
		this.cantitate=cantit;
	}
	public void display() {
		// TODO Auto-generated method stub
		Log.i("Ingredient","Ingredient "+id+" "+general_name+" "+cantitate+" "+um+" ("+um_id+") poza:"+poza);
	}
	
}
