package com.tendero.kennyyim.tendero.controlls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Producto;
import com.tendero.kennyyim.tendero.model.Solicitud;

import java.util.List;

/**
 * Created by Kenny Yim on 21/08/2017.
 */

public class SolicitudAdapter extends RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder> {

    private List<Solicitud> datos;

    private View.OnClickListener listener;

    private int globalPosition;

    private OnItemClickListener onItemClickListener;

    public SolicitudAdapter(List<Solicitud> datos,OnItemClickListener onItemClickListener) {
        this.datos = datos;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public SolicitudViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_solicitud, viewGroup, false);

        SolicitudViewHolder tvh = new SolicitudViewHolder(itemView,onItemClickListener);

        return tvh;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(SolicitudViewHolder viewHolder, int pos) {
        Solicitud item = datos.get(pos);
        globalPosition = pos;

        viewHolder.bindTitular(item,pos);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class SolicitudViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtSolicitud;
        private TextView txtResponsable;
        private TextView txtFechaInicio;
        private TextView txtFechaFin;
        private LinearLayout lContent;

        OnItemClickListener listener;

        public SolicitudViewHolder(View itemView,OnItemClickListener listener) {
            super(itemView);

            this.listener = listener;
            txtResponsable = (TextView)itemView.findViewById(R.id.txt_responsable);
            txtSolicitud = (TextView)itemView.findViewById(R.id.txt_solicitud);
            lContent = (LinearLayout)itemView.findViewById(R.id.content_solicitud);
            txtFechaInicio = (TextView)itemView.findViewById(R.id.txt_recha_inicio);
            txtFechaFin = (TextView)itemView.findViewById(R.id.txt_recha_fin);

        }

        public void bindTitular(Solicitud t, final int position) {
            txtSolicitud.setText(t.getTextSolicitud());
            txtSolicitud.setTag(position);
            txtFechaInicio.setText("Fecha Creacion Solicitud : " +t.getFechaInicio());
            txtFechaFin.setText("Fecha Finalizacion Solicitud :" +t.getFechaFinal());

            if (t.getResponsable() !=null && !t.getResponsable().equals("")){
                txtResponsable.setText(t.getResponsable());
            }else{
                txtResponsable.setText("Sin Asignar");
            }

            lContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToSolicitudDetailActivity(position);
                }
            });

        }
    }
}
