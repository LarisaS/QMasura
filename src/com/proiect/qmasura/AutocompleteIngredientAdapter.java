package com.proiect.qmasura;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.proiect.qmasura.obiecte.DetailedGeneralIngredient;
import com.proiect.qmasura.obiecte.Ingredient;

public class AutocompleteIngredientAdapter extends ArrayAdapter<DetailedGeneralIngredient> implements Filterable {

	ArrayList<DetailedGeneralIngredient> values;
	ArrayList<DetailedGeneralIngredient> originalValues;
	Filter myFilter;
	Context context;
	
	public AutocompleteIngredientAdapter(Context context, int resource,ArrayList<DetailedGeneralIngredient> values) {
		super(context,R.layout.autocomplete_ingredient_layout, values);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.values= values;//new ArrayList<Utilizator>(values);
		this.originalValues= new ArrayList<DetailedGeneralIngredient>(values);
		
	}
	
	@Override
	public Filter getFilter() 
	{
	    Filter myFilter = new Filter() 
	    {
	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) 
	        {
	            FilterResults filterResults = new FilterResults();
	            if(constraint != null && constraint.length()>0) 
	            {
	            	ArrayList<DetailedGeneralIngredient> filtrate= new ArrayList<DetailedGeneralIngredient>();
	            	
	            	for(int i=0;i<originalValues.size();i++)
	            		if(originalValues.get(i).getGeneralName().contains(constraint))
	            			filtrate.add(originalValues.get(i));
	            	
	                filterResults.values = filtrate;
	                filterResults.count = filtrate.size();
	            }
	            else
	            {
	            	  filterResults.values = originalValues;
		                filterResults.count = originalValues.size();
	            }
	            return filterResults;
	        }

	        @Override
	        protected void publishResults(CharSequence contraint, FilterResults results) 
	        {
	        	values=(ArrayList<DetailedGeneralIngredient>) results.values;
	            if(results != null && results.count > 0) 
	            {
	                notifyDataSetChanged();
	            }
	            else {
	                notifyDataSetInvalidated();
	            }
	        }
	    };
	    return myFilter;
	}

	static class ViewHolder {
		  TextView ingredient;
		}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	      ViewHolder viewHolder;
	      if(convertView==null){

	          // inflate the layout
	          LayoutInflater inflater = ((Activity) context).getLayoutInflater();
	          convertView = inflater.inflate(R.layout.autocomplete_ingredient_layout, parent, false);

	          // well set up the ViewHolder
	          viewHolder = new 	 ViewHolder();
	          viewHolder.ingredient = (TextView) convertView.findViewById(R.id.ingredient_suggestion);

	          // store the holder with the view.
	          convertView.setTag(viewHolder);

	      }else{
	          // we've just avoided calling findViewById() on resource everytime
	          // just use the viewHolder
	          viewHolder = ( ViewHolder) convertView.getTag();
	      }

	      // object item based on the position
	      DetailedGeneralIngredient objectItem = this.getItem(position);

	      // assign values if the object is not null
	      if(objectItem != null) {
	          // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
	         
	    	 viewHolder.ingredient.setText(objectItem.getGeneralName());
	      }

	      return convertView;
	}
	
	  @Override
	    public int getCount() {
	        return values.size();
	    }

	    @Override
	    public DetailedGeneralIngredient getItem(int position) {
	        return values.get(position);
	    }

	    
	    public String getItemUsername(int position)
	    {
	    	return values.get(position).getGeneralName();
	    }
	    
	 
}
