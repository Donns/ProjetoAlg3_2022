package dao;
import Entidades.Cliente;
import java.util.List;

/**
 *
 * @author Victor
 */
public interface ClienteDAO {
    public void adicionar(Cliente cli);
    public void editar(Cliente cli);
    public boolean remover (String cpf);
    public List<Cliente> listar();
    
}
