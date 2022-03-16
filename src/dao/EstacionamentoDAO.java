package dao;

import Entidades.Estacionamento;
import java.util.List;

public interface EstacionamentoDAO {
    public void adicionar(Estacionamento estacionamento);
    public void editar(Estacionamento estacionamento);
    public boolean remover (String cpf);
    public List<Estacionamento> listar();
}
