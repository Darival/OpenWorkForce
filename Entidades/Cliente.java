package Entidades;
import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Usuario
{
    public Cliente(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }
}