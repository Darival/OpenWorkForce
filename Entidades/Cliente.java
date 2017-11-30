package Entidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente extends Usuario
{
    public Cliente(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }
}