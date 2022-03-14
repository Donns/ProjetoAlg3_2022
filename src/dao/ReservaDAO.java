package dao;

import Entidades.Reserva;
import java.util.List;

/**
 *
 * @author Victor
 */
public interface ReservaDAO {
    public void adicionar(Reserva reserva);
    
    public void atualizar(Reserva reserva);
    
    public boolean remover(String cpf_cli);
    
    public List<Reserva> listar();
    
    public void editar(Reserva reserva);
}
