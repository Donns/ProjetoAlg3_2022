package Entidades;

public class Cardapio {

    private int prato_id;
    private String nome_prato;
    private String descricao;
    private int classificacao;
    private float valor;

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPrato_id() {
        return prato_id;
    }

    public void setPrato_id(int prato_id) {
        this.prato_id = prato_id;
    }

    public String getNome_prato() {
        return nome_prato;
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

}
