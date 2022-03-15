package dao.postgres;

import Entidades.Restaurante;
import dao.RestauranteDAO;
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
public class RestauranteDAOImp implements RestauranteDAO {

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

    @Override
    public void adicionar(Restaurante restaurante) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(id) FROM restaurante;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO restaurante (id, nome, telefone) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, restaurante.getNome());
            pstm.setString(3, restaurante.getTel_rest());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public void atualizar(Restaurante restaurante) {
        abreConexao();
        String sql = "UPDATE restaurante SET nome = : WHERE id = ?;";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, restaurante.getNome());
            pstm.setString(2, restaurante.getTel_rest());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM restaurante WHERE id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Restaurante restaurante) { 
        remover(restaurante.getId_rest());
        adicionar(restaurante);
    }

    @Override
    public List<Restaurante> listar() {
        abreConexao();
        List<Restaurante> ret = new ArrayList<>();
        String sql = "SELECT * FROM restaurante";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Restaurante novo = new Restaurante();
                novo.setId_rest(rs.getInt("id"));
                novo.setNome(rs.getString("nome"));
                novo.setTel_rest(rs.getString("telefone"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RestauranteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
