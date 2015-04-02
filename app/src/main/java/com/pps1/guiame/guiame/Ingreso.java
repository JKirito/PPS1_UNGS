package com.pps1.guiame.guiame;

/**
 * Created by Agustina on 25/03/2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;


public class Ingreso extends ActionBarActivity
{

    private EditText txtDni;
    private EditText txtContraseña;
    private Button btnAceptar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        //Obtenemos una referencia a los controles de la interfaz
        txtDni = (EditText)findViewById(R.id.txtDni);
        txtContraseña = (EditText)findViewById(R.id.txtContraseña);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);

        //Implementamos el evento “click” del botón
        btnAceptar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Ingresador ingresador = new Ingresador(txtDni.getText().toString(), txtContraseña.getText().toString());
                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run()
                    {
                        try
                        {
                            final List<String> errores = ingresador.ingresarUsuario();

                            if(errores.size() > 0)
                            {
                                runOnUiThread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getApplicationContext(),
                                                        errores.get(0), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                return;
                            }

                            Bundle bundle = new Bundle();
                            bundle.putString("dni", txtDni.getText().toString());

                            Intent intent = new Intent(getApplicationContext(), ListaMateriasUsuario.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creamos el Intent
                Intent intent =
                        new Intent(Ingreso.this, Principal.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume()
   {
       super.onResume();

       txtDni.setText("");
       txtContraseña.setText("");
    }




}