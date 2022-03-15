package dao;
import Entidades.Endereco;
import java.util.List;

/**
 *
 * @author Victor
 */
public interface EnderecoDAO {
    public void adicionar(Endereco endereco);
    
    public void atualizar(Endereco endereco);
    
    public boolean remover(String cpf);
    
    public List<Endereco> listar();
    
    public void editar(Endereco endereco);
    
}
