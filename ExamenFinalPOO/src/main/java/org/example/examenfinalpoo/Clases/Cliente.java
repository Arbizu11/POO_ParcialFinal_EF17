package org.example.examenfinalpoo.Clases;

public class Cliente { //00042423 Define la clase Cliente
    private int id; //00042423 Declara una variable privada para almacenar el ID del cliente
    private String nombre; //00042423 Declara una variable privada para almacenar el nombre del cliente
    private String direccion; //00042423 Declara una variable privada para almacenar la dirección del cliente
    private String telefono; //00042423 Declara una variable privada para almacenar el teléfono del cliente

    public Cliente(int id, String nombre, String direccion, String telefono) { // 00042423 Constructor de la clase Cliente
        this.id = id; // 00042423 Inicializa la variable id con el valor pasado como parámetro
        this.nombre = nombre; //00042423 Inicializa la variable nombre con el valor pasado como parámetro
        this.direccion = direccion; // 00042423 Inicializa la variable direccion con el valor pasado como parámetro
        this.telefono = telefono; //00042423 Inicializa la variable telefono con el valor pasado como parámetro
    }


    public int getId() { // 00042423 Método getter para obtener el ID del cliente
        return id; // 00042423 Retorna el valor de la variable id
    }

    public void setId(int id) { //00042423 Método setter para establecer el ID del cliente
        this.id = id; //00042423 Asigna el valor pasado como parámetro a la variable id
    }

    public String getNombre() { //00042423 Método getter para obtener el nombre del cliente
        return nombre; //00042423 Retorna el valor de la variable nombre
    }

    public void setNombre(String nombre) { // 00042423 Método setter para establecer el nombre del cliente
        this.nombre = nombre; //00042423 Asigna el valor pasado como parámetro a la variable nombre
    }

    public String getDireccion() { //00042423 Método getter para obtener la dirección del cliente
        return direccion; // 00042423 Retorna el valor de la variable direccion
    }

    public void setDireccion(String direccion) { //00042423 Método setter para establecer la dirección del cliente
        this.direccion = direccion; //00042423 Asigna el valor pasado como parámetro a la variable direccion
    }

    public String getTelefono() { //00042423 Método getter para obtener el teléfono del cliente
        return telefono; //00042423 Retorna el valor de la variable telefono
    }

    public void setTelefono(String telefono) { //00042423 Método setter para establecer el teléfono del cliente
        this.telefono = telefono; //00042423 Asigna el valor pasado como parámetro a la variable telefono
    }
}
