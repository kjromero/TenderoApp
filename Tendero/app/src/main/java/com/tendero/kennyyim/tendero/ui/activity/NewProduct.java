package com.tendero.kennyyim.tendero.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.utils.RandomString;

public class NewProduct extends AppCompatActivity {

    FirebaseDatabase database;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
       final  DatabaseReference myRef = database.getReference(FirebaseReferences.PRODUCTOS_REFERENCE);

        final RandomString randomString = new RandomString();

        final EditText edtName = (EditText)findViewById(R.id.edt_name_product);
        final EditText edtCantidad = (EditText)findViewById(R.id.edt_cantidad);
        final EditText edtValor = (EditText)findViewById(R.id.edt_value);


        Button btnNew = (Button)findViewById(R.id.btn_new_product);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto newProducto = new Producto(randomString.nextString(),edtName.getText().toString(),Integer.parseInt(edtCantidad.getText().toString()),Double.parseDouble(edtValor.getText().toString()));
                myRef.child(randomString.nextString()).setValue(newProducto);
                NewProduct.this.finish();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                NewProduct.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
