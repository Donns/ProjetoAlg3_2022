package dao;

import Entidades.Comanda;
import java.util.List;

public interface ComandaDAO {
public void adicionar(Comanda comanda);
    
    
    public boolean remover(int comanda_id);

    public void editar(Comanda comanda);
    
    public List<Comanda> listar();    
}
