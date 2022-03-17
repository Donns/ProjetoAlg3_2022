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
public class FinanceiroDAOImp implements FinanceiroDAO {

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
    public void iniciar() {
        //despesa_fun
        abreConexao();
        String sql2 = "INSERT INTO financeiro (pagto_fun,pagto_for,dinheiro_caixa) VALUES (?,?,?);";
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setFloat(1, despesa_fun());
            pstm.setFloat(2, despesa_forn());
            pstm.setFloat(3, caixa());
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
    }

    @Override
    public float despesa_fun() {
        abreConexao();
        String sql = "SELECT SUM (salario) as valor FROM funcionario";
        float valor = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                valor = rs.getFloat("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        return valor;
    }

    @Override
    public float despesa_forn() {
        abreConexao();
        String sql = "SELECT SUM (valor_total_produtos) as valor FROM fornecedora";
        float valor = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                valor = rs.getFloat("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        return valor;
    }

    @Override
    public float caixa() {
        abreConexao();
        String sql = "SELECT SUM (couvert) as valor FROM venda";
        String sql2 = "SELECT SUM (pedido) as valor FROM venda";
        String sql3 = "SELECT SUM (preco_reserva) as valor FROM reserva";
        String sql4 = "SELECT SUM (preco) as valor FROM estacionamento";
        String sql5 = "SELECT count(*) FROM financeiro";
        float valor = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            ResultSet rs2 = con.createStatement().executeQuery(sql2);
            ResultSet rs3 = con.createStatement().executeQuery(sql3);
            ResultSet rs4 = con.createStatement().executeQuery(sql4);
            ResultSet rs5 = con.createStatement().executeQuery(sql5);
            while (rs5.next()) {
                if (rs5.getInt("count") == 0) {
                    while (rs.next() && rs2.next() && rs3.next() && rs4.next()) {
                        valor = rs.getFloat("valor") + rs2.getFloat("valor") + rs3.getFloat("valor") + rs4.getFloat("valor");
                    }
                } else {
                    String sql6 = "SELECT dinheiro_caixa FROM financeiro";
                    ResultSet rs6 = con.createStatement().executeQuery(sql6);
                    while (rs6.next()) {
                        valor = rs6.getFloat("dinheiro_caixa");
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        return valor;
    }

    @Override
    public float atualizaCaixaFun() {
        abreConexao();
        float valor_atualizado = 0;
        String sql = "SELECT (dinheiro_caixa - pagto_fun) AS valor FROM financeiro;";
        String sql2 = "UPDATE financeiro SET dinheiro_caixa = ?;";
        String sql3 = "UPDATE funcionario SET salario = 0;";
        String sql4 = "UPDATE financeiro SET pagto_fun = 0;";
        String sql5 = "UPDATE venda SET couvert = 0;";
        String sql6 = "UPDATE venda SET pedido = 0;";
        String sql7 = "UPDATE reserva SET preco_reserva = 0;";
        String sql8 = "UPDATE estacionamento SET preco = 0;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                valor_atualizado = rs.getFloat("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setFloat(1, valor_atualizado);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql3);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql4);
            PreparedStatement pstm5 = con.prepareStatement(sql5);
            PreparedStatement pstm6 = con.prepareStatement(sql6);
            PreparedStatement pstm7 = con.prepareStatement(sql7);
            PreparedStatement pstm8 = con.prepareStatement(sql8);
            pstm.execute();
            pstm5.execute();
            pstm6.execute();
            pstm7.execute();
            pstm8.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        return valor_atualizado;

    }

    @Override
    public float atualizaCaixaFor() {
        abreConexao();
        float valor_atualizado = 0;
        String sql = "SELECT (dinheiro_caixa - pagto_for) AS valor FROM financeiro;";
        String sql2 = "UPDATE financeiro SET dinheiro_caixa = ?;";
        String sql3 = "UPDATE fornecedora SET valor_total_produtos = 0;";
        String sql4 = "UPDATE financeiro SET pagto_for = 0;";
        String sql5 = "UPDATE venda SET couvert = 0;";
        String sql6 = "UPDATE venda SET pedido = 0;";
        String sql7 = "UPDATE reserva SET preco_reserva = 0;";
        String sql8 = "UPDATE estacionamento SET preco = 0;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                valor_atualizado = rs.getFloat("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql2);
            pstm.setFloat(1, valor_atualizado);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql3);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        abreConexao();
        try {
            PreparedStatement pstm = con.prepareStatement(sql4);
            PreparedStatement pstm5 = con.prepareStatement(sql5);
            PreparedStatement pstm6 = con.prepareStatement(sql6);
            PreparedStatement pstm7 = con.prepareStatement(sql7);
            PreparedStatement pstm8 = con.prepareStatement(sql8);
            pstm.execute();
            pstm5.execute();
            pstm6.execute();
            pstm7.execute();
            pstm8.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FinanceiroDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fechaConexao();
        }
        return valor_atualizado;

    }

}
