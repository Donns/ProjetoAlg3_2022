package Entidades;

/**
 *
 * @author Victor
 */
public class Restaurante extends Endereco{
    private String nome;
    private String tel_rest;
    private int id;

    public int getId_rest() {
        return id;
    }

    public void setId_rest(int id_rest) {
        this.id = id_rest;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel_rest() {
        return tel_rest;
    }

    public void setTel_rest(String tel_rest) {
        this.tel_rest = tel_rest;
    }
    
}
