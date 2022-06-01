package com.example.bts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.bts.databinding.FragmentSecondBinding;

import java.util.List;

public class SecondFragment extends Fragment {
    private ListView list;
    private List<Aliment> aliments;
    private FragmentSecondBinding binding;
    private SQLiteHelper helper;
    private TextView listevide;
    private EditText poid;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (ListView) getView().findViewById(R.id.textview_second);
        helper = new SQLiteHelper(getActivity().getApplicationContext());
        aliments = helper.getAllAliment();
        listevide = (TextView) getView().findViewById(R.id.listevide);

        list.setAdapter(new ListAlimentsAdapt(getContext(),aliments));

        poid = (EditText) getView().findViewById(R.id.poid);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int check=0;
                String checkPoid = poid.getText().toString();

                if(checkPoid.matches("") || Integer.parseInt(binding.poid.getText().toString())<1) {
                    return;
                }
                float tmpPoid = (Float.parseFloat(String.valueOf(poid.getText())))/100;
                Bundle bundle = new Bundle();
                bundle.putFloat("poid", tmpPoid);
                bundle.putString("nom", helper.getOneAlim(i+1).getNom());
                bundle.putInt("cal", helper.getOneAlim(i+1).getCalories());
                bundle.putInt("liquid", helper.getOneAlim(i+1).getSolid());

                NavHostFragment.findNavController(SecondFragment.this).
                        navigate(R.id.action_SecondFragment_to_FirstFragment, bundle);
            }
        });

        if(aliments.isEmpty()) {
            listevide.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            list.setAdapter(new ListAlimentsAdapt(getActivity().getApplicationContext(), aliments));
        }
        binding.toProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_produitFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}