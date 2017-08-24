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
import com.tendero.kennyyim.tendero.model.User;

import java.util.ArrayList;
import java.util.List;

public class RedirectActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private DatabaseReference userRef;

    FirebaseDatabase database;

    private List<User> listAUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(FirebaseReferences.USERS_REFERENCE);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAUsers = new ArrayList<User>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    listAUsers.add(child.getValue(User.class));
                }

                mAuth = FirebaseAuth.getInstance();

                FirebaseUser user = mAuth.getCurrentUser();


                for (User currentUser : listAUsers){
                    if (currentUser.getEmail().equals(user.getEmail())){
                        if (currentUser.getRol().equals("admin")){
                            Intent goSolicitudes = new Intent(RedirectActivity.this, SolicitudesAdminActivity.class);
                            startActivity(goSolicitudes);
                            RedirectActivity.this.finish();
                            return;
                        }
                        if (currentUser.getRol().equals("tendero")){
                            Intent goMAin = new Intent(RedirectActivity.this, MainActivity.class);
                            startActivity(goMAin);
                            RedirectActivity.this.finish();
                            return;
                        }
                        if (currentUser.getRol().equals("soporte")){
                            Intent goSoporteActivity = new Intent(RedirectActivity.this, SoporteActivity.class);
                            startActivity(goSoporteActivity);
                            RedirectActivity.this.finish();
                            return;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
