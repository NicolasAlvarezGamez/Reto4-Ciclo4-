package com.example.hamburguesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sin_excusas_c1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sin_excusas_c1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button btnañadir;

    public sin_excusas_c1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sin_excusas_c1.
     */
    // TODO: Rename and change types and number of parameters
    public static sin_excusas_c1 newInstance(String param1, String param2) {
        sin_excusas_c1 fragment = new sin_excusas_c1();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_sin_excusas_c1, container, false);
        btnañadir = (Button) vista.findViewById(R.id.añadir_sin_excusas);
        btnañadir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista) {
                Toast.makeText(getContext(), "Se Añadirá la Opción de Añadir", Toast.LENGTH_LONG).show();
            }
        });
        return vista;
    }

}