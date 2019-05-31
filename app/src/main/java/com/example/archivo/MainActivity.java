package com.example.archivo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTexto;
    Button btnGuardarDatos;
    Button btnRecuperarDatos;
    String nombreFichero;
    ArrayList<String> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtTexto = findViewById(R.id.edtTexto);
        btnGuardarDatos = findViewById(R.id.btnGuardar);
        btnRecuperarDatos = findViewById(R.id.btnRecuperar);

        nombreFichero = "notas.txt";
        listaNotas = new ArrayList<>();

        leer();



        btnGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mando los datos a grabar", Toast.LENGTH_LONG).show();
                grabar();
            }
        });

        btnRecuperarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leer();
            }
        });
    }

    public void grabar(){

        try {
            FileOutputStream fos = getApplicationContext().openFileOutput(nombreFichero, Context.MODE_APPEND);
            fos.write(edtTexto.getText().toString().getBytes());
            fos.close();
        }catch(Exception e){
            Log.e("Activity Ficheros", e.getMessage(), e);
        }
        Toast.makeText(this, "Se han grabado los datos", Toast.LENGTH_LONG).show();
    }

    public void leer(){
        listaNotas.clear();
        try {
            FileInputStream fis = getApplicationContext().openFileInput(nombreFichero);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(fis));
            String linea;
            do{
                linea = entrada.readLine();
                if(linea != null){
                    listaNotas.add(linea);
                }
            }while(linea != null);
        }catch (Exception e){
            Log.e("Activity Ficheros", e.getMessage(), e);
        }

        String mostrar = "";
        for(int i = 0 ; i < listaNotas.size() ; i++){
            mostrar += listaNotas.get(i) + "\n";
        }
        edtTexto.setText(mostrar);
    }
}
