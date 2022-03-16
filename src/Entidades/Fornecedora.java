package Entidades;

/**
 *
 * @author Victor
 */
public class Fornecedora {
    private String nome;
    private String tel;
    private String cnpj;
    private String produtos;
    private int id;
    private float valor_total_produtos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor_total_produtos() {
        return valor_total_produtos;
    }

    public void setValor_total_produtos(float valor_total_produtos) {
        this.valor_total_produtos = valor_total_produtos;
    }
    
}
