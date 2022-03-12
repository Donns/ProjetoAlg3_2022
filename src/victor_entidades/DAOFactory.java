package victor_entidades;

import dao.RestauranteDAO;
import dao.postgres.RestauranteDAOImp;

/**
 *
 * @author Victor
 */
public class DAOFactory {
    public static RestauranteDAO createDAO(){
        return new RestauranteDAOImp();
    }
    
}
