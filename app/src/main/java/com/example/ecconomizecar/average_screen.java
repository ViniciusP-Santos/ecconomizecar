package com.example.ecconomizecar;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class average_screen extends AppCompatActivity {
    SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
    Date data = new Date();
    private EditText input_KmRodados, input_LitrosAbastecidos;
    private TextView text_result;
    private Button button_calcular, button_verMedias;
    String[] message = {"Preencha o campo KM Rodado","Preencha o campo Litros abastecidos"};
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_screen);

        getSupportActionBar().hide();
        initialComponents();


        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtKms = input_KmRodados.getText().toString();
                String txtLitros = input_LitrosAbastecidos.getText().toString();

                if(txtKms.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, message[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else if(txtLitros.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, message[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.RED);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }else{
                    float kms = Float.parseFloat(txtKms);
                    float litros = Float.parseFloat(txtLitros);

                    float result = kms/litros;
                    String resultado = String.format("%.02f", result);
                    text_result.setText("A média é: "+resultado);
                    SaveUserData(resultado, kms, litros);
                    input_KmRodados.setText(null);
                    input_LitrosAbastecidos.setText(null);
                }
            }
        });

        button_verMedias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(average_screen.this, average_list.class);
                startActivity(intent);
            }
        });
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onStart() {
        super.onStart();

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }

    private void getAverageData(){
        db.collection(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    private void SaveUserData(String result, Float kms, Float litros){
        String dataFormatada = formataData.format(data);

        Map<String,Object> medias = new HashMap<>();
        medias.put("data", dataFormatada);
        medias.put("media", result);
        medias.put("kms", kms);
        medias.put("litros", litros);



        DocumentReference documentReference = db.collection(userId).document();
        documentReference.set(medias).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao salvar os Dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error","Erro ao salvar os dados"+e.toString());
            }
        });
    }

    private void initialComponents(){
        button_calcular = findViewById(R.id.buttonCalcularMedia);
        button_verMedias = findViewById(R.id.button_verMedias);
        input_LitrosAbastecidos = findViewById(R.id.input_LitrosAbastecidos);
        input_KmRodados = findViewById(R.id.input_KmRodados);
        text_result = findViewById(R.id.text_result);
    }
}