package dao.postgres;

import dao.EntregaDAO;
import Entidades.Entrega;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class EntregaDAOImpl implements EntregaDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Entrega entrega) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(entrega_id) FROM entrega;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO entrega (entrega_id, nome_prato, endereco, cpf_cli) VALUES (?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, entrega.getNome_prato());
            pstm.setString(3, entrega.getEndereco());
            pstm.setString(4, entrega.getCpf_cli());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM entrega WHERE entrega_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Entrega entrega) { 
        remover(entrega.getEntrega_id());
        adicionar(entrega);
    }

    @Override
    public List<Entrega> listar() {
        abreConexao();
        List<Entrega> ret = new ArrayList<>();
        String sql = "SELECT * FROM entrega";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Entrega novo = new Entrega();
                novo.setEntrega_id(rs.getInt("entrega_id"));
                novo.setNome_prato(rs.getString("nome_prato"));
                novo.setEndereco(rs.getString("endereco"));
                novo.setCpf_cli(rs.getString("cpf_cli"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EntregaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
