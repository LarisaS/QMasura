package com.proiect.qmasura;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.AdapterView.OnItemClickListener;

import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;

public class ListareReteteFragment extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<SummaryReteta> retete;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 
		 			if(!this.getArguments().isEmpty() && this.getArguments().containsKey("retete"))
		 					retete=(ArrayList<SummaryReteta>) this.getArguments().getSerializable("retete");
		 			else
		 					retete= new ArrayList<SummaryReteta>();
		 	
		            fragmentView=inflater.inflate(R.layout.listare_retete, container, false);
		         	GridView imagini= (GridView)fragmentView.findViewById(R.id.lista_retete_sugerate);
					ListareReteteAdaptor adaptor_grid= new ListareReteteAdaptor(getActivity(),retete);
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
		 
		    super.onCreateOptionsMenu(menu,inflater);
		     
		}
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     return super.onOptionsItemSelected(item);
	 }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }
	 
}


