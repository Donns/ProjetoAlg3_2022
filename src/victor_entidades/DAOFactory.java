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
import dao.FornecedoraDAO;
import dao.postgres.EnderecoDAOImp;
import dao.postgres.FornecedoraDAOimp;

/**
 *
 * @author Victor
 */
public class DAOFactory {
    
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
