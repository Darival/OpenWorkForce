import Entidades.*;
import java.io.File;
import java.io.Console;

class OpenWorkForce {
    public static void main(String[] args){
        Cliente user = bienvenida();

        System.out.println("Bienvenido " + user.getName());

        Servicio servicio = new Servicio("Costureria");
        servicio.create();


        System.out.println("Contratos Creados: ");

        System.out.println(Contrato.all().size());

        Contrato contrato = new Contrato(user, servicio.getKey(), "y asi", 200.0);

        contrato.create();

        System.out.println(contrato.getServicio().nombre);

        System.out.println(contrato.cliente().getName());
    }

    private static Cliente bienvenida(){
        Cliente user = null;
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

    private static Cliente registroDeUsuario(){
        Console cons = System.console();

        System.out.println("Nombre:");
        String name = cons.readLine();
        String email;
        do{
            System.out.println("Correo:");
            email = cons.readLine().toLowerCase();
        }while(new File("./datos/usuarios/" + email + ".ser").exists());

        String password = new String(cons.readPassword("Contraseña:"));
        Cliente cliente = new Cliente(name, email, password);

        cliente.create();

        return cliente;
    }

    private static Cliente login(){
        Console cons = System.console();
        System.out.println("Correo:");
        String email = cons.readLine().toLowerCase();
        String password = new String(cons.readPassword("Contraseña:"));
        return (Cliente) Cliente.authUser(email, password);
    }
}
