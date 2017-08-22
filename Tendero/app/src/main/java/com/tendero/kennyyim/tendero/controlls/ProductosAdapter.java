package com.tendero.kennyyim.tendero.controlls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Producto;

import java.util.List;


public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder>  implements View.OnClickListener {

    private List<Producto> datos;

    private View.OnClickListener listener;


    public ProductosAdapter(List<Producto> datos) {
        this.datos = datos;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_producto, viewGroup, false);

        itemView.setOnClickListener(this);

        ProductosViewHolder tvh = new ProductosViewHolder(itemView);

        return tvh;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder viewHolder, int pos) {
        Producto item = datos.get(pos);


        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ProductosViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtxName;
        private TextView txtCantidad;
        private TextView txtValor;

        public ProductosViewHolder(View itemView) {
            super(itemView);

            txtxName = (TextView)itemView.findViewById(R.id.name);
            txtCantidad = (TextView)itemView.findViewById(R.id.cantidad);
            txtValor = (TextView)itemView.findViewById(R.id.valor);
        }

        public void bindTitular(Producto t) {
            txtxName.setText(t.getNombre());
            txtCantidad.setText(String.valueOf(t.getCantidad()));
            txtValor.setText("Valor : "+String.valueOf(t.getValor()));
            txtxName.setTag(t.getId());


        }
    }
}
