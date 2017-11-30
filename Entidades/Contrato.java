package Entidades;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Entidades.Servicio;

public class Contrato extends Entidad implements Serializable{
    
    private String servicio;
    private String cliente;
    private String empleado;
    public String descripcion;
    private String estado;
    public String recibo;
    private double compensacion;
    private static String database = "./datos/contratos/";

    public Contrato(Cliente cliente,String servicio, String descripcion, double compensacion){
        setCliente(cliente);
        setServicio(servicio);
        setDescripcion(descripcion);
        setCompensacion(compensacion);
        setEmpleado(null);
        setEstado("abierto");
        recibo = null;
    }

    public void setEmpleado(String empleado){
        this.empleado = empleado;
    }

    public String getEmpleado(){
        return empleado;
    }
    
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente.getEmail();
    }
        
    public void setEstado(String estado)
    {
        this.estado = estado;
    }
        
    public String getEstado()
    {
        return estado;
    }

    public void setCliente(String email)
    {
        this.cliente = email;
    }

    public void setServicio(String servicio)
    {
        this.servicio = servicio;
    }

    public Servicio getServicio()
    {
        return Servicio.read(servicio);
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setCompensacion(double compensacion)
    {
        this.compensacion = compensacion;
    }

    public double getCompensacion()
    {
        return  compensacion;
    }

    public boolean create()
    {
        return super.create(database + getKey());
    }
    
    public String getKey()
    {
        int id = new File(database).listFiles().length;
        return "contrato" + id;
    }

    public String toString()
    {
        return getServicio().nombre + "\n" +
        "Descripcion:\n" + getDescripcion() + "\n" +
        "Estado:\t" + getEstado() + "\t" +
        "Paga:\t$" + getCompensacion();
    }

    public static ArrayList<Contrato> all(){
        List<Contrato> listado = list(database).stream().map(object -> (Contrato) object).collect(Collectors.toList());
        return new ArrayList<Contrato>(listado);
    }

    public Cliente cliente(){
        return (Cliente) Cliente.read(cliente);
    }

    public static Contrato read(String query)
    {
        return (Contrato) fetch(database + query);
    };

    public Contrato update(){return this;};
    public boolean delete(){return false;};
}