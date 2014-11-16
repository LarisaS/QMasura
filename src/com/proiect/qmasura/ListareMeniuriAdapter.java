package com.proiect.qmasura;

import java.util.ArrayList;

import com.proiect.qmasura.obiecte.Meniu;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListareMeniuriAdapter extends ArrayAdapter<Meniu> {
	private final Context context;
	private  ArrayList<Meniu> values;

	public ListareMeniuriAdapter(Context context,ArrayList<Meniu> values) {
		super(context,R.layout.listare_meniuri_item,values);
		this.context = context;
	    this.values = values; 
	   
	}
	

	
	
	 public View getView(int position, View convertView, ViewGroup parent) {
	       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	       View rowView = inflater.inflate(R.layout.listare_meniuri_item, parent, false);
	       
	       TextView denumire = (TextView) rowView.findViewById(R.id.denumire_meniu);
	       TextView retete = (TextView) rowView.findViewById(R.id.numar_retete_in_meniu);
	       TextView disponibilitate = (TextView) rowView.findViewById(R.id.disponibilitate);
	       
	       Typeface tf = Typeface.createFromAsset(context.getAssets(),
	                "fonts/Sunshine.ttf");
	       
	       denumire.setTypeface(tf);
	       retete.setTypeface(tf);
	       disponibilitate.setTypeface(tf);
	       
	       
	       
	       Meniu current= values.get(position);
	       Log.i("AAAAA", current.getDenumire()+"  ");
	       
	       denumire.setText(current.getDenumire());
	       
	       retete.setText(current.getRetete().size()+" retete");
	       
	       if(current.esteDisponibil())
	    	   	disponibilitate.setText("Poti sa o faci");
	       else
	    	   disponibilitate.setText("Nu ai toate ingredientele");
	       
	       return rowView; 
	    }

}
