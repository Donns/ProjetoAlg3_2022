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
import dao.FuncionarioDAO;
import Entidades.Pessoa;
import Entidades.Funcionario;

public class FuncionarioDAOImpl implements FuncionarioDAO{
    
    private Connection con;
    
    private void abreConexao(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123" );
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    private void fechaConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    @Override
    public void adicionar(Funcionario fun) {
        abreConexao();
        String sqlP = "INSERT INTO Pessoa (cpf, nome, telefone, data_nasc) VALUES (?, ?, ?, ?);";
        String sqlC = "INSERT INTO Funcionario (salario, cpf, nome) VALUES (?, ?, ?);";
        try {
            PreparedStatement pstmP = con.prepareStatement(sqlP);
            pstmP.setString(1, fun.getFunc().getCpf());
            pstmP.setString(2, fun.getFunc().getNome());
            pstmP.setString(3, fun.getFunc().getTelefone());
            pstmP.setString(4, fun.getFunc().getData_nasc());
            pstmP.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try{
            PreparedStatement pstmC = con.prepareStatement(sqlC);
            
            pstmC.setFloat(1, fun.getSalario());
            pstmC.setString(2, fun.getFunc().getCpf());
            pstmC.setString(3, fun.getFunc().getNome());
            pstmC.execute();
        }
        catch (SQLException ex) {        
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
    }

    @Override
    public void editar(Funcionario fun) { 
        remover(fun.getFunc().getCpf());
        adicionar(fun);
     
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
        String sql = "DELETE FROM funcionario WHERE cpf = '" + cpf + "'";
        String sqlP = "DELETE FROM pessoa WHERE cpf = '" + cpf + "'";
        //String sqlP = "DELETE FROM pessoa WHERE cpf = " + cpf;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            int retP = con.createStatement().executeUpdate(sqlP);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return false;
    }

    @Override
    public List<Funcionario> listar() {
        abreConexao();
        List<Funcionario> ret = new ArrayList<>();
        String sql = "SELECT pessoa.cpf, pessoa.nome, pessoa.telefone, pessoa.data_nasc, funcionario.salario FROM pessoa, funcionario WHERE pessoa.cpf = funcionario.cpf;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Funcionario nova = new Funcionario();
                Pessoa novaP = new Pessoa();
                
                novaP.setCpf((rs.getString("cpf")));
                novaP.setNome((rs.getString("nome")));
                novaP.setTelefone((rs.getString("telefone")));
                novaP.setData_nasc((rs.getString("data_nasc")));
                nova.setFunc(novaP);
                nova.setSalario(rs.getInt("salario"));
                ret.add(nova);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return ret;
    }
   
}
