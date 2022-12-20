package com.example.ecconomizecar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class QuantosKms extends AppCompatActivity {
    private EditText input_kmLitro, input_KmPercorrer, input_valLitro;
    private TextView text_result;
    private Button button_calcular, button_verMedias;
    String[] message = {"Preencha o campo KM por litro","Preencha o campo Km irá percorrer", "Preencha o campo de valor por litro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantos_kms);

        getSupportActionBar().hide();
        initialComponents();

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtKms = input_kmLitro.getText().toString();
                String txtKmPercorrer = input_KmPercorrer.getText().toString();
                String txtValLitro = input_valLitro.getText().toString();

                if(txtKms.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else if(txtKmPercorrer.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else if(txtValLitro.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, message[2], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else{
                    float kms = Float.parseFloat(txtKms);
                    float kmPercorrer = Float.parseFloat(txtKmPercorrer);
                    float valLitro = Float.parseFloat(txtValLitro);

                    float result = (kmPercorrer/kms)*valLitro;
                    String resultado = String.format("%.02f", result);
                    text_result.setText("A você ira gastar: R$"+resultado);
                    input_kmLitro.setText(null);
                    input_KmPercorrer.setText(null);
                    input_valLitro.setText(null);
                }
            }
        });
    }
    private void initialComponents(){
        button_calcular = findViewById(R.id.buttonCalcularMedia);
        input_kmLitro = findViewById(R.id.kmLitro);
        input_KmPercorrer = findViewById(R.id.kmPercorrer);
        input_valLitro = findViewById(R.id.valLitro);
        text_result = findViewById(R.id.text_result);

    }

}