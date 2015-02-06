package com.proiect.qmasura;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.proiect.qmasura.obiecte.DetailedGeneralIngredient;
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.UnitatiDeMasura;
import com.proiect.qmasura.sqlite.DbHelper;

public class AdaugaIngredientFragment extends Fragment {
	private View myFragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";

	private AutoCompleteTextView ingredient;
    private EditText cantitate;
    private Button adauga; 
	private AutocompleteIngredientAdapter adapter;
	private DetailedGeneralIngredient selected_ingredient;
	private Spinner unitati_ingredient;
	private UnitatiMasuraSpinnerAdapter adapterSpinner;
	private ArrayList<UnitatiDeMasura> unitati_values;
	private Ingredient new_ingredient;
	DbHelper db_helper;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 
		 		ArrayList<DetailedGeneralIngredient> ingrediente= null;
		 		
		 		db_helper= new DbHelper(getActivity());
		 		
		 		new_ingredient= new Ingredient();
		 		
		 		if(!this.getArguments().isEmpty() && this.getArguments().containsKey("ingrediente"))
		 			ingrediente=(ArrayList<DetailedGeneralIngredient>) this.getArguments().getSerializable("ingrediente");
		 		else
		 			ingrediente= new ArrayList<DetailedGeneralIngredient>();
		 		
		 		Toast.makeText(getActivity(), "Ingrediente disponibile "+ingrediente.size(), Toast.LENGTH_SHORT).show();
		 		
		 	
		 	   myFragmentView=inflater.inflate(R.layout.adauga_ingredient_layout, container, false);
		       ingredient = (AutoCompleteTextView) myFragmentView.findViewById(R.id.ingredient_selectat);
		      
		       cantitate=(EditText) myFragmentView.findViewById(R.id.cantitate_ingredient);
		       cantitate.setRawInputType(Configuration.KEYBOARD_12KEY);
		       
		       Button adauga= (Button) myFragmentView.findViewById(R.id.adauga_ingredientul_selectat);
		       adauga.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					insereazaIngredient();
				}
			});
		       Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Sunshine.ttf");
		       
		       adauga.setTypeface(tf);
		       ingredient.setTypeface(tf);
		       cantitate.setTypeface(tf);
		       
		       unitati_values= new ArrayList<UnitatiDeMasura>();
		       unitati_values.add(new UnitatiDeMasura(1, "Unitate"));
		       
		       unitati_ingredient=(Spinner) myFragmentView.findViewById(R.id.unitate_ingredient);
		       adapterSpinner = new UnitatiMasuraSpinnerAdapter(getActivity(), 
		    		   											R.layout.spinner_item_layout, 
		    		   											unitati_values);
		       unitati_ingredient.setAdapter(adapterSpinner);
		       unitati_ingredient.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					UnitatiDeMasura um= (UnitatiDeMasura) parent.getItemAtPosition(position);
					new_ingredient.setUm(um.getName());
					new_ingredient.setUm_id(um.getId());
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
			});
		     
		       adapter = new AutocompleteIngredientAdapter(this.getActivity(), android.R.layout.simple_list_item_1, ingrediente);
				
				ingredient.setAdapter(adapter);
				ingredient.setOnItemClickListener(new OnItemClickListener() {        
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						// TODO Auto-generated method stub			
						InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(view.getWindowToken(), 0);	
						DetailedGeneralIngredient selected=(DetailedGeneralIngredient)parent.getItemAtPosition(position);
						selected_ingredient=selected;
						selected.display();
						ingredient.setText(selected.getGeneralName());
						
						adapterSpinner.clear();
						adapterSpinner.addAll(selected_ingredient.getUmsList());
						adapterSpinner.notifyDataSetChanged();
						
						if(selected_ingredient.getUmsList().size()>=1){
							new_ingredient.setUm(selected_ingredient.getUmsList().get(0).getName());
							new_ingredient.setUm_id(selected_ingredient.getUmsList().get(0).getId());
						}
						new_ingredient.setGeneral_name(selected.getGeneralName());
						new_ingredient.setPoza(selected.getPicure());
					}
		        });
				
		       
		 		
		 		return myFragmentView;
		    }
	private void closeFragment(){
		db_helper.close();
		getFragmentManager().popBackStack();
	}
	
	private void insereazaIngredient(){
		
		if(cantitate.getText().toString().isEmpty())
			Toast.makeText(getActivity(), "Adaugati o cantitate", Toast.LENGTH_SHORT).show();
		else{
			try{
				float cantit= Float.parseFloat(cantitate.getText().toString());
				new_ingredient.setCantitate(cantit);
				new_ingredient.display();
				
				db_helper.insereazaIngredientInFrigider(new_ingredient);
				closeFragment();
			} catch (NumberFormatException e){
				Toast.makeText(getActivity(), "Cantitatea introdusa nu este corecta", Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
}
