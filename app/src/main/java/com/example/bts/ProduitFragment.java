package com.example.bts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.bts.databinding.FragmentProduitBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProduitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProduitFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private FragmentProduitBinding binding;
    private SQLiteHelper helper;
    private EditText nom;
    private EditText description;
    private EditText kcal;
    private CheckBox liquidCheckBox;
    private int liquid;
    private Aliment aliment;
    public ProduitFragment() {
        // Required empty public constructor
    }

    public static ProduitFragment newInstance(String param1, String param2) {
        ProduitFragment fragment = new ProduitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentProduitBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.validerProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aliment = new Aliment();
                helper = new SQLiteHelper(getActivity().getApplicationContext());
                nom = (EditText) getView().findViewById(R.id.nomProduit);
                description = (EditText) getView().findViewById(R.id.descriptionProduit);
                kcal = (EditText) getView().findViewById(R.id.caloriesProduit);
                liquidCheckBox = (CheckBox) getView().findViewById(R.id.liquidOrSolid);

                if (liquidCheckBox.isChecked()){
                    liquid = 1;
                } else {
                    liquid = 0;
                }

                int cal = Integer.parseInt(String.valueOf(kcal.getText()));

                aliment.setCalories(cal);
                aliment.setNom(String.valueOf(nom.getText()));
                aliment.setDescription(String.valueOf(description.getText()));
                aliment.setSolid(liquid);
                helper.addAlim(aliment);
                Log.i("blue", aliment.getNom()+aliment.getCalories());
                NavHostFragment.findNavController(ProduitFragment.this)
                        .navigate(R.id.action_produitFragment_to_SecondFragment);
            }
        });
    }
}