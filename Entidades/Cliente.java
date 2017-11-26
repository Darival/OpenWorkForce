package Entidades;
import java.io.Serializable;

public class Cliente extends Usuario{

    public Cliente(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }
}