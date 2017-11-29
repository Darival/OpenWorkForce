package Entidades;

import java.io.*;

public class Servicio extends Entidad implements Serializable{
    
    public String nombre;
    private static String database = "./datos/servicios/";

    public Servicio(String nombre){
        this.nombre = nombre;
    }

    public String getKey(){
        return nombre;
    }

    public boolean create(){
        if(exists(database + nombre)){
            return false;
        }
        return super.create(database + nombre);
    }

    public static Servicio read(String query)
    {
        return (Servicio) fetch(database + query);
    };

    public Servicio update(){ return this;};
    public boolean delete(){return false;};
}