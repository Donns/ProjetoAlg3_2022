package dao;
import Entidades.Fornecedora;
import java.util.List;

/**
 *
 * @author Victor
 */
public interface FornecedoraDAO {
    public void adicionar(Fornecedora fornecedora);
    
    public void atualizar(Fornecedora fornecedora);
    
    public boolean remover(int id);
    
    public List<Fornecedora> listar();
    
    public void editar(Fornecedora fornecedora);
    
}
