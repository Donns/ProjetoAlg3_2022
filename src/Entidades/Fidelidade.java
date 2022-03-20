package Entidades;

public class Fidelidade {
    private String cpf_cli;
    private int qtd_compras;
    private float desconto;

    public String getCpf_cli() {
        return cpf_cli;
    }

    public void setCpf_cli(String cpf_cli) {
        this.cpf_cli = cpf_cli;
    }

    public int getQtd_compras() {
        return qtd_compras;
    }

    public void setQtd_compras(int qtd_compras) {
        this.qtd_compras = qtd_compras;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

}
