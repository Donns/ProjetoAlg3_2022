package dao;

import Entidades.Entrega;
import java.util.List;

public interface EntregaDAO {
    public void adicionar(Entrega entrega);
    
    
    public boolean remover(int entrega_id);
    
    public void editar(Entrega entrega);

    public List<Entrega> listar();
    
}
