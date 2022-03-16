package dao.postgres;

import Entidades.Financeiro;
import dao.FinanceiroDAO;
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
public class FinanceiroDAOImp implements FinanceiroDAO{
 private Connection con;

    private void abreConexao() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fechaConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionar(Financeiro financeiro) {
        
        abreConexao();
        int id = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT MAX(id) FROM financeiro;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id++;
        
        String sql2 = "INSERT INTO financeiro (id, pag_forn,pag_func,vendas) VALUES (?,?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setInt(1, id);
            pstm.setFloat(2, financeiro.getPagto_fornec());
            pstm.setFloat(3, financeiro.getPagto_func());
            pstm.setFloat(4, financeiro.getVendas());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public void atualizar(Financeiro financeiro) {
        abreConexao();
        String sql = "UPDATE financeiro WHERE id = ?;";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setFloat(1, financeiro.getPagto_fornec());
            pstm.setFloat(2, financeiro.getPagto_func());
            pstm.setFloat(3, financeiro.getVendas());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public boolean remover(int id) {
        abreConexao();
        String sql = "DELETE FROM financeiro WHERE id = " + id;
        try {
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            fechaConexao();
        }
        
        return false;
    }
    
    @Override
    public void editar(Financeiro financeiro) { 
        remover(financeiro.getId());
        adicionar(financeiro);
    }

    @Override
    public List<Financeiro> listar() {
        abreConexao();
        List<Financeiro> ret = new ArrayList<>();
        String sql = "SELECT * FROM financeiro";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Financeiro novo = new Financeiro();
                novo.setId(rs.getInt("id"));
                novo.setPagto_fornec(Float.parseFloat(rs.getString("pagamento_forn")));
                novo.setPagto_func(Float.parseFloat(rs.getString("pagamento_func")));
                novo.setVendas(Float.parseFloat(rs.getString("vendas")));
                
                ret.add(novo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }

        return ret;
    }
    
}
