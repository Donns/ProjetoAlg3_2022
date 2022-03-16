package Entidades;

/**
 *
 * @author Victor
 */
public class Cliente {
    private Pessoa cli;
    private int vendas_fidelidade; //mudar nome

    public Pessoa getCli() {
        return cli;
    }

    public void setCli(Pessoa cli) {
        this.cli = cli;
    }

    public int getVendas_fidelidade() {
        return vendas_fidelidade;
    }

    public void setVendas_fidelidade(int vendas_fidelidade) {
        this.vendas_fidelidade = vendas_fidelidade;
    }
    
}
