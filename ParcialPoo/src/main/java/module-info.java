module org.example.parcialpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.parcialpoo to javafx.fxml;
    exports org.example.parcialpoo;
}