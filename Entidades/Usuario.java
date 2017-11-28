package Entidades;
import java.io.*;

public abstract class Usuario implements Serializable {
    private String name;
    private String email;
    private String password;
    
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
        
        file = new File("./datos/usuarios/" + email + ".ser");
        
        if(!file.exists()){
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

    public static boolean create(Usuario user){
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;

        File file = null;
        
        try {
            file = new File("./datos/usuarios/" + user.getEmail() + ".ser");

            if (!file.createNewFile()){
                System.out.println("Este correo electronico ya esta registrado.");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(user);

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

    public Usuario read(){ return this;};
    public Usuario update(){ return this;};
    public boolean delete(){return false;};
}