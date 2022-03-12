package Entidades;

/**
 *
 * @author Victor
 */
public class Fornecedora {
    private String nome_fornec;
    private String tel_fornec;
    private String cnpj_fornec;
    private String produtos_fornec;//lista
    private int id_fornec;

    public int getId_fornec() {
        return id_fornec;
    }

    public void setId_fornec(int id_fornec) {
        this.id_fornec = id_fornec;
    }

    public String getNome_fornec() {
        return nome_fornec;
    }

    public void setNome_fornec(String nome_fornec) {
        this.nome_fornec = nome_fornec;
    }

    public String getProdutos_fornec() {
        return produtos_fornec;
    }

    public void setProdutos_fornec(String produtos_fornec) {
        this.produtos_fornec = produtos_fornec;
    }

    public String getTel_fornec() {
        return tel_fornec;
    }

    public void setTel_fornec(String tel_fornec) {
        this.tel_fornec = tel_fornec;
    }

    public String getCnpj_fornec() {
        return cnpj_fornec;
    }

    public void setCnpj_fornec(String cnpj_fornec) {
        this.cnpj_fornec = cnpj_fornec;
    }
    
}
