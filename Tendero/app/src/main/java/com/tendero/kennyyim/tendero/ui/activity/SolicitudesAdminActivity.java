package com.tendero.kennyyim.tendero.ui.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tendero.kennyyim.tendero.MainActivity;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.controlls.OnItemClickListener;
import com.tendero.kennyyim.tendero.controlls.ProductosAdapter;
import com.tendero.kennyyim.tendero.controlls.SolicitudAdapter;
import com.tendero.kennyyim.tendero.model.Admin;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.model.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class SolicitudesAdminActivity extends AppCompatActivity implements OnItemClickListener {

    Toolbar toolbar;

    FirebaseDatabase database;

    private RecyclerView recView;

    private SolicitudAdapter adaptador;

    private List<Solicitud> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes_admin);
        toolbar = (Toolbar) findViewById(R.id.toolbar_solicitudes);
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        DatabaseReference solicitudesRef = database.getReference(FirebaseReferences.SOLICITUDES_REFERENCE);

                recView = (RecyclerView)findViewById(R.id.rec_view_solicitudes);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(
                new LinearLayoutManager(SolicitudesAdminActivity.this,LinearLayoutManager.VERTICAL,false));

        solicitudesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("PRODUCT",dataSnapshot.toString());

                list = new ArrayList<Solicitud>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    list.add(child.getValue(Solicitud.class));
                }

                setSolicitudAdapter();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void setSolicitudAdapter(){
        adaptador = new SolicitudAdapter(list,this);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String myTag = (String)view.getTag();
                Toast.makeText(SolicitudesAdminActivity.this,String.valueOf(view.getTag()),Toast.LENGTH_LONG).show();
            }
        });
        recView.setAdapter(adaptador);
    }


    @Override
    public void goToSolicitudDetailActivity(int position)
    {
        Solicitud solicitud = list.get(position);
        Intent intent = new Intent(SolicitudesAdminActivity.this, SolicitudDetailActivity.class);
        intent.putExtra(SolicitudDetailActivity.INTENT_EXTRA_SOLICITUD, solicitud);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:
                FirebaseAuth.getInstance().signOut();
                Intent goLogin = new Intent(SolicitudesAdminActivity.this, LoginActivity.class);
                startActivity(goLogin);
                SolicitudesAdminActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
