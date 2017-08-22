package com.tendero.kennyyim.tendero.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Solicitud;

public class SolicitudDetailActivity extends AppCompatActivity {

    final public static String INTENT_EXTRA_SOLICITUD = "SLT_DETAIL";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar_solicitud_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Solicitud solicitud = (Solicitud)getIntent().getSerializableExtra(INTENT_EXTRA_SOLICITUD);

        TextView txtV = (TextView)findViewById(R.id.txt_solicitud);
        txtV.setText(solicitud.getTextSolicitud());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                SolicitudDetailActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
