package dao.postgres;

import dao.ClassificacaoDAO;
import Entidades.Classificacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class ClassificacaoDAOImpl implements ClassificacaoDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Classificacao classificacao) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(classificacao_id) FROM classificacao;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO classificacao (classificacao_id, nome_classificacao) VALUES (?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setString(2, classificacao.getNome_classificacao());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

   
    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM classificacao WHERE classificacao_id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Classificacao classificacao) { 
        remover(classificacao.getClassificacao_id());
        adicionar(classificacao);
    }

    @Override
    public List<Classificacao> listar() {
        abreConexao();
        List<Classificacao> ret = new ArrayList<>();
        String sql = "SELECT * FROM classificacao";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Classificacao novo = new Classificacao();
                novo.setClassificacao_id(rs.getInt("classificacao_id"));
                novo.setNome_classificacao(rs.getString("nome_classificacao"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassificacaoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
