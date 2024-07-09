package org.example.examenfinalpoo.Clases;

public class Facilitador { // 00042423 Define la clase Facilitador

    private int id; // 00042423 Declara una variable privada para almacenar el ID del facilitador
    private String facilitador; // 00042423 Declara una variable privada para almacenar el nombre del facilitador

    public Facilitador(int id, String facilitador) { // 00042423 Constructor de la clase Facilitador
        this.id = id; // 00042423 Inicializa la variable id con el valor pasado como parámetro
        this.facilitador = facilitador; // 00042423 Inicializa la variable facilitador con el valor pasado como parámetro
    }

    public int getId() { // 00042423 Método getter para obtener el ID del facilitador
        return id; // 00042423 Retorna el valor de la variable id
    }

    public void setId(int id) { // 00042423 Método setter para establecer el ID del facilitador
        this.id = id; // 00042423 Asigna el valor pasado como parámetro a la variable id
    }

    public String getFacilitador() { // 00042423 Método getter para obtener el nombre del facilitador
        return facilitador; // 00042423 Retorna el valor de la variable facilitador
    }

    public void setFacilitador(String facilitador) { // 00042423 Método setter para establecer el nombre del facilitador
        this.facilitador = facilitador; // 00042423 Asigna el valor pasado como parámetro a la variable facilitador
    }

    @Override
    public String toString() { // 00042423 Sobrescribe el método toString de la clase Object
        return facilitador; // 00042423 Retorna el nombre del facilitador para utilizarlo en la comboVox
    }
}
