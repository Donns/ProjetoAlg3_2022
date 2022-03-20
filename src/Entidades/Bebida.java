package Entidades;

public class Bebida {
    private int bebida_id;
    private String tipo_bebida;
    private String marca_bebida;
    private float litro_bebida;

    public int getBebida_id() {
        return bebida_id;
    }

    public void setBebida_id(int bebida_id) {
        this.bebida_id = bebida_id;
    }

    public String getTipo_bebida() {
        return tipo_bebida;
    }

    public void setTipo_bebida(String tipo_bebida) {
        this.tipo_bebida = tipo_bebida;
    }

    public String getMarca_bebida() {
        return marca_bebida;
    }

    public void setMarca_bebida(String marca_bebida) {
        this.marca_bebida = marca_bebida;
    }

    public float getLitro_bebida() {
        return litro_bebida;
    }

    public void setLitro_bebida(float litro_bebida) {
        this.litro_bebida = litro_bebida;
    }
}
