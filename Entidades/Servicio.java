package Entidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Servicio extends Entidad implements Serializable{
    
    public String nombre;
    private static String database = "./datos/servicios/";

    public Servicio(String nombre){
        this.nombre = nombre;
    }

    public String getKey(){
        return nombre;
    }

    public String toString(){
        return nombre;
    }

    public boolean create(){
        if(exists(database + nombre)){
            return false;
        }
        return super.create(database + nombre);
    }

    public static ArrayList<Servicio> all(){
        List<Servicio> listado = list(database).stream().map(object -> (Servicio) object).collect(Collectors.toList());
        return new ArrayList<Servicio>(listado);
    }

    public static Servicio read(String query)
    {
        return (Servicio) fetch(database + query);
    };

    public Servicio update(){ return this;};
    public boolean delete(){return false;};
}