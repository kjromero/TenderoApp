package com.tendero.kennyyim.tendero.controlls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.model.Solicitud;

import java.util.List;

/**
 * Created by Kenny Yim on 21/08/2017.
 */

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>  implements View.OnClickListener {

    private List<Solicitud> datos;

    private View.OnClickListener listener;


    public SolicitudAdapter(List<Solicitud> datos) {
        this.datos = datos;
    }

    @Override
    public SolicitudViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_solicitud, viewGroup, false);

        itemView.setOnClickListener(this);

        SolicitudViewHolder tvh = new SolicitudViewHolder(itemView);

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
    public void onBindViewHolder(SolicitudViewHolder viewHolder, int pos) {
        Solicitud item = datos.get(pos);


        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class SolicitudViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtSolicitud;

        public SolicitudViewHolder(View itemView) {
            super(itemView);

            txtSolicitud = (TextView)itemView.findViewById(R.id.txt_solicitud);
        }

        public void bindTitular(Solicitud t) {
            txtSolicitud.setText(t.getTextSolicitud());


        }
    }
}
