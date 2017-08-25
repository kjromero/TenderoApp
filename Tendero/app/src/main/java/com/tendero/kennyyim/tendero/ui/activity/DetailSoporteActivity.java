package com.tendero.kennyyim.tendero.ui.activity;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;
import com.tendero.kennyyim.tendero.model.Solicitud;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DetailSoporteActivity extends AppCompatActivity {

    final public static String INTENT_EXTRA_SOLICITUD = "SLT_DETAIL";

    Toolbar toolbar;

    private Button btnAsignar;
    private TextView txtAsignado;
    private EditText edtCommentSolicitud;


    private String correo;
    private String contrasena;

    private Session session;

    FirebaseDatabase database;

    DatabaseReference refSolicitudes;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_soporte);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        refSolicitudes = database.getReference(FirebaseReferences.SOLICITUDES_REFERENCE);


        toolbar = (Toolbar) findViewById(R.id.toolbar_soporte_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Solicitud solicitud = (Solicitud)getIntent().getSerializableExtra(INTENT_EXTRA_SOLICITUD);

        btnAsignar = (Button)findViewById(R.id.btn_finalizar);
        txtAsignado = (TextView)findViewById(R.id.txt_asginado);
        TextView txtFInicio= (TextView)findViewById(R.id.txt_fecha_inicio);
        TextView txtFFin = (TextView)findViewById(R.id.txt_fecha_fin);
        edtCommentSolicitud = (EditText)findViewById(R.id.edt_comment);

        txtFFin.setText(solicitud.getFechaFinal());
        txtFInicio.setText(solicitud.getFechaInicio());

        txtAsignado.setText(solicitud.getResponsable());

        correo = "sastoque.kenny@gmail.com";
        contrasena = "kenny123456";

        TextView txtV = (TextView)findViewById(R.id.txt_solicitud);
        txtV.setText(solicitud.getTextSolicitud());

        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties  = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                try{
                    session = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo,contrasena);
                        }
                    });

                    if (session != null){

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(correo));
                        message.setSubject("Solicitud Finalizada ");
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mAuth.getCurrentUser().getEmail()));
                        message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(correo));

                        message.setContent("Usted ha registrado como finalizada la solicitud :"+solicitud.getTextSolicitud(),"text/html; charset=utf-8");
                        Transport.send(message);

                        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");
                        Date currentTime = Calendar.getInstance().getTime();
                        solicitud.setFechaFinal(postFormater.format(currentTime));
                        solicitud.setComentarioSoporte(edtCommentSolicitud.getText().toString());
                        solicitud.setEstado("Finalizada");

                        refSolicitudes.child(solicitud.getId()).setValue(solicitud);
                        DetailSoporteActivity.this.finish();


                    }
                }catch (Exception e ){
                    e.printStackTrace();
                }



            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                DetailSoporteActivity.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
