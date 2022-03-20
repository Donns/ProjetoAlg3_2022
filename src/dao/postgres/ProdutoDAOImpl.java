package dao.postgres;

import dao.ProdutoDAO;
import Entidades.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Produto produto) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(produto_id) FROM produto;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO produto (produto_id, nome_produto, valor_produto) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, produto.getNome_produto());
            pstm.setFloat(3, produto.getValor_produto());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM produto WHERE produto_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Produto produto) { 
        remover(produto.getProduto_id());
        adicionar(produto);
    }

    @Override
    public List<Produto> listar() {
        abreConexao();
        List<Produto> ret = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Produto novo = new Produto();
                novo.setProduto_id(rs.getInt("produto_id"));
                novo.setNome_produto(rs.getString("nome_produto"));
                novo.setValor_produto(rs.getFloat("valor_produto"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
