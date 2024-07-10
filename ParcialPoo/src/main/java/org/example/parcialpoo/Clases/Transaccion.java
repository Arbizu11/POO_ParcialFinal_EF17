package org.example.parcialpoo.Clases;

import java.time.LocalDate;

public class Transaccion { // 00042423 Define la clase Transaccion
    private int id; // 00042423 Declara una variable privada para almacenar el ID de la transacción
    private LocalDate fecha; // 00042423 Declara una variable privada para almacenar la fecha de la transacción
    private float monto; // 00042423 Declara una variable privada para almacenar el monto de la transacción
    private String descripcion; // 00042423 Declara una variable privada para almacenar la descripción de la transacción
    private int id_tarjeta; // 00042423 Declara una variable privada para almacenar el ID de la tarjeta asociada

    public Transaccion(int id, LocalDate fecha, float monto, String descripcion, int id_tarjeta) { // 00042423 Constructor de la clase Transaccion
        this.id = id; // 00042423 Inicializa la variable id con el valor pasado como parámetro
        this.fecha = fecha; // 00042423 Inicializa la variable fecha con el valor pasado como parámetro
        this.monto = monto; // 00042423 Inicializa la variable monto con el valor pasado como parámetro
        this.descripcion = descripcion; // 00042423 Inicializa la variable descripcion con el valor pasado como parámetro
        this.id_tarjeta = id_tarjeta; // 00042423 Inicializa la variable id_tarjeta con el valor pasado como parámetro
    }

    public int getId() { // 00042423 Método getter para obtener el ID de la transacción
        return id; // 00042423 Retorna el valor de la variable id
    }

    public void setId(int id) { // 00042423 Método setter para establecer el ID de la transacción
        this.id = id; // 00042423 Asigna el valor pasado como parámetro a la variable id
    }

    public LocalDate getFecha() { // 00042423 Método getter para obtener la fecha de la transacción
        return fecha; // 00042423 Retorna el valor de la variable fecha
    }

    public void setFecha(LocalDate fecha) { // 00042423 Método setter para establecer la fecha de la transacción
        this.fecha = fecha; // 00042423 Asigna el valor pasado como parámetro a la variable fecha
    }

    public float getMonto() { // 00042423 Método getter para obtener el monto de la transacción
        return monto; // 00042423 Retorna el valor de la variable monto
    }

    public void setMonto(float monto) { // 00042423 Método setter para establecer el monto de la transacción
        this.monto = monto; // 00042423 Asigna el valor pasado como parámetro a la variable monto
    }

    public String getDescripcion() { // 00042423 Método getter para obtener la descripción de la transacción
        return descripcion; // 00042423 Retorna el valor de la variable descripcion
    }

    public void setDescripcion(String descripcion) { // 00042423 Método setter para establecer la descripción de la transacción
        this.descripcion = descripcion; // 00042423 Asigna el valor pasado como parámetro a la variable descripcion
    }

    public int getId_tarjeta() { // 00042423 Método getter para obtener el ID de la tarjeta asociada
        return id_tarjeta; // 00042423 Retorna el valor de la variable id_tarjeta
    }

    public void setId_tarjeta(int id_tarjeta) { // 00042423 Método setter para establecer el ID de la tarjeta asociada
        this.id_tarjeta = id_tarjeta; // 00042423 Asigna el valor pasado como parámetro a la variable id_tarjeta
    }
}

