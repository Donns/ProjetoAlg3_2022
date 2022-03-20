package dao.postgres;

import dao.CardapioDAO;
import Entidades.Cardapio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class CardapioDAOImpl implements CardapioDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Cardapio cardapio) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(prato_id) FROM cardapio;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO cardapio (prato_id,nome_prato,descricao,classificacao_id,valor) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, cardapio.getNome_prato());
            pstm.setString(3, cardapio.getDescricao());
            pstm.setInt(4, cardapio.getClassificacao());
            pstm.setFloat(5, cardapio.getValor());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

   
    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM cardapio WHERE prato_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Cardapio cardapio) { 
        remover(cardapio.getPrato_id());
        adicionar(cardapio);
    }

    @Override
    public List<Cardapio> listar() {
        abreConexao();
        List<Cardapio> ret = new ArrayList<>();
        String sql = "SELECT * FROM cardapio";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Cardapio novo = new Cardapio();
                novo.setPrato_id(rs.getInt("prato_id"));
                novo.setNome_prato(rs.getString("nome_prato"));
                novo.setDescricao(rs.getString("descricao"));
                novo.setClassificacao(rs.getInt("classificacao_id"));
                novo.setValor(rs.getFloat("valor"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CardapioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
