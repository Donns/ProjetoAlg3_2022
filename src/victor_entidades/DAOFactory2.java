package victor_entidades;

import dao.ReservaDAO;
import dao.postgres.ReservaDAOImp;

/**
 *
 * @author Victor
 */
public class DAOFactory2 {
    public static ReservaDAO createDAO(){
        return new ReservaDAOImp();
    }
    
}
