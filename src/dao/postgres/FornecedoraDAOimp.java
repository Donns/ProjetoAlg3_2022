package dao.postgres;

import Entidades.Fornecedora;
import dao.FornecedoraDAO;
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
public class FornecedoraDAOimp implements FornecedoraDAO{

     private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Fornecedora fornecedora) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(id) FROM fornecedora;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO fornecedora (id, nome, telefone,cnpj,produtos,valor_total_produtos) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, fornecedora.getNome());
            pstm.setString(3, fornecedora.getTel());
            pstm.setString(4, fornecedora.getCnpj());
            pstm.setString(5, fornecedora.getProdutos());
            pstm.setFloat(6, fornecedora.getValor_total_produtos());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public void atualizar(Fornecedora fornecedora) {
        abreConexao();
        String sql = "UPDATE fornecedora SET nome = : WHERE id = ?;";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, fornecedora.getNome());
            pstm.setString(2, fornecedora.getTel());
            pstm.setString(3, fornecedora.getCnpj());
            pstm.setString(4, fornecedora.getProdutos());
            pstm.setFloat(5, fornecedora.getValor_total_produtos());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM fornecedora WHERE id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Fornecedora fornecedora) { 
        remover(fornecedora.getId());
        adicionar(fornecedora);
    }

    @Override
    public List<Fornecedora> listar() {
        abreConexao();
        List<Fornecedora> ret = new ArrayList<>();
        String sql = "SELECT * FROM fornecedora";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Fornecedora novo = new Fornecedora();
                novo.setId(rs.getInt("id"));
                novo.setNome(rs.getString("nome"));
                novo.setTel(rs.getString("telefone"));
                novo.setCnpj(rs.getString("cnpj"));
                novo.setProdutos(rs.getString("produtos"));
                novo.setValor_total_produtos(rs.getFloat("valor_total_produtos"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FornecedoraDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
