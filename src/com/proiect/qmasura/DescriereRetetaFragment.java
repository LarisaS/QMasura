package com.proiect.qmasura;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;
import com.proiect.qmasura.utilitare.ClasaUtilitara;

public class DescriereRetetaFragment extends Fragment {
			View fragmentView;
			private static final String ARG_SECTION_NUMBER = "section_number";
			private Reteta reteta;
			
			 @Override
			    public void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			    }
			
			 public View onCreateView(LayoutInflater inflater, ViewGroup container,
				        Bundle savedInstanceState) {
				        // Inflate the layout for this fragment
				 
				 			if(!this.getArguments().isEmpty() && this.getArguments().containsKey("reteta"))
				 					reteta=(Reteta) this.getArguments().getSerializable("reteta");
				 			else
				 					reteta= null;
				 			reteta.display();
				            fragmentView=inflater.inflate(R.layout.reteta_dataliu, container, false);
				         	WebView continut= (WebView)fragmentView.findViewById(R.id.continut_reteta);
				         	continut.setBackgroundColor(0x88ffffff);
				         	if(reteta!=null)
							{
								String html=ClasaUtilitara.descriereReteta(reteta);
								Log.i("Descrierer reteta","Reteta"+html);
								continut.loadData(html, "text/html",  "utf-8");
							}
							
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
