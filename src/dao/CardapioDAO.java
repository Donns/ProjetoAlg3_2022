package dao;

import Entidades.Cardapio;
import java.util.List;

public interface CardapioDAO {
     public void adicionar(Cardapio cardapio);
    
    
    public boolean remover(int prato_id);
    
    public void editar(Cardapio cardapio);

    public List<Cardapio> listar();
}
