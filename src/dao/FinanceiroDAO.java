package dao;
import Entidades.Financeiro;
import java.util.List;


/**
 *
 * @author Victor
 */
public interface FinanceiroDAO {
    public void adicionar(Financeiro financeiro);
    
    public void atualizar(Financeiro financeiro);
    
    public boolean remover(int id);
    
    public List<Financeiro> listar();
    
    public void editar(Financeiro financeiro);
    
}
