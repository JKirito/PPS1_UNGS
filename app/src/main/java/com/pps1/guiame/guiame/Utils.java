package com.pps1.guiame.guiame;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Created by javi on 25/03/15.
 */
public class Utils {

    public static String getPHPResult(String phpName){
        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet("http://10.0.2.2/guiame/"+phpName+".php");
        String resultado=null;
        try {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
            Log.d("RESULTADO",resultado);
        } catch (Exception e) {
            Log.d("EXCEPTION AL LEER",e+"");
            // TODO: handle exception
        }
        return resultado;
    }
}
