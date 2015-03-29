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

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run()
                    {
                    try
                    {
                        Ingreso.this.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                Ingresador ingresador = new Ingresador(txtDni.getText().toString(), txtContraseña.getText().toString());
                                List<String> errores = ingresador.validarDatos();

                                if(errores.size() > 0)
                                {
                                    Toast.makeText(getApplicationContext(),
                                            "Usuario incorrecto!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                ingresador.ingresarUsuario();

                                Intent intent = new Intent(Ingreso.this, ListaMaterias.class);
                                startActivity(intent);
                            }
                        });
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
}