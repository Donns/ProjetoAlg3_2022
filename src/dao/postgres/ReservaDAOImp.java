package dao.postgres;
import Entidades.Reserva;
import dao.ReservaDAO;
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
public class ReservaDAOImp implements ReservaDAO{
    
    private Connection con;
    
    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void adicionar(Reserva reserva) {
        
        abreConexao();
        String sql = "INSERT INTO reserva (cpf_cli,preco_reserva,hora_entrada) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, reserva.getCpf_cli());
            pstm.setFloat(2, reserva.getPreco_reserva());
            pstm.setString(3, reserva.getHora_entrada());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

   
    @Override
    public boolean remover(String cpf_cli) {
        abreConexao();
        String sql = "DELETE FROM reserva WHERE cpf_cli = '" + cpf_cli+"'";
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Reserva reserva) { 
        remover(reserva.getCpf_cli());
        adicionar(reserva);
    }

    @Override
    public List<Reserva> listar() {
        abreConexao();
        List<Reserva> ret = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Reserva novo = new Reserva();
                novo.setCpf_cli(rs.getString("cpf_cli"));
                novo.setPreco_reserva(rs.getFloat("preco_reserva"));
                novo.setHora_entrada(rs.getString("hora_entrada"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }
    
}
