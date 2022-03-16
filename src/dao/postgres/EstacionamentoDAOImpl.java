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
import Entidades.Estacionamento;
import dao.EstacionamentoDAO;

public class EstacionamentoDAOImpl implements EstacionamentoDAO{
    
    private Connection con;
    
    private void abreConexao(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123" );
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    private void fechaConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
   
    
    @Override
    public void adicionar(Estacionamento estacionamento) {
        abreConexao();
        String sql = "INSERT INTO Estacionamento (cpf, nome, cod_vaga, preco, hora_entrada, hora_saida) VALUES (?, ?, ?, ?, ?, ?);";
        //String sqlC = "INSERT INTO Cliente (qtd_vendas, cpf, nome) VALUES (?, ?, ?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, estacionamento.getCpf());
            pstm.setString(2, estacionamento.getNome());
            pstm.setInt(3, estacionamento.getCod_vaga());
            pstm.setFloat(4, estacionamento.getPreco());
            pstm.setString(5, estacionamento.getHora_entrada());
            pstm.setString(6, estacionamento.getHora_saida());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
    }

    @Override
    public void editar(Estacionamento estacionamento) { 
        remover(estacionamento.getCpf());
        adicionar(estacionamento);
     
        /*abreConexao();
        String sqlC = "UPDATE Cliente SET nome = ?, telefone = ? WHERE cpf = ?;";
        String sqlP = "UPDATE Pessoa SET nome = ?, telefone = ? WHERE cpf = ?;"; // não sei se está certo
        try {
            PreparedStatement pstmC = con.prepareStatement(sqlC);
            pstmC.setString(1, cli.getCli().getNome());
            pstmC.setString(2, cli.getCli().getTelefone());
            pstmC.setString(3, cli.getCli().getCpf());
            pstmC.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            PreparedStatement pstmP = con.prepareStatement(sqlP);
            pstmP.setString(1, cli.getCli().getNome());
            pstmP.setString(2, cli.getCli().getTelefone());
            pstmP.setString(3, cli.getCli().getCpf());
            pstmP.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }*/
    }

    @Override
    public boolean remover(String cpf) {
        abreConexao();
        String sql = "DELETE FROM estacionamento WHERE cod_vaga = " + cpf;
        //String sqlP = "DELETE FROM pessoa WHERE cpf = '" + cod_vaga + "'";
        //String sqlP = "DELETE FROM pessoa WHERE cpf = " + cpf;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return false;
    }

    @Override
    public List<Estacionamento> listar() {
        abreConexao();
        List<Estacionamento> ret = new ArrayList<>();
        String sql = "SELECT * FROM Estacionamento;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Estacionamento novo = new Estacionamento();
                
                novo.setCpf((rs.getString("cpf")));
                novo.setNome((rs.getString("nome")));
                novo.setCod_vaga((rs.getInt("cod_vaga")));
                novo.setPreco((rs.getFloat("preco")));
                novo.setHora_entrada((rs.getString("hora_entrada")));
                novo.setHora_saida(rs.getString("hora_saida"));
                ret.add(novo);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return ret;
    }
   
}
