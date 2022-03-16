package dao;

import Entidades.Pessoa;
import java.util.List;

public interface PessoaDAO {
    public void adicionar(Pessoa pessoa);
    public void editar(Pessoa pessoa);
    public boolean remover (String cpf);
    public List<Pessoa> listar();
}
