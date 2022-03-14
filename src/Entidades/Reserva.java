package Entidades;

/**
 *
 * @author Victor
 */
public class Reserva {
    private String cpf_cli;
    private float preco_reserva;
    private String hora_entrada;

    public String getCpf_cli() {
        return cpf_cli;
    }

    public void setCpf_cli(String cpf_cli) {
        this.cpf_cli = cpf_cli;
    }

    public float getPreco_reserva() {
        return preco_reserva;
    }

    public void setPreco_reserva(float preco_reserva) {
        this.preco_reserva = preco_reserva;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }
    
}
