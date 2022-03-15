package victor_entidades;

import dao.EnderecoDAO;
import dao.postgres.EnderecoDAOImp;

/**
 *
 * @author Victor
 */
public class DAOFactory3 {
    public static EnderecoDAO createDAO(){
        return new EnderecoDAOImp();
    }
    
}