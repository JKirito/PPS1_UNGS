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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("Inicio","sea crea second activity");
        setContentView(R.layout.activity_lista);
        Thread tr = new Thread(){
            @Override
            public void run(){
                final String Resultado = leer();
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

    public String leer(){
        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet("http://10.0.2.2/guiame/logineame.php");
        Log.d("LEER HTTPGET","leeeer "+httpget );
        String resultado=null;
        try {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            Log.d("ENTITY",httpget+"");
            resultado = EntityUtils.toString(entity, "UTF-8");
            Log.d("resulatadoooo!!",resultado);
        } catch (Exception e) {
            Log.d("EXCEPTION",e+"");
            // TODO: handle exception
        }
        return resultado;
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
        } catch (Exception e) {
            Log.d("EXCEPCION obtDatosJSON", e+"");
            // TODO: handle exception
        }
        return listado;
    }

    public void cargaListado(ArrayList<String> datos){
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        ListView listado = (ListView) findViewById(R.id.listView1);
        listado.setAdapter(adaptador);
    }
}
