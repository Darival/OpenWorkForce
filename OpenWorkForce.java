import Controladores.*;
import Entidades.*;
import java.util.Scanner;

class OpenWorkForce {
    public static void main(String[] args){
        Usuario user = bienvenida();
    }

    private static Usuario bienvenida(){
        Usuario user = null;
        System.out.println("Bienvenido a OpenWorkforce");
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Login[L] \t Register[R]");
            String option = sc.next().toUpperCase();
            sc.close();
            /* if(option == "L"){
                user = login();
            } */
            if(option == "R"){
                user = registroDeUsuario();
            }
        } while (user == null);
        return user;
    }

    private static Usuario authUser(String email, String password){
        return new Cliente("test","test", "test");
    }

    private static Usuario registroDeUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre:");
        String name = sc.next();
        System.out.println("Correo:");
        String email = sc.next().toLowerCase();
        System.out.println("Contraseña:");
        String password = sc.next();
        sc.close();
        return new Cliente(name, email, password);
    }

    private static void cerrarSession(){

    }

    /* private static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Correo:");
        String email = sc.next().toLowerCase();
        System.out.println("Contraseña:");
        String password = sc.next();
        sc = close();
        Usuario user = authUser(email, password);
    } */
}
