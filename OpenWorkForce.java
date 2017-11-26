import Controladores.*;
import Entidades.*;
import java.io.*;


//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;

class OpenWorkForce {
    public static void main(String[] args){
        Usuario user = bienvenida();
        
        System.out.println(user.toString());
    }

    private static Usuario bienvenida(){
        Usuario user = null;
        System.out.println("Bienvenido a OpenWorkforce");
        do {
            Console cons = System.console();
            System.out.println("Login[L] \t Register[R]");
            String option = cons.readLine().toUpperCase();
            if(option.equals("L")){
                user = login();
            }
            if(option.equals("R")){
                user = registroDeUsuario();
            }
        } while (user == null);
        return user;
    }

    private static Usuario registroDeUsuario(){
        Console cons = System.console();
        System.out.println("Nombre:");
        String name = cons.readLine();
        System.out.println("Correo:");
        String email = cons.readLine().toLowerCase();
        String password = new String(cons.readPassword("Contraseña:"));
        Cliente cliente = new Cliente(name, email, password);
        if(!cliente.create(cliente)){
            return null;
        };
        return cliente;
    }

    private static void cerrarSession(){

    }

    private static Usuario login(){
        Console cons = System.console();
        System.out.println("Correo:");
        String email = cons.readLine().toLowerCase();
        String password = new String(cons.readPassword("Contraseña:"));
        return Usuario.authUser(email, password);
    }
}
