package com.example.ecconomizecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manutencoes extends AppCompatActivity {
    private Button btn_voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manutencoes);

        getSupportActionBar().hide();
        iniciarComponentes();

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manutencoes.this, main_screen.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes() {
        btn_voltar = findViewById(R.id.button_voltar);
    }
}