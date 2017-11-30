package Entidades;

public class Empleado extends Usuario{
    private String servicio;
    private float ingresos;

    public void cobrar(float ingresos){
        this.ingresos += ingresos;
    }

    public float ingresoAcumuladoMensual(){
        return ingresos;
    }
}