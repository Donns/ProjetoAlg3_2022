package dao;

import Entidades.Fidelidade;
import java.util.List;

public interface FidelidadeDAO {
     public void adicionar(Fidelidade fidelidade);
    
    public boolean remover(String cpf);
    
    public void editar(Fidelidade fidelidade);

    public List<Fidelidade> listar();
}
