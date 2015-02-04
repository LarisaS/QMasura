package com.proiect.qmasura;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.proiect.qmasura.obiecte.UnitatiDeMasura;

public class UnitatiMasuraSpinnerAdapter extends ArrayAdapter<UnitatiDeMasura> {

	private List<UnitatiDeMasura> values;
	Context context;
	public UnitatiMasuraSpinnerAdapter(Context context, int resource,
			List<UnitatiDeMasura> objects) {
		super(context, resource, objects);
		this.context=context;
		values=objects;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {

	    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	            View row = inflater.inflate(R.layout.spinner_item_layout, parent,
	                    false);
	       TextView make = (TextView) row.findViewById(R.id.unity_title);
	       Typeface tf = Typeface.createFromAsset(((Activity) context).getAssets(),"fonts/Sunshine.ttf");
		      
	        make.setTypeface(tf);
	        make.setText(values.get(position).getName());
	        return row;
	    }


	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		 LayoutInflater inflater = ((Activity) context).getLayoutInflater();
         View row = inflater.inflate(R.layout.spinner_item_layout, parent,
                 false);
    TextView make = (TextView) row.findViewById(R.id.unity_title);
    Typeface tf = Typeface.createFromAsset(((Activity) context).getAssets(),"fonts/Sunshine.ttf");
	      
     make.setTypeface(tf);
     make.setText(values.get(position).getName());
     return row;
	        }
	
	
	public UnitatiDeMasura getItem(int position){
		return values.get(position);
	}
}
