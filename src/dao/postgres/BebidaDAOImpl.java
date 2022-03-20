package dao.postgres;

import Entidades.Bebida;
import dao.BebidaDAO;
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
public class BebidaDAOImpl implements BebidaDAO {

    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Bebida bebida) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(bebida_id) FROM bebida;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO bebida (bebida_id,tipo_bebida,marca_bebida,litro_bebida) VALUES (?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, bebida.getTipo_bebida());
            pstm.setString(3, bebida.getMarca_bebida());
            pstm.setFloat(4, bebida.getLitro_bebida());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    
    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM bebida WHERE bebida_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Bebida bebida) { 
        remover(bebida.getBebida_id());
        adicionar(bebida);
    }

    @Override
    public List<Bebida> listar() {
        abreConexao();
        List<Bebida> ret = new ArrayList<>();
        String sql = "SELECT * FROM bebida";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Bebida novo = new Bebida();
                novo.setBebida_id(rs.getInt("bebida_id"));
                novo.setTipo_bebida(rs.getString("tipo_bebida"));
                novo.setMarca_bebida(rs.getString("marca_bebida"));
                novo.setLitro_bebida(rs.getFloat("litro_bebida"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BebidaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
