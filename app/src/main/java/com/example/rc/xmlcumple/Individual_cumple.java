package com.example.rc.xmlcumple;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Individual_cumple extends Activity {

    static final String KEY_NOMBRE = "nombre";
    static final String KEY_FECHA = "fecha";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cumple_individual);

        // getting intent data
        Intent in = getIntent();

        // Get XML values from previous inten
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String fecha = in.getStringExtra(KEY_FECHA);


        // Displaying all values on the screen
        TextView nom = (TextView) findViewById(R.id.nombre2);
        TextView fec = (TextView) findViewById(R.id.fecha2);


        nom.setText(nombre);
        fec.setText(fecha);
    }
}
