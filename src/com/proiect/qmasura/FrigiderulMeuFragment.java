package com.proiect.qmasura;


import java.util.ArrayList;

import com.proiect.qmasura.obiecte.Ingredient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FrigiderulMeuFragment extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<Ingredient> ingrediente;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setHasOptionsMenu(true);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		ingrediente= new ArrayList<Ingredient>();
		 		  String tomato = "http://www.foodproductiondaily.com/var/plain_site/storage/images/publications/food-beverage-nutrition/foodproductiondaily.com/innovations/packaging-made-from-tomato-waste/8593675-1-eng-GB/Packaging-made-from-tomato-waste.jpg";
		          String onions="http://upload.wikimedia.org/wikipedia/commons/8/85/Red_onions.jpg";
		          String eggs="http://www.writtenchinese.com/wp-content/uploads/2013/09/Eggs.jpg";
		          String carrot="https://www.agric.wa.gov.au/sites/gateway/files/carrot-1.jpg";
		          String cabbage="http://topfoodfacts.com/wp-content/uploads/2013/01/cabbage.jpg";
		 		
		 		for(int i=0;i<400;i++)
		 		{
		 			  if(i%5==0)
		 			  {
		 				  Ingredient ing=new Ingredient("Ceapa",onions);
		 				  ing.setCantitate("12");
		 				  ing.setUnitate("buc");
		 				 ingrediente.add(ing);
		 				 
		 			  }
				          else
				       if(i%5==1)
				       {
			 				 ingrediente.add(new Ingredient("Rosii",tomato));
			 			  }
				       else
				    	   if(i%5==2)
				    	   {
				 				 ingrediente.add(new Ingredient("Oua",eggs));
				 			  }
				    	   else
				    		   if(i%5==3)
				    		   {
					 				 ingrediente.add(new Ingredient("Morcovi",carrot));
					 			  }
				    		   else
				    			   if(i%5==4)
				    			   {
						 				 ingrediente.add(new Ingredient("Varza",cabbage));
						 			  }
		 			
		 		}
		 			
		 		
		         fragmentView=inflater.inflate(R.layout.frigiderul_meu_view, container, false);
		         GridView imagini= (GridView)fragmentView.findViewById(R.id.frigiderul_meu);
					FrigiderulMeuGridAdaptor adaptor_grid= new FrigiderulMeuGridAdaptor(getActivity(),ingrediente);
					imagini.setAdapter(adaptor_grid);
					imagini.setOnItemClickListener(new OnItemClickListener() {
				        public void onItemClick(AdapterView<?> parent, View v,
				                int position, long id) {	   
				        
				        }
				    });
		         return fragmentView;
		    }

	 
	 
	 
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		    // Inflate the menu items for use in the action bar
		 	inflater = this.getActivity().getMenuInflater();
		    inflater.inflate(R.menu.meniu_frigider, menu);
		    super.onCreateOptionsMenu(menu,inflater);
		     
		}
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     switch (item.getItemId()) {
	         case R.id.adauga_ingredient:
	         {
	        	 final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
	        	 AdaugaIngredientFragment fragment= new AdaugaIngredientFragment();
	        	 Bundle args= new Bundle();
	        	 args.putSerializable("ingrediente", ingrediente);
	        	 fragment.setArguments(args);
	        	 ft.replace(R.id.container, fragment, "AdaugaIngredient"); 
	        	 ft.addToBackStack("Adauga ingredient");
	        	 ft.commit(); 
	        	
	            return true;
	         }
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }
	 
}


