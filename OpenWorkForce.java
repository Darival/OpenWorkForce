import Entidades.*;
import java.io.File;
import java.util.ArrayList;
import java.io.Console;

class OpenWorkForce {
    public static void main(String[] args){
        Cliente user = bienvenida();

        init();

        ArrayList<Servicio> servicios = Servicio.all();

        System.out.println("Bienvenido " + user.getName());

        while(user != null){
            System.out.println("Ver mis Contratos[1]");
            System.out.println("Crear contrato[2]");
            System.out.println("Cerrar Session[3]");
    
    
            Console cons = System.console();
            String option = cons.readLine().toUpperCase();
    
    
            switch (option) {
                case "1":
                    listContratos(user);
                    break;
                case "2":
                    //createContrato(user);
                    System.out.println("crear contrato");
                    Contrato contrato = new Contrato(user, "Albanileria", "y asi", 200.0);
                    contrato.create();
                    break;
                case "3":
                    user = null;
                    System.out.println("Adios.");
                    break;
            }
        }
    }


    public static void showMenu(Cliente user){
    }

    private static void listContratos(Cliente cliente){
        for(Contrato contrato: cliente.contratos()){
            System.out.println("__________________________________________");
            System.out.println(contrato);
            System.out.println("__________________________________________");
        }
    }

    private static void init(){
        String[] serviceNames = new String[]
        {
            "Albanileria",
            "Costureria",
            "Plomeria"
        };

        for(String name: serviceNames){
            (new Servicio(name)).create();
        }
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
        }while(new File("./datos/usuarios/" + email + ".obj").exists());

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
