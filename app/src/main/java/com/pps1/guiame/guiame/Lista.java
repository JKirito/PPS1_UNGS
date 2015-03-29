package com.pps1.guiame.guiame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.util.ArrayList;


public class Lista extends ActionBarActivity
{

    private final String PHP_NAME_LISTADOR = "listarMaterias.php";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Thread tr = new Thread(){
            @Override
            public void run(){
                final String Resultado = Utils.getPHPResult(PHP_NAME_LISTADOR);
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                cargaListado(obtDatosJSON(Resultado));
                            }
                        });
            }
        };
        tr.start();
    }


    public ArrayList<String> obtDatosJSON(String response)
    {
        ArrayList<String> listado= new ArrayList<String>();
        try {
            JSONArray json= new JSONArray(response);
            String texto;
            for (int i=0; i<json.length();i++)
            {
                texto = json.getJSONObject(i).getString("id") +" - "+
                        json.getJSONObject(i).getString("nombre") +" - "+
                        json.getJSONObject(i).getString("mail") +" - "+
                        json.getJSONObject(i).getString("dni") +" - "+
                        json.getJSONObject(i).getString("contrasena");
                Log.d("texto",texto);
                listado.add(texto);
            }
        }
        catch (Exception e)
        {
            Log.d("EXCEPCION obtDatosJSON", e+"");
        }
        return listado;
    }

    public void cargaListado(ArrayList<String> datos)
    {
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.listView1);
        listado.setAdapter(adaptador);
    }
}
