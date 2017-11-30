package Entidades;
import java.io.*;
import java.util.ArrayList;

import Entidades.Contrato;

public abstract class Usuario extends Entidad implements Serializable {
    private String name;
    private String email;
    private String password;

    private static String database = "./datos/usuarios/";
    
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String toString(){
        return "Name: " + getName()+ "\t Email: " + getEmail();
    }

    public static Usuario authUser(String email, String password){
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        File file = null;
        Usuario user = null;
        
        file = new File(database + email + ".obj");
        
        if(!file.exists()){
            System.out.println("Datos Incorrectos.");
            return null;
        }

        try {
            fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);
            user = (Usuario) ois.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    public boolean create()
    {
        return super.create(database + getEmail());
    }

    public String getKey()
    {
        return getEmail();
    };

    public static Usuario read(String query)
    {
        return (Usuario) fetch(database + query);
    };
    public Usuario update(){ return this;};
    public boolean delete(){return false;};
}