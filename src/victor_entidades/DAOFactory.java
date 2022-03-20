package victor_entidades;

import dao.RestauranteDAO;
import dao.postgres.RestauranteDAOImp;
import dao.PessoaDAO;
import dao.ClienteDAO;
import dao.EstacionamentoDAO;
import dao.FuncionarioDAO;
import dao.VendaDAO;
import dao.postgres.ClienteDAOImpl;
import dao.postgres.EstacionamentoDAOImpl;
import dao.postgres.FuncionarioDAOImpl;
import dao.postgres.PessoaDAOImpl;
import dao.postgres.VendaDAOImpl;
import dao.ReservaDAO;
import dao.postgres.ReservaDAOImp;
import dao.EnderecoDAO;
import dao.FinanceiroDAO;
import dao.FornecedoraDAO;
import dao.postgres.EnderecoDAOImp;
import dao.postgres.FinanceiroDAOImp;
import dao.postgres.FornecedoraDAOimp;
import dao.BebidaDAO;
import dao.CardapioDAO;
import dao.ClassificacaoDAO;
import dao.ComandaDAO;
import dao.EntregaDAO;
import dao.EstoqueDAO;
import dao.FidelidadeDAO;
import dao.ProdutoDAO;
import dao.postgres.BebidaDAOImpl;
import dao.postgres.CardapioDAOImpl;
import dao.postgres.ClassificacaoDAOImpl;
import dao.postgres.ComandaDAOImpl;
import dao.postgres.EntregaDAOImpl;
import dao.postgres.EstoqueDAOImpl;
import dao.postgres.FidelidadeDAOImpl;
import dao.postgres.ProdutoDAOImpl;

/**
 *
 * @author Victor
 */
public class DAOFactory {
    
    public static BebidaDAO createBebidaDAO(){
        return new BebidaDAOImpl();
    }
    
    public static CardapioDAO createCardapioDAO(){
        return new CardapioDAOImpl();
    }
    
    public static ClassificacaoDAO createClassificacaoDAO(){
        return new ClassificacaoDAOImpl();
    }
    
    public static ComandaDAO createComandaDAO(){
        return new ComandaDAOImpl();
    }
    
    public static EntregaDAO createEntregaDAO(){
        return new EntregaDAOImpl();
    }
   
    public static EstoqueDAO createEstoqueDAO(){
        return new EstoqueDAOImpl();
    }
    
    public static FidelidadeDAO createFidelidadeDAO(){
        return new FidelidadeDAOImpl();
    }
    
    public static ProdutoDAO createProdutoDAO(){
        return new ProdutoDAOImpl(); 
    }
    
    public static FinanceiroDAO createFinanDAO(){
        return new FinanceiroDAOImp();
    }
    
    public static RestauranteDAO createRestDAO(){
        return new RestauranteDAOImp();
    }
    
    public static FornecedoraDAO createFornDAO(){
        return new FornecedoraDAOimp();
    }
    
    public static PessoaDAO createDAO(){
        return new PessoaDAOImpl();
    }
    
    public static ClienteDAO createCliDAO(){
        return new ClienteDAOImpl();
    }
    
    public static FuncionarioDAO createFunDAO(){
        return new FuncionarioDAOImpl();
    }
    
    public static VendaDAO createVendaDAO(){
       return new VendaDAOImpl();
    }
    
    public static EstacionamentoDAO createEstacionamentoDAO(){
       return new EstacionamentoDAOImpl();
   }
    
    public static ReservaDAO createReservaDAO(){
        return new ReservaDAOImp();
    }
    
    public static EnderecoDAO createEndDAO(){
        return new EnderecoDAOImp();
    }
    
}
