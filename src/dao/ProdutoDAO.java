package dao;

import Entidades.Produto;
import java.util.List;

public interface ProdutoDAO {
    public void adicionar(Produto produto);
    
    public boolean remover(int produto_id);
    
    public void editar(Produto produto);

    public List<Produto> listar();
}
