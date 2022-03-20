package dao.postgres;

import dao.FidelidadeDAO;
import Entidades.Fidelidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class FidelidadeDAOImpl implements FidelidadeDAO{
    
    private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(FidelidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FidelidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Fidelidade fidelidade) {
        
        abreConexao();
        
        String sql2 = "INSERT INTO fidelidade (cpf_cli, qtd_compras, desconto) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setString(1, fidelidade.getCpf_cli());
            pstm.setInt(2, fidelidade.getQtd_compras());
            pstm.setFloat(3, fidelidade.getDesconto());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FidelidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(String cpf_cli) {
        abreConexao();
        String sql = "DELETE FROM fidelidade WHERE cpf_cli = '" + cpf_cli+"'";
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
    public void editar(Fidelidade fidelidade) { 
        remover(fidelidade.getCpf_cli());
        adicionar(fidelidade);
    }

    @Override
    public List<Fidelidade> listar() {
        abreConexao();
        List<Fidelidade> ret = new ArrayList<>();
        String sql = "SELECT * FROM fidelidade";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Fidelidade novo = new Fidelidade();
                novo.setCpf_cli(rs.getString("cpf_cli"));
                novo.setQtd_compras(rs.getInt("qtd_compras"));
                novo.setDesconto(rs.getFloat("desconto"));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FidelidadeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }

}
