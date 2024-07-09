package org.example.examenfinalpoo.Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection { //00379223 Define la clase DatabaseConnection

    private static final String url = "jdbc:mysql://localhost:3306/dbBancoCentral"; //00379223 URL de la base de datos
    private static final String userName = "root"; //00379223 Nombre de usuario para la base de datos
    private static final String password = "JacyFac2"; //00379223 Contraseña para la base de datos

    public static Connection getConnection() throws SQLException { //00379223 Método estático para obtener una conexión a la base de datos
        return DriverManager.getConnection(url, userName, password); //00379223 Devuelve una conexión utilizando DriverManager
    }
}
