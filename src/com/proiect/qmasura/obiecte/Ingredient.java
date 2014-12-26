package com.proiect.qmasura.obiecte;

import java.io.Serializable;

public class Ingredient implements Serializable {
	/*
	 * "id":1,"name":"mere","general_name":"mere","category_id":6,"buc_quantity":150.0,"picture":null
	 * */
	private String name, general_name,poza;
	private int id, category_id;
	private  float cantitate;
	private int um_id=0;
	private String um="";
	public Ingredient(String nume,String um,int um_id)
	{
		this.name=nume;
		this.poza=null;
		this.um=um;
		this.um_id=um_id;
		cantitate=0;
	}
	
	public String  getCantitateCuUnitati()
	{
		return cantitate+" "+um;		
	}
	
	public String getNume()
	{
		return this.name;
	}
	
	public String getUrlPoza()
	{
		return this.poza;
	}
	
	public float getCantitate()
	{
		return this.cantitate;
		
	}
	
	public void setCantitate(float cantit)
	{
		this.cantitate=cantit;
	}
	
	public void setNume(String nume)
	{
		this.name=nume;
	}
	
	
	public void setURLPoza(String url)
	{
		this.poza=url;
	}
}
