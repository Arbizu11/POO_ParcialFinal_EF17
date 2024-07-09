package org.example.parcialpoo;


import java.sql.Date;

public class Card
{
    private int id_tarjeta;
    private String numero_tarjeta;
    private Date fechaExp;
    private String tipo;
    private int id_cliente;
    private int id_facilitador;

    public Card() {}

    public Card(int id_tarjeta, String numero_tarjeta, Date fechaExp, String tipo,int id_cliente, int id_facilitador) {
        this.id_tarjeta = id_tarjeta;
        this.numero_tarjeta = numero_tarjeta;
        this.fechaExp = fechaExp;
        this.tipo = tipo;
        this.id_cliente = id_cliente;
        this.id_facilitador = id_facilitador;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public Date getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(Date fechaExp) {
        this.fechaExp = fechaExp;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_facilitador() {
        return id_facilitador;
    }

    public void setId_facilitador(int id_facilitador) {
        this.id_facilitador = id_facilitador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
