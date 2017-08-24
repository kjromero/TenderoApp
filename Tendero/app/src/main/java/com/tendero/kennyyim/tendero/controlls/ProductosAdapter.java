package com.tendero.kennyyim.tendero.controlls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Producto;

import java.util.List;


public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder> {

    private List<Producto> datos;

    private View.OnClickListener listener;

    private OnItemClickListener onItemClickListener;

    public ProductosAdapter(List<Producto> datos,OnItemClickListener onItemClickListener) {
        this.datos = datos;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_producto, viewGroup, false);


        ProductosViewHolder tvh = new ProductosViewHolder(itemView,onItemClickListener);

        return tvh;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(ProductosViewHolder viewHolder, int pos) {
        Producto item = datos.get(pos);


        viewHolder.bindTitular(item,pos);
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
        private LinearLayout lContentProduct;

        OnItemClickListener listener;

        public ProductosViewHolder(View itemView,OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            txtxName = (TextView)itemView.findViewById(R.id.name);
            txtCantidad = (TextView)itemView.findViewById(R.id.cantidad);
            txtValor = (TextView)itemView.findViewById(R.id.valor);
            lContentProduct = (LinearLayout)itemView.findViewById(R.id.content_product);
        }

        public void bindTitular(Producto t, final int position) {
            txtxName.setText(t.getNombre());
            txtCantidad.setText(String.valueOf(t.getCantidad()));
            txtValor.setText("Valor : "+String.valueOf(t.getValor()));
            txtxName.setTag(t.getId());
            lContentProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToSolicitudDetailActivity(position);
                }
            });

        }
    }
}
