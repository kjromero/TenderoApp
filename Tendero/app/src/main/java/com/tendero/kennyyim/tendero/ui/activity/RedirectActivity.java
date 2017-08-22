package com.tendero.kennyyim.tendero.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tendero.kennyyim.tendero.MainActivity;
import com.tendero.kennyyim.tendero.R;
import com.tendero.kennyyim.tendero.model.Admin;
import com.tendero.kennyyim.tendero.model.FirebaseReferences;

import java.util.ArrayList;
import java.util.List;

public class RedirectActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private DatabaseReference adminsRef;

    FirebaseDatabase database;

    private List<Admin> listAdmins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        database = FirebaseDatabase.getInstance();
        adminsRef = database.getReference(FirebaseReferences.ADMIN_REFERENCE);

        adminsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAdmins = new ArrayList<Admin>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    listAdmins.add(child.getValue(Admin.class));
                }


                mAuth = FirebaseAuth.getInstance();

                FirebaseUser user = mAuth.getCurrentUser();

                boolean isAdmin = false;

                for (Admin admin : listAdmins){
                    if (admin.getEmail().equals(user.getEmail())){
                        isAdmin = true;
                        Intent goSolicitudes = new Intent(RedirectActivity.this, SolicitudesAdminActivity.class);
                        startActivity(goSolicitudes);
                        RedirectActivity.this.finish();
                        break;
                    }
                }

                if (!isAdmin){
                    Intent goMAin = new Intent(RedirectActivity.this, MainActivity.class);
                    startActivity(goMAin);
                    RedirectActivity.this.finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
