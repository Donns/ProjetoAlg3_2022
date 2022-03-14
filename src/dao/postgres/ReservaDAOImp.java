package dao.postgres;
import Entidades.Reserva;
import dao.ReservaDAO;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Victor
 */
public class ReservaDAOImp {
    
    private Connection con;
    
    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
