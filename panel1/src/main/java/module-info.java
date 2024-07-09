module org.example.panel1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.panel1 to javafx.fxml;
    exports org.example.panel1;
}