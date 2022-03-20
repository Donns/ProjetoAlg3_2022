package Entidades;

public class Entrega {
    private int entrega_id;
    private String nome_prato;
    private String endereco;
    private String cpf_cli;

    public int getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(int entrega_id) {
        this.entrega_id = entrega_id;
    }

    public String getNome_prato() {
        return nome_prato;
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf_cli() {
        return cpf_cli;
    }

    public void setCpf_cli(String cpf_cli) {
        this.cpf_cli = cpf_cli;
    }
}
