package com.proiect.qmasura.obiecte;

import java.io.Serializable;

import org.json.JSONObject;

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
	public Ingredient()
	{
	}
	public Ingredient(JSONObject ingr)
	{
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGeneral_name() {
		return general_name;
	}
	public void setGeneral_name(String general_name) {
		this.general_name = general_name;
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
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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
	
}
