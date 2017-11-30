package Entidades;
import java.io.*;
import java.util.ArrayList;

abstract class Entidad implements Serializable {

    public boolean create(String filename){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        File file = new File(filename + ".obj");

        try {
            fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static boolean exists(String query)
    {
        File file = new File(query + ".obj");
        return file.exists();
    }

    public static ArrayList<Entidad> list(String database)
    {
        ArrayList<Entidad> entidades = new ArrayList<Entidad>();

        File[] files = new File(database)
            .listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".obj")
            );

        for(File file: files){
            entidades.add((Entidad) fetch(file.getPath().replaceFirst("\\.obj", "")));
        }

        return entidades;
    }

    public static Entidad fetch(String query)
    {
        Entidad entidad = null;
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        File file = new File(query + ".obj");
        if(file.exists()){
            try {
                fin = new FileInputStream(file);
                ois = new ObjectInputStream(fin);
                entidad = (Entidad) ois.readObject();
    
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
        }
        return entidad;
    };

    abstract public String getKey();
    abstract public Entidad update();
    abstract public boolean delete();
}