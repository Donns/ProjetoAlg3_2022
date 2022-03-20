package Entidades;

public class Comanda {
    private int comanda_id;
    private String prato;
    private String bebida;

    public int getComanda_id() {
        return comanda_id;
    }

    public void setComanda_id(int comanda_id) {
        this.comanda_id = comanda_id;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }
}
