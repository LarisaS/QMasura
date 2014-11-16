package com.proiect.qmasura;

import java.util.ArrayList;

import com.proiect.qmasura.obiecte.Ingredient;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AdaugaIngredientFragment extends Fragment {
	private View myFragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";

	private AutoCompleteTextView ingredient;
    private EditText cantitate;
    private Button adauga; 
	private AutocompleteIngredientAdapter adapter;
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 
		 		ArrayList<Ingredient> ingrediente= null;
		 		if(!this.getArguments().isEmpty() && this.getArguments().containsKey("ingrediente"))
		 			ingrediente=(ArrayList<Ingredient>) this.getArguments().getSerializable("ingrediente");
		 		else
		 			ingrediente= new ArrayList<Ingredient>();
		 		
		 		Toast.makeText(getActivity(), "Ingrediente disponibile "+ingrediente.size(), Toast.LENGTH_SHORT).show();
		 		
		 		myFragmentView=inflater.inflate(R.layout.adauga_ingredient_layout, container, false);
		       AutoCompleteTextView ingredient= (AutoCompleteTextView) myFragmentView.findViewById(R.id.ingredient_selectat);
		       EditText cantitate=(EditText) myFragmentView.findViewById(R.id.cantitate_ingredient);
		       Button adauga= (Button) myFragmentView.findViewById(R.id.adauga_ingredientul_selectat);
		       
		       Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Sunshine.ttf");
		       
		       adauga.setTypeface(tf);
		       ingredient.setTypeface(tf);
		       cantitate.setTypeface(tf);
		       
		       adapter = new AutocompleteIngredientAdapter(this.getActivity(), android.R.layout.simple_list_item_1, ingrediente);
				
				ingredient.setAdapter(adapter);
				ingredient.setOnItemClickListener(new OnItemClickListener() {        
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub			
						InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(view.getWindowToken(), 0);	
						
					}
		        });
				
		       
		 		
		 		return myFragmentView;
		    }
}
