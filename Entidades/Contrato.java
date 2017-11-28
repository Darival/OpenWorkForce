package Entidades;


import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Contrato {
    
    private int id;
    private int servicio;
    private String cliente;
    private String empleado;
    public String descripcion;
    public String estado;
    public int recibo;
    private double compensacion;

    final static String database = "./datos/contratos.csv";


    public Contrato(String cliente,int servicio, String descripcion, double compensacion){
        setCliente(cliente);
        setServicio(servicio);
        setDescripcion(descripcion);
        setCompensacion(compensacion);
    }

    public Contrato(Cliente cliente,int servicio, String descripcion, double compensacion){
        setCliente(cliente);
        setServicio(servicio);
        setDescripcion(descripcion);
        setCompensacion(compensacion);
        
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente.getEmail();
    }

    public void setCliente(String email){
        this.cliente = email;
    }

    public void setServicio(int servicio){
        this.servicio = servicio;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setCompensacion(double compensacion){
        this.compensacion = compensacion;
    }


    public static ArrayList<Contrato> list(Usuario user){
        File file = new File("./datos/contratos.csv");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

        ArrayList<Contrato> contratos = new ArrayList<Contrato>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String renglon;
            while((renglon = br.readLine())!= null){
                String [] datos = renglon.split(",");
                Contrato contrato = new Contrato(
                    datos[0],
                    Integer.parseInt(datos[1]),
                    datos[2],
                    Double.parseDouble(datos[3])
                );
                contratos.add(contrato);
            }
        } catch (Exception e) {
            System.out.println("No se pudo obtener el listado de contratos.");
        }
        return contratos;        
    }

    public void create(){
        try{
            File file = new File(database);
            FileReader fr = new FileReader(file);;
            BufferedReader br = new BufferedReader(fr);;
            FileWriter fw =  new FileWriter(file,true);;
            BufferedWriter bw = new BufferedWriter(fw); ;
            if(file.exists()){
                bw.newLine();
                int id = count(database);
                bw.write(
                    id + "," +
                    servicio + "," +
                    cliente +  "," +
                    empleado + "," +
                    descripcion +
                    ",abierto," +
                    ",," +
                    compensacion
                );
            }else{
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw); 
                bw.write("");
                int id = count(database);
                bw.write(
                    id + "," +
                    servicio + "," +
                    cliente +  "," +
                    empleado + "," +
                    descripcion +
                    ",abierto," +
                    ",," +
                    compensacion
                );
            }
            bw.close();
            fw.close();
            br.close();
            fr.close();
        }catch(Exception e){
            System.out.println(e);
        }
        /* private int id;
        private int servicio;
        private String cliente;
        private String empleado;
        public String descripcion;
        public String estado;
        public int recibo;
        private double compensacion; */
    }

    public int count(String filename) throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if(endsWithoutNewLine) {
                ++count;
            } 
            return count;
        } finally {
            is.close();
        }
    }
}