package dao;

import Entidades.Bebida;
import java.util.List;

public interface BebidaDAO {
    public void adicionar(Bebida bebida);
    
    
    public boolean remover(int bebida_id);
    
    public void editar(Bebida bebida);

    public List<Bebida> listar();
}
