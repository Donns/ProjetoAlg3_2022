package Entidades;

public class Venda {
    private String cpf;
    private String nome;
    private int cod_pedido;
    private boolean estacionou;
    private boolean reservou;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    private float gorjeta;
    private float couvert;

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

    public int getCod_pedido() {
        return cod_pedido;
    }

    public void setCod_pedido(int cod_pedido) {
        this.cod_pedido = cod_pedido;
    }

    public boolean isEstacionou() {
        return estacionou;
    }

    public void setEstacionou(boolean estacionou) {
        this.estacionou = estacionou;
    }

    public boolean isReservou() {
        return reservou;
    }

    public void setReservou(boolean reservou) {
        this.reservou = reservou;
    }

    public float getGorjeta() {
        return gorjeta;
    }

    public void setGorjeta(float gorjeta) {
        this.gorjeta = gorjeta;
    }

    public float getCouvert() {
        return couvert;
    }

    public void setCouvert(float couvert) {
        this.couvert = couvert;
    }
}
