package com.pps1.guiame.guiame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends Activity
{
    private Button btnRegistrarse;
    private Button btnIngresar;
    private Button btnVistaRapida;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        btnRegistrarse = (Button)findViewById(R.id.btnRegistrarse);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnVistaRapida = (Button)findViewById(R.id.btnVistaRapida );

        btnRegistrarse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creamos el Intent
                Intent intent =
                        new Intent(Principal.this, Registro.class);
                startActivity(intent);
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creamos el Intent
                Intent intent =
                        new Intent(Principal.this, Ingreso.class);
                startActivity(intent);
            }
        });
        btnVistaRapida.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creamos el Intent
                Intent intent =
                        new Intent(Principal.this, ListaMaterias.class);
                startActivity(intent);
            }
        });
    }
}