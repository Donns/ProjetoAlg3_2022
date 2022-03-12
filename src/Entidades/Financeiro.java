package Entidades;

/**
 *
 * @author Victor
 */
public class Financeiro {
    private float pagto_fornec;
    private float pagto_func;
    private float vendas;

    public float getPagto_fornec() {
        return pagto_fornec;
    }

    public void setPagto_fornec(float pagto_fornec) {
        this.pagto_fornec = pagto_fornec;
    }

    public float getPagto_func() {
        return pagto_func;
    }

    public void setPagto_func(float pagto_func) {
        this.pagto_func = pagto_func;
    }

    public float getGanho_total() {
        return vendas;
    }

    public void setGanho_total(float ganho_total) {
        this.vendas = ganho_total;
    }
    
}
