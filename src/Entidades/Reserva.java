package Entidades;

/**
 *
 * @author Victor
 */
public class Reserva {
    private int cpf_cli;
    private int preco_reserva;
    private int hora_entrada;

    public int getCpf_cli() {
        return cpf_cli;
    }

    public void setCpf_cli(int cpf_cli) {
        this.cpf_cli = cpf_cli;
    }

    public int getPreco_reserva() {
        return preco_reserva;
    }

    public void setPreco_reserva(int preco_reserva) {
        this.preco_reserva = preco_reserva;
    }

    public int getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(int hora_entrada) {
        this.hora_entrada = hora_entrada;
    }
    
}
