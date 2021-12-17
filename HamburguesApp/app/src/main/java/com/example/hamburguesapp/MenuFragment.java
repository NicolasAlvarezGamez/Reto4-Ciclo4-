package com.example.hamburguesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "MenuFragment";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button combo1;
    Button combo2;
    Button combo3;
    Button adminproducts;
    TextView txtview;
    TextView txtview2;
    LinearLayout linearLayout;
    LinearLayout linearLayout1;
    LinearLayout linearLayout2;
    LinearLayout linearLayout3;


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        vista = inflater.inflate(R.layout.fragment_menu, container, false);

        combo1 = vista.findViewById(R.id.combo1);
        combo2 = vista.findViewById(R.id.combo2);
        combo3 = vista.findViewById(R.id.combo3);
        adminproducts = vista.findViewById(R.id.adminProductos);
        txtview = vista.findViewById(R.id.txttitle_menu);
        txtview2 = vista.findViewById(R.id.gracias_esconder);
        linearLayout = vista.findViewById(R.id.ocultar);
        linearLayout1 = vista.findViewById(R.id.ocultar1);
        linearLayout2 = vista.findViewById(R.id.ocultar2);
        linearLayout3 = vista.findViewById(R.id.ocultar3);

        combo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.linearLayoutaReemplazar, sin_excusas_c1.newInstance("",""));

                // Commit the transaction
                transaction.commit();

                //Ocultar
                combo1.setVisibility(View.GONE);
                adminproducts.setVisibility(View.GONE);
                txtview.setVisibility(View.GONE);
                txtview2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);


            }
        });

        combo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.linearLayoutaReemplazar, fragment_cebolla_bbq.newInstance("",""));

                // Commit the transaction
                transaction.commit();

                //Ocultar
                combo1.setVisibility(View.GONE);
                adminproducts.setVisibility(View.GONE);
                txtview.setVisibility(View.GONE);
                txtview2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);


            }
        });

        combo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.linearLayoutaReemplazar, fragment_the_queen.newInstance("",""));

                // Commit the transaction
                transaction.commit();

                //Ocultar
                combo1.setVisibility(View.GONE);
                adminproducts.setVisibility(View.GONE);
                txtview.setVisibility(View.GONE);
                txtview2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);


            }
        });

        adminproducts.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuFragment.this.getContext(),activity_admin_productos.class);
            startActivity(intent);
        }
    });

        return vista;
}

}