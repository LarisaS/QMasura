package com.proiect.qmasura;


import java.util.ArrayList;

import com.proiect.qmasura.obiecte.DetailedGeneralIngredient;
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.sqlite.DbHelper;

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
	private ArrayList<Ingredient> ingredienteDisponibile;
	private ArrayList<DetailedGeneralIngredient> ingredienteGenerale;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setHasOptionsMenu(true);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		ingredienteDisponibile= new ArrayList<Ingredient>();
		 		DbHelper db_helper= new DbHelper(this.getActivity());
		 		ingredienteDisponibile=db_helper.ingredienteDinFrigider();
		 		ingredienteGenerale= db_helper.obtineIngredienteGenerale();
		 		db_helper.close();
		 		
		        fragmentView=inflater.inflate(R.layout.frigiderul_meu_view, container, false);
		        GridView imagini= (GridView)fragmentView.findViewById(R.id.frigiderul_meu);
					FrigiderulMeuGridAdaptor adaptor_grid= new FrigiderulMeuGridAdaptor(getActivity(),ingredienteDisponibile);
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
	        	 args.putSerializable("ingrediente", ingredienteGenerale);
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


