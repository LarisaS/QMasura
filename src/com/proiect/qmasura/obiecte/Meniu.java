package com.proiect.qmasura.obiecte;

import java.util.ArrayList;

public class Meniu {

	private ArrayList<Reteta> retete;
	private String denumire, descriere;
	private boolean can_be_done=true;
	
	public Meniu(String denumire, String descriere)
	{
		retete= new ArrayList<Reteta>();
		this.denumire=denumire;
		this.descriere=descriere;
		can_be_done=false;
		
	}
	
	
	public Meniu()
	{
		retete= new ArrayList<Reteta>();
	}

	public boolean esteDisponibil()
	{
		return can_be_done;
	}
	
	public ArrayList<Reteta> getRetete() {
		return retete;
	}

	public void setRetete(ArrayList<Reteta> retete) {
		this.retete = retete;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	
}
