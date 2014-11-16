package com.proiect.qmasura;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.proiect.qmasura.obiecte.Meniu;
import com.proiect.qmasura.utilitare.UtilitarBackground;

public class ListareMeniuriFragment extends Fragment {
	private View myFragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ListareMeniuriAdapter adaptor;
	private ListView listare;
	private Button adauga;
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		myFragmentView=inflater.inflate(R.layout.listare_meniuri, container, false);
		       
		 		listare= (ListView) myFragmentView.findViewById(R.id.listare_meniuri);
		 		
		 		adauga=(Button) myFragmentView.findViewById(R.id.adauga_meniu);
		 		
		 		Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
			                "fonts/Sunshine.ttf");
		 		adauga.setTypeface(tf);
		 		
		 		ArrayList<Meniu> meniuri= new ArrayList<Meniu>();
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		meniuri.add(new Meniu("Meniul meu",""));
		 		
		 		Toast.makeText(getActivity(), meniuri.size()+"" , Toast.LENGTH_SHORT).show();
		 		
		 		adaptor= new ListareMeniuriAdapter(this.getActivity().getApplicationContext(),meniuri);
		 		
		 		listare.setAdapter(adaptor);
		 		
		 		listare.setOnItemSelectedListener(new OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "Va urma", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}});
		 		
		        return myFragmentView;
		    }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }

}

