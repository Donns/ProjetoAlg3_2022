package dao;

import Entidades.Restaurante;
import java.util.List;
/**
 *
 * @author Victor
 */
public interface RestauranteDAO {
    public void adicionar(Restaurante restaurante);
    
    
    public boolean remover(int id);
    
    public List<Restaurante> listar();
    
    public void editar(Restaurante restaurante);
    
}
