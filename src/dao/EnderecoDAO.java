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
    
    public boolean remover(String cidade, String rua,String bairro,String numero,String cep);
    
    public List<Endereco> listar();
    
    public void editar(Endereco endereco);
    
}
