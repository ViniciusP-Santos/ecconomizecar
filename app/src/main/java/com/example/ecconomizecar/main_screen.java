package com.example.ecconomizecar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class main_screen extends AppCompatActivity {
    private TextView user_name, logout;
    private Button screen_average, screen_alcoolvsgasolina, screen_quantosKms, screen_manutencoes;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        getSupportActionBar().hide();
        initialComponents();

        screen_average.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen.this, average_screen.class);
                startActivity(intent);
            }
        });
        screen_alcoolvsgasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen.this, alcoolvsgasolina.class);
                startActivity(intent);
            }
        });
        screen_quantosKms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen.this, QuantosKms.class);
                startActivity(intent);
            }
        });

        screen_manutencoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_screen.this, manutencoes.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(main_screen.this, formLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    user_name.setText(documentSnapshot.getString("nome"));
                }
            }
        });
    }

    private void initialComponents(){
        screen_average = findViewById(R.id.buttonMedia);
        screen_alcoolvsgasolina = findViewById(R.id.buttonAlcxGas);
        screen_quantosKms = findViewById(R.id.buttonKms);
        screen_manutencoes = findViewById(R.id.buttonManutencoes);
        user_name = findViewById(R.id.user_name);
        logout = findViewById(R.id.logout);
    }
}