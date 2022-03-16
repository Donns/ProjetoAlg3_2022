package Entidades;

public class Estacionamento {
    private String cpf;
    private String nome;
    private int cod_vaga;
    private float preco;
    private String hora_entrada;
    private String hora_saida;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_vaga() {
        return cod_vaga;
    }

    public void setCod_vaga(int cod_vaga) {
        this.cod_vaga = cod_vaga;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_saida() {
        return hora_saida;
    }

    public void setHora_saida(String hora_saida) {
        this.hora_saida = hora_saida;
    }
    
    
}
