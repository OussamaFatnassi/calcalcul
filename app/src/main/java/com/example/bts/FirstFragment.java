package com.example.bts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.bts.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ListView list;
    private TextView totText;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            list = (ListView) getView().findViewById(R.id.menulist);

            totText = (TextView) getView().findViewById(R.id.total);

            if(getArguments() != null ) {
                if(getArguments().getString("nom")!="asd") {
                    String nom = getArguments().getString("nom");
                    float poid = getArguments().getFloat("poid");
                    int cal = getArguments().getInt("cal");
                    int liquid = getArguments().getInt("liquid");
                    String tmpPoid = (poid * cal) + " cal";

                    String tmpCal = String.valueOf(getArguments().getInt("cal"));

                    if (liquid == 1) {
                        tmpCal += " cal/100ml";
                    } else {
                        tmpCal += " cal/100g";
                    }
                    menuAlim aliment = new menuAlim(nom, tmpPoid, tmpCal);

                    ((MainActivity) getContext()).setAliment(aliment);

                    //Afficher total calories
                    int totCal = Math.round(poid * cal);

                    int totalTmp = ((MainActivity) getContext()).getTotal() + totCal;

                    ((MainActivity) getContext()).setTotal(totalTmp);

                    totText.setText(String.valueOf(totalTmp) + " cal");
                }
            }

            List<menuAlim> aliments = ((MainActivity)getContext()).getAliments();

            list.setAdapter(new menuAdapter(getContext(),aliments));

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}