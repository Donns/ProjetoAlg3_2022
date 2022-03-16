package dao.postgres;

import Entidades.Cliente;
import Entidades.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ClienteDAO;

/**
 *
 * @author Victor
 */
public class ClienteDAOImpl implements ClienteDAO{
    private Connection con;
    
    private void abreConexao(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123" );
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    private void fechaConexao(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    //private PessoaDAO dao = DAOFactory.createDAO();
    
    @Override
    public void adicionar(Cliente cli) {
        abreConexao();
        String sqlP = "INSERT INTO Pessoa (cpf, nome, telefone, data_nasc) VALUES (?, ?, ?, ?);";
        String sqlC = "INSERT INTO Cliente (qtd_vendas, cpf, nome) VALUES (?, ?, ?);";
        try {
            PreparedStatement pstmP = con.prepareStatement(sqlP);
            pstmP.setString(1, cli.getCli().getCpf());
            pstmP.setString(2, cli.getCli().getNome());
            pstmP.setString(3, cli.getCli().getTelefone());
            pstmP.setString(4, cli.getCli().getData_nasc());
            pstmP.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        try{
            PreparedStatement pstmC = con.prepareStatement(sqlC);
            
            pstmC.setInt(1, cli.getVendas_fidelidade());
            pstmC.setString(2, cli.getCli().getCpf());
            pstmC.setString(3, cli.getCli().getNome());
            pstmC.execute();
        }
        catch (SQLException ex) {        
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
    }

    @Override
    public void editar(Cliente cli) { 
        remover(cli.getCli().getCpf());
        adicionar(cli);
     
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
        String sql = "DELETE FROM cliente WHERE cpf = '" + cpf + "'";
        String sqlP = "DELETE FROM pessoa WHERE cpf = '" + cpf + "'";
        //String sqlP = "DELETE FROM pessoa WHERE cpf = " + cpf;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            int retP = con.createStatement().executeUpdate(sqlP);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return false;
    }

    @Override
    public List<Cliente> listar() {
        abreConexao();
        List<Cliente> ret = new ArrayList<>();
        String sql = "SELECT pessoa.cpf, pessoa.nome, pessoa.telefone, pessoa.data_nasc, cliente.qtd_vendas FROM pessoa, cliente WHERE pessoa.cpf = cliente.cpf;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Cliente nova = new Cliente();
                Pessoa novaP = new Pessoa();
                
                novaP.setCpf((rs.getString("cpf")));
                novaP.setNome((rs.getString("nome")));
                novaP.setTelefone((rs.getString("telefone")));
                novaP.setData_nasc((rs.getString("data_nasc")));
                nova.setCli(novaP);
                nova.setVendas_fidelidade(rs.getInt("qtd_vendas"));
                ret.add(nova);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return ret;
    }
    
}
