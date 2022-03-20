package dao.postgres;
import Entidades.Endereco;
import dao.EnderecoDAO;
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
public class EnderecoDAOImp implements EnderecoDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Endereco endereco) {
        
        abreConexao();
        String sql2 = "INSERT INTO endereco (cpf, nome, cep,cidade,bairro,rua,numero) VALUES (?,?,?,?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setString(1, endereco.getCpf());
            pstm.setString(2, endereco.getNome());
            pstm.setString(3, endereco.getCep());
            pstm.setString(4, endereco.getCidade());
            pstm.setString(5, endereco.getBairro());
            pstm.setString(6, endereco.getRua());
            pstm.setString(7, endereco.getNumero());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }


    @Override
    public boolean remover(String cpf) {
        abreConexao();
        String sql = "DELETE FROM endereco WHERE cpf = '" + cpf+"'";
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Endereco endereco) { 
        remover(endereco.getCpf());
        adicionar(endereco);
    }

    @Override
    public List<Endereco> listar() {
        abreConexao();
        List<Endereco> ret = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Endereco novo = new Endereco();
                novo.setCpf(rs.getString("cpf"));
                novo.setNome(rs.getString("nome"));
                novo.setCep(rs.getString("cep"));
                novo.setCidade(rs.getString("cidade"));
                novo.setBairro(rs.getString("bairro"));
                novo.setRua(rs.getString("rua"));
                novo.setNumero(rs.getString("numero"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

    
}
