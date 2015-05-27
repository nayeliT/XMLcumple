package com.example.rc.xmlcumple;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    // All static variables
    static final String URL = "http://eulisesrdz.260mb.net/naye/bbdd.xml";




    // XML node keys
    static final String KEY_TAG = "festejo"; // parent node
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_FECHA = "fecha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_TAG);
        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_NOMBRE, parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_FECHA, parser.getValue(e, KEY_FECHA));

            // adding HashList to ArrayList
            menuItems.add(map);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.list_fiesta, new String[]{ KEY_NOMBRE, KEY_FECHA}, new int[]{ R.id.nombre2_2, R.id.fecha2_2 });

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting values from selected ListItem
                String nom = ((TextView) view.findViewById(R.id.nombre2_2)).getText().toString();
                String fec = ((TextView) view.findViewById(R.id.fecha2_2)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), Individual_cumple.class);
                in.putExtra(KEY_NOMBRE, nom);
                in.putExtra(KEY_FECHA, fec);
                startActivity(in);

            }
        });
    }
}
