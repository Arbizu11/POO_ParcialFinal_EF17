module org.example.examenfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.examenfinalpoo to javafx.fxml;
    exports org.example.examenfinalpoo;
    exports org.example.examenfinalpoo.Clases;
    opens org.example.examenfinalpoo.Clases to javafx.fxml;
}