package org.example.parcialpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BancoCentralApplication extends Application { // 00379223 Define la clase BancoCentralApplication que extiende Application
    @Override
    public void start(Stage stage) throws IOException { // 00379223 Sobrescribe el método start, que es el punto de entrada para JavaFX
        FXMLLoader fxmlLoader = new FXMLLoader(BancoCentralApplication.class.getResource("BancoCentral.fxml")); // 00379223 Crea un FXMLLoader para cargar el archivo FXML
        Scene scene = new Scene(fxmlLoader.load(), 800, 800); // 00379223 Crea una escena cargando el contenido del archivo FXML y establece sus dimensiones
        stage.setTitle("Examen final POO"); // 00379223 Establece el título de la ventana
        stage.setScene(scene); // 00379223 Establece la escena en el escenario (Stage)
        stage.show(); // 00379223 Muestra el escenario (Stage) en pantalla
    }

    public static void main(String[] args) { // 00379223 Método principal que inicia la aplicación
        launch(); // 00379223 Llama al método launch para iniciar la aplicación JavaFX
    }
}
