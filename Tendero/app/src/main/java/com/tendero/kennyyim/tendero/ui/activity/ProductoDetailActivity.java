package com.tendero.kennyyim.tendero.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.model.Solicitud;

public class ProductoDetailActivity extends AppCompatActivity {

    public final static String INTENT_EXTRA_PRODUCTO = "PRO_DETAIL";

    Toolbar toolbar;

    FirebaseDatabase database;

    DatabaseReference refProductos;

    private EditText edtNombreProducto;
    private EditText edtCantidadProducto;
    private EditText edtValorProducto;
    private Button btnEditar;
    private Button btnEliminar;

    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar_producto_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        producto = (Producto) getIntent().getSerializableExtra(INTENT_EXTRA_PRODUCTO);

        database = FirebaseDatabase.getInstance();
        refProductos = database.getReference(FirebaseReferences.PRODUCTOS_REFERENCE);

        edtNombreProducto = (EditText)findViewById(R.id.edt_nombre_producto);
        edtCantidadProducto = (EditText)findViewById(R.id.edt_cantidad_producto);
        edtValorProducto = (EditText)findViewById(R.id.edt_valor_producto);

        btnEditar = (Button)findViewById(R.id.btn_editar);
        btnEliminar = (Button)findViewById(R.id.btn_eliminar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.setNombre(edtNombreProducto.getText().toString());
                producto.setCantidad(Integer.parseInt(edtCantidadProducto.getText().toString()));
                producto.setValor(Double.parseDouble(edtValorProducto.getText().toString()));

                refProductos.child(producto.getId()).setValue(producto);
                ProductoDetailActivity.this.finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refProductos.child(producto.getId()).removeValue();
                ProductoDetailActivity.this.finish();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                ProductoDetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
