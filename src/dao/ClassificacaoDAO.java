package dao;

import Entidades.Classificacao;
import java.util.List;

public interface ClassificacaoDAO {
     public void adicionar(Classificacao classificacao);
    
    
    public boolean remover(int classificacao_id);
    
    public void editar(Classificacao classificacao);

    public List<Classificacao> listar();
}
