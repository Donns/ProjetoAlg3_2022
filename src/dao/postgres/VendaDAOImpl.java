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
import Entidades.Venda;
import dao.VendaDAO;

public class VendaDAOImpl implements VendaDAO{
    
    private Connection con;
    
    private void abreConexao(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123" );
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
     private void abreConexaoCli(){
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
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    private void fechaConexaoCli(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex); //getName?
        }
    }
    
    @Override
    public float preco(String cpf){
        abreConexao();
        String sql = "SELECT preco FROM estacionamento WHERE cpf = '" + cpf + "'";
        float valor = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                valor = rs.getFloat("preco");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
        return valor;
    }
    //private PessoaDAO dao = DAOFactory.createDAO();
    
    @Override
    public float precoReserva(String cpf){
        abreConexao();
        String sql = "SELECT preco_reserva FROM reserva WHERE cpf_cli = '" + cpf + "'";
        float valor = 0;
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                valor = rs.getFloat("preco_reserva");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
        return valor;
    }
    
    public int clienteExiste(Venda venda){
        abreConexaoCli();
        //boolean existe = false;
        int qtd_vendas = 0;
        String sqlC = "SELECT cpf, qtd_vendas FROM cliente";
        try {
            ResultSet rsC = con.createStatement().executeQuery(sqlC);
            while(rsC.next()){
                if (venda.getCpf().equals(rsC.getString("cpf"))){
                    qtd_vendas = rsC.getInt("qtd_vendas") + 1;
                    //existe = true;
                    //break;
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexaoCli();
        }
        return qtd_vendas;
    }
    
    public void VendaCancelada(int cod){
        abreConexaoCli();
        //System.out.println("entrou");
        //boolean existe = false;
        int qtd_vendas = 0;
        String sqlC = "SELECT cpf, qtd_vendas FROM cliente";
        String sqlV = "SELECT cpf FROM venda WHERE cod_pedido = " + cod;
        String cpf = "";
        try{
            ResultSet rsV = con.createStatement().executeQuery(sqlV);
            while(rsV.next()){
                cpf = rsV.getString("cpf");
                //System.out.println("try: " + cpf);
            }
        } catch (SQLException ex) {
            //System.out.println("catch");
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexaoCli();
        }
        
        abreConexao();
        try {
            ResultSet rsC = con.createStatement().executeQuery(sqlC);
            //System.out.println("cpf é: " + cpf);
            while(rsC.next()){
                if (cpf.equals(rsC.getString("cpf"))){
                    qtd_vendas = rsC.getInt("qtd_vendas") - 1;
                    //System.out.println(qtd_vendas);
                    //existe = true;
                    //break;
                }else{
                    //System.out.println("Cliente não cadastrado.");
                }
            }
        }catch (SQLException ex) {
            //System.out.println("catch");
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexaoCli();
        }
        
        abreConexao();
        String atualizaFidelidade = "UPDATE Cliente Set qtd_vendas = ? WHERE cpf = ?";
        try{
            PreparedStatement pstmC = con.prepareStatement(atualizaFidelidade);
            pstmC.setInt(1, qtd_vendas);
            pstmC.setString(2, cpf);
            pstmC.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
    }
    
    
    public int CodigoPedido(){
        abreConexao();
        int cod_pedido = 0;
        String sqlS = "SELECT MAX(cod_pedido) FROM venda";
        try{
            ResultSet rs = con.createStatement().executeQuery(sqlS);
            while(rs.next()){
                cod_pedido = rs.getInt("max")+1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        fechaConexao();
        return cod_pedido;
    }
    
    
    
    @Override
    public void adicionar(Venda venda) {
        int cod_pedido = 1; 
        int fidelidade = clienteExiste(venda);
        abreConexao();
        
        String sqlV = "INSERT INTO Venda (cpf, nome, cod_pedido, estacionamento, reserva,status, gorjeta, couvert,pedido) VALUES (?, ?, ?, ?, ?, ?, ?,?,?);";
        String atualizaFidelidade = "UPDATE Cliente Set qtd_vendas = ? WHERE cpf = ?";
       
        try{
            PreparedStatement pstmC = con.prepareStatement(atualizaFidelidade);
            PreparedStatement pstmP = con.prepareStatement(sqlV);

            pstmC.setInt(1, fidelidade);
            pstmC.setString(2, venda.getCpf());
            pstmC.execute();
            
            if(venda.getCod_pedido() == 0){
                cod_pedido = CodigoPedido();
                abreConexao();
            }
            
            if(venda.getCod_pedido() > 1){
                cod_pedido = venda.getCod_pedido();
            }
            venda.setCod_pedido(cod_pedido);
            pstmP.setString(1, venda.getCpf());
            pstmP.setString(2, venda.getNome());
            pstmP.setInt(3, venda.getCod_pedido());
            pstmP.setBoolean(4, venda.isEstacionou());
            pstmP.setBoolean(5, venda.isReservou());
            pstmP.setBoolean(6, venda.isStatus());
            pstmP.setFloat(7, venda.getGorjeta());
            pstmP.setFloat(8, venda.getCouvert());
            pstmP.setFloat(9, venda.getPedido());
            pstmP.execute();

        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally{
            fechaConexao();
        }
    }

    @Override
    public void editar(Venda venda) { 
        remover(venda.getCod_pedido());
        adicionar(venda);
    }

    @Override
    public boolean remover(int cod) {
        abreConexao();
        String sql = "DELETE FROM venda WHERE cod_pedido = " + cod; //parei aq
        //String sqlP = "DELETE FROM pessoa WHERE cpf = " + cpf;
        try {
            VendaCancelada(cod);
            abreConexao();
            int ret = con.createStatement().executeUpdate(sql);
            return ret > 0;
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return false;
    }

    @Override
    public List<Venda> listar() {
        abreConexao();
        List<Venda> ret = new ArrayList<>();
        String sql = "SELECT * FROM venda;";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Venda nova = new Venda();
                //Pessoa novaP = new Pessoa();
                
                nova.setCpf((rs.getString("cpf")));
                nova.setNome((rs.getString("nome")));
                nova.setCod_pedido(rs.getInt("cod_pedido"));
                nova.setEstacionou(rs.getBoolean("estacionamento"));
                nova.setReservou(rs.getBoolean("reserva"));
                nova.setGorjeta(rs.getFloat("gorjeta"));
                nova.setCouvert(rs.getFloat("couvert"));
                nova.setStatus(rs.getBoolean("status"));
                nova.setPedido(rs.getFloat("pedido"));
                ret.add(nova);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            fechaConexao();
        }
        return ret;
    }

    @Override
    public boolean pagamento(Venda venda, boolean opcao) {
        abreConexao();
        boolean resposta = opcao;
        //System.out.println(venda.getCod_pedido());
        String atualizaStatus = "UPDATE venda Set status = ? WHERE cod_pedido = ?";
        try{
            PreparedStatement pstm = con.prepareStatement(atualizaStatus);
            pstm.setBoolean(1, opcao);
            pstm.setInt(2, venda.getCod_pedido());
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(VendaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fechaConexao();
        }
    return resposta;
    }
}
