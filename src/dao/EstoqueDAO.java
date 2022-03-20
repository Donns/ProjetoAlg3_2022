package dao;

import Entidades.Estoque;
import java.util.List;

public interface EstoqueDAO {
    public void adicionar(Estoque estoque);
    
    
    public boolean remover(int id);
    
    public void editar(Estoque estoque);

    public List<Estoque> listar();
}
