package org.example.examenfinalpoo.Clases;

import java.time.LocalDate;

public class Tarjeta { // 00379223 Define la clase Tarjeta

    private int id; // 00379223 Declara una variable privada para almacenar el ID de la tarjeta
    private String numero; // 00379223 Declara una variable privada para almacenar el número de la tarjeta
    private LocalDate fechaExp; // 00379223 Declara una variable privada para almacenar la fecha de expiración de la tarjeta
    private String tipo; // 00379223 Declara una variable privada para almacenar el tipo de la tarjeta
    private int id_cliente; // 00379223 Declara una variable privada para almacenar el ID del cliente
    private String facilitador; // 00379223 Declara una variable privada para almacenar el nombre del facilitador

    public Tarjeta(int id, String numero, LocalDate fechaExp, String tipo, int id_cliente, String facilitador) { // 00379223 Constructor de la clase Tarjeta
        this.id = id; // 00379223 Inicializa la variable id con el valor pasado como parámetro
        this.numero = numero; // 00379223 Inicializa la variable numero con el valor pasado como parámetro
        this.fechaExp = fechaExp; // 00379223 Inicializa la variable fechaExp con el valor pasado como parámetro
        this.tipo = tipo; // 00379223 Inicializa la variable tipo con el valor pasado como parámetro
        this.id_cliente = id_cliente; // 00379223 Inicializa la variable id_cliente con el valor pasado como parámetro
        this.facilitador = facilitador; // 00379223 Inicializa la variable facilitador con el valor pasado como parámetro
    }

    public int getId() { // 00379223 Método getter para obtener el ID de la tarjeta
        return id; // 00379223 Retorna el valor de la variable id
    }

    public void setId(int id) { // 00379223 Método setter para establecer el ID de la tarjeta
        this.id = id; // 00379223 Asigna el valor pasado como parámetro a la variable id
    }

    public String getNumero() { // 00379223 Método getter para obtener el número de la tarjeta
        return numero; // 00379223 Retorna el valor de la variable numero
    }

    public void setNumero(String numero) { // 00379223 Método setter para establecer el número de la tarjeta
        this.numero = numero; // 00379223 Asigna el valor pasado como parámetro a la variable numero
    }

    public LocalDate getFechaExp() { // 00379223 Método getter para obtener la fecha de expiración de la tarjeta
        return fechaExp; // 00379223 Retorna el valor de la variable fechaExp
    }

    public void setFechaExp(LocalDate fechaExp) { // 00379223 Método setter para establecer la fecha de expiración de la tarjeta
        this.fechaExp = fechaExp; // 00379223 Asigna el valor pasado como parámetro a la variable fechaExp
    }

    public String getTipo() { // 00379223 Método getter para obtener el tipo de la tarjeta
        return tipo; // 00379223 Retorna el valor de la variable tipo
    }

    public void setTipo(String tipo) { // 00379223 Método setter para establecer el tipo de la tarjeta
        this.tipo = tipo; // 00379223 Asigna el valor pasado como parámetro a la variable tipo
    }

    public int getId_cliente() { // 00379223 Método getter para obtener el ID del cliente
        return id_cliente; // 00379223 Retorna el valor de la variable id_cliente
    }

    public void setId_cliente(int id_cliente) { // 00379223 Método setter para establecer el ID del cliente
        this.id_cliente = id_cliente; // 00379223 Asigna el valor pasado como parámetro a la variable id_cliente
    }

    public String getFacilitador() { // 00379223 Método getter para obtener el nombre del facilitador
        return facilitador; // 00379223 Retorna el valor de la variable facilitador
    }

    public void setFacilitador(String facilitador) { // 00379223 Método setter para establecer el nombre del facilitador
        this.facilitador = facilitador; // 00379223 Asigna el valor pasado como parámetro a la variable facilitador
    }
}
