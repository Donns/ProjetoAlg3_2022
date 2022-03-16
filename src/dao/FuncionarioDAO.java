package dao;

import Entidades.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    public void adicionar(Funcionario func);
    public void editar(Funcionario func);
    public boolean remover (String cpf);
    public List<Funcionario> listar();
}
