package dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.PessoaDAO;
import Entidades.Pessoa;

public class PessoaDAOImpl implements PessoaDAO{
    
    private Connection con;
    
    private void abreConexao(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123" );
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    private void fechaConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    @Override
    public void adicionar(Pessoa pessoa) {
        abreConexao();
        String sql = "INSERT INTO Pessoa (cpf, nome, telefone, data_nasc) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, pessoa.getCpf());
            pstm.setString(2, pessoa.getNome());
            pstm.setString(3, pessoa.getTelefone());
            pstm.setString(4, pessoa.getData_nasc());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
    }

    @Override
    public void editar(Pessoa pessoa) {
        abreConexao();
        String sql = "UPDATE Pessoa SET nome = ?, telefone = ? WHERE cpf = ?;"; // não sei se está certo
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getTelefone());
            pstm.setString(3, pessoa.getCpf());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
    }

    @Override
    public boolean remover(String cpf) {
        String sql = "DELETE FROM Pessoa WHERE cpf = " + cpf;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return false;
    }

    @Override
    public List<Pessoa> listar() {
        abreConexao();
        List<Pessoa> ret = new ArrayList<>();
        String sql = "SELECT * FROM pessoa;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Pessoa nova = new Pessoa();
                nova.setCpf(rs.getString("cpf"));
                nova.setNome(rs.getString("nome"));
                nova.setTelefone(rs.getString("telefone"));
                nova.setData_nasc(rs.getString("data_nasc"));
                
                ret.add(nova);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return ret;
    }
   
}
