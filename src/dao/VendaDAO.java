package dao;

import java.util.List;
import Entidades.Venda;

public interface VendaDAO {
    public void adicionar(Venda venda);
    public void editar(Venda venda);
    public boolean remover (int cod);
    public boolean pagamento(Venda venda, boolean opcao);
    public float preco(String cpf);
    public float precoReserva(String cpf);
    public List<Venda> listar();
}
