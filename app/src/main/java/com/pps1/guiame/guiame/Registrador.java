package com.pps1.guiame.guiame;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by javi on 25/03/15.
 */
public class Registrador
{
    private String nombreYApellido;
    private String dni;
    private String mail;
    private String pass;
    private String pass2;

    private final String MSJ_NOM_APELLINVALIDO = "Nombre y Apellido inválido";
    private final String MSJ_NOM_APELL_CORTO = "Nombre y Apellido demasiado corto";
    private final String MSJ_DNI_INVALIDO = "DNI invalido";
    private final String MSJ_MAIL_INVALIDO = "Mail invalido";
    private final String MSJ_PASS_CORTA = "La contraseña debe tener al menos 4 caracteres";
    private final String MSJ_PASS_NULL = "Debe ingresar la contraseña";
    private final String MSJ_PASS_NOCOINCIDE = "Las contraseñas no coinciden";

    private final String PHP_NAME_REGISTRADOR = "registrarUsuario.php";
    private final String PHP_RESULT_OK = "OK";


    public Registrador(String nombreYApellido, String dni, String mail, String pass, String pass2)
    {
        this.nombreYApellido = nombreYApellido;
        this.dni = dni;
        this.mail = mail;
        this.pass = pass;
        this.pass2 = pass2;
    }
    
    public List<String> validarDatos()
    {
        List<String> errores = new ArrayList<String>();
        if(this.nombreYApellido == null || this.nombreYApellido.replaceAll("/s","").length() == 0)
        {
            errores.add(MSJ_NOM_APELLINVALIDO);
        }
        if(this.nombreYApellido != null && this.nombreYApellido.replaceAll("/s","").length() <= 5)
        {
            errores.add(MSJ_NOM_APELL_CORTO);
        }
        if(dni == null || dni.length() != 8)
        {
            errores.add(MSJ_DNI_INVALIDO);
        }
        if(this.mail == null){
            errores.add(MSJ_MAIL_INVALIDO);
        }else{
            if(!MailValidator.validateEmail(this.mail))
            {
                errores.add(MSJ_MAIL_INVALIDO);
            }
        }

        if(this.pass == null)
        {
            errores.add(MSJ_PASS_NULL);
        }
        else
        {
            if(!this.pass.equals(pass2))
            {
                errores.add(MSJ_PASS_NOCOINCIDE);
            }else if(this.pass.length()<4)
            {
                errores.add(MSJ_PASS_CORTA);
            }
        }

    return errores;
    }

    public List<String> registrarDatos()
    {
        List<String> errores = this.validarDatos();

        if(errores.size() > 0)
        {
            return errores;
        }

        //La key del map deben ser los nombres de los campos en la tabla
        Map<String, String> datos = new HashMap<String, String>();
        datos.put("nombre",nombreYApellido);
        datos.put("mail",mail);
        datos.put("dni",dni);
        datos.put("contrasena",pass);

        String result = Utils.enviarPost(datos, PHP_NAME_REGISTRADOR);

        //TODO: qué hago con el result?
        Log.d("result post", result);

        return errores;
    }


}
