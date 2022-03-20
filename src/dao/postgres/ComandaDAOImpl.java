package dao.postgres;

import dao.ComandaDAO;
import Entidades.Comanda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class ComandaDAOImpl implements ComandaDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Comanda comanda) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(comanda_id) FROM comanda;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO comanda (comanda_id, nome_prato, nome_bebida) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, comanda.getPrato());
            pstm.setString(3, comanda.getBebida());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

   

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM comanda WHERE comanda_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Comanda comanda) { 
        remover(comanda.getComanda_id());
        adicionar(comanda);
    }

    @Override
    public List<Comanda> listar() {
        abreConexao();
        List<Comanda> ret = new ArrayList<>();
        String sql = "SELECT * FROM comanda";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Comanda novo = new Comanda();
                novo.setComanda_id(rs.getInt("comanda_id"));
                novo.setPrato(rs.getString("nome_prato"));
                novo.setBebida(rs.getString("nome_bebida"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ComandaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
