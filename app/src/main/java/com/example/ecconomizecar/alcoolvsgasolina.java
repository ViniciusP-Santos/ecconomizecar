package com.example.ecconomizecar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class alcoolvsgasolina extends AppCompatActivity {
    private EditText input_alcool, input_gasolina;
    private TextView text_result;
    private Button button_calcular;
    String[] message = {"Preencha o campo preço gasolina","Preencha o campo preço alcool"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcoolvsgasolina);

        getSupportActionBar().hide();
        initialComponents();

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtAlcool = input_alcool.getText().toString();
                String txtGasolina = input_gasolina.getText().toString();

                if (txtAlcool.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else if (txtGasolina.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else {

                }
                float ltAlcool = Float.parseFloat(txtAlcool);
                float ltGasolina = Float.parseFloat(txtGasolina);

                float result = ltAlcool / ltGasolina;
                String resultado;
                if (result < 0.7) {
                    text_result.setText("Alcool é mais \nvantajoso no momento!");
                } else {
                    text_result.setText("Gasolina é mais \nvantajosa no momento!");
                }
                input_alcool.setText(null);
                input_gasolina.setText(null);
            }
        });
    }

    private void initialComponents(){
        button_calcular = findViewById(R.id.buttonCalcularMedia);
        input_alcool= findViewById(R.id.input_alcool);
        input_gasolina = findViewById(R.id.input_gasolina);
        text_result = findViewById(R.id.text_result);
    }
}