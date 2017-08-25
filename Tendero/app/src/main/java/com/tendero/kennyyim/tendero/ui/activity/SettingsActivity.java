package com.tendero.kennyyim.tendero.ui.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Solicitud;
import com.tendero.kennyyim.tendero.utils.RandomString;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference refSolicitudes = database.getReference(FirebaseReferences.SOLICITUDES_REFERENCE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TypedValue typedValueColorPrimaryDark = new TypedValue();
        SettingsActivity.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }

        final EditText editText = (EditText)findViewById(R.id.edt_text_solicitud);
        Button btnSolicitud = (Button)findViewById(R.id.btn_enviar_solicitud);

        btnSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandomString randomString = new RandomString();
                String id= randomString.nextString();
                SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
                Date currentTime = Calendar.getInstance().getTime();
                Solicitud solicitud = new Solicitud(id,editText.getText().toString(),"",postFormater.format(currentTime) ,"","Sin asignar","");

                refSolicitudes.child(id).setValue(solicitud);
                SettingsActivity.this.finish();
            }
        });


        //refSolicitudes.child(i.getId()+String.valueOf(conunt)).setValue(i);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                SettingsActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
