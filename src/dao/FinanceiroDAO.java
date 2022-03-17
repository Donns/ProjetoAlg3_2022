package dao;
import Entidades.Financeiro;
import java.util.List;


/**
 *
 * @author Victor
 */
public interface FinanceiroDAO {
    public void iniciar();
    
    public float despesa_fun();
    
    public float despesa_forn();
    
    public float caixa();
    
    public float atualizaCaixaFun();
    
    public float atualizaCaixaFor();
    
}
