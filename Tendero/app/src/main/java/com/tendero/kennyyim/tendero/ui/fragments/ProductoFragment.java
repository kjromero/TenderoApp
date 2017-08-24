package com.tendero.kennyyim.tendero.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.tendero.kennyyim.tendero.MainActivity;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.controlls.OnItemClickListener;
import com.tendero.kennyyim.tendero.controlls.ProductosAdapter;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.model.Solicitud;
import com.tendero.kennyyim.tendero.ui.activity.NewProduct;
import com.tendero.kennyyim.tendero.ui.activity.ProductoDetailActivity;
import com.tendero.kennyyim.tendero.ui.activity.SolicitudDetailActivity;
import com.tendero.kennyyim.tendero.ui.activity.SolicitudesAdminActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductoFragment extends Fragment  implements OnItemClickListener {

    FirebaseDatabase database;

    private RecyclerView recView;

    private ArrayList<Producto> datos;

    List<Producto> list;

    private ProductosAdapter adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Productos");

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseReferences.PRODUCTOS_REFERENCE);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goNew = new Intent(getActivity(), NewProduct.class);
                startActivity(goNew);
            }
        });

        recView = (RecyclerView)view.findViewById(R.id.rec_view);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(
                new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("PRODUCT",dataSnapshot.toString());

                list = new ArrayList<Producto>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    list.add(child.getValue(Producto.class));
                }

               adaptador = new ProductosAdapter(list,ProductoFragment.this);

                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       //String myTag = (String)view.getTag();
                     //   Toast.makeText(getActivity(),myTag,Toast.LENGTH_LONG).show();
                    }
                });
                recView.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void goToSolicitudDetailActivity(int position) {
        Producto producto = list.get(position);
        Intent intent = new Intent(getActivity(), ProductoDetailActivity.class);
        intent.putExtra(ProductoDetailActivity.INTENT_EXTRA_PRODUCTO, producto);
        startActivity(intent);
    }
}
