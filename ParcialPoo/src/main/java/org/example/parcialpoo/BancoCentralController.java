package org.example.parcialpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.parcialpoo.Clases.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BancoCentralController implements Initializable {// 00379223 Define la clase BancoCentralController que implementa Initializable

    private Connection connection; // 00379223 Declara una variable privada para almacenar la conexión a la base de datos

    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableView<Cliente> tblCliente; // 00379223 Define una TableView para mostrar clientes
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Cliente, Integer> idClienteColumn; // 00379223 Define una TableColumn para el ID de cliente en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Cliente, String> nombreClienteColumn; // 00379223 Define una TableColumn para el nombre de cliente en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Cliente, String> direccionClienteColumn; // 00379223 Define una TableColumn para la dirección de cliente en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Cliente, String> telefonoClienteColumn; // 00379223 Define una TableColumn para el teléfono de cliente en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableView<Tarjeta> tblTarjeta; // 00379223 Define una TableView para mostrar tarjetas
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, Integer> idTarjetaColumn; // 00379223 Define una TableColumn para el ID de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, String> numeroTarjetaColumn; // 00379223 Define una TableColumn para el número de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, LocalDate> fechaExpTarjetaColumn; // 00379223 Define una TableColumn para la fecha de expiración de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, String> tipoTarjetaColumn; // 00379223 Define una TableColumn para el tipo de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, Integer> idClienteTarjetaColumn; // 00379223 Define una TableColumn para el ID de cliente de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Tarjeta, String> facilitadorTarjetaColumn; // 00379223 Define una TableColumn para el facilitador de tarjeta en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableView<Transaccion> tblTransaccion; // 00379223 Define una TableView para mostrar transacciones
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Transaccion, Integer> idTransaccionColumn; // 00379223 Define una TableColumn para el ID de transacción en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Transaccion, LocalDate> fechaTransaccionColumn; // 00379223 Define una TableColumn para la fecha de transacción en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Transaccion, Float> montoTransaccionColumn; // 00379223 Define una TableColumn para el monto de transacción en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Transaccion, String> descripcionTransaccionColumn; // 00379223 Define una TableColumn para la descripción de transacción en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TableColumn<Transaccion, Integer> idTarjetaTransaccionColumn; // 00379223 Define una TableColumn para el ID de tarjeta de transacción en la TableView
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorNombre; // 00379223 Define una etiqueta para mostrar errores relacionados con el nombre de cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorDireccion; // 00379223 Define una etiqueta para mostrar errores relacionados con la dirección de cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorTelefono; // 00379223 Define una etiqueta para mostrar errores relacionados con el teléfono de cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextArea txtNombreCliente; // 00379223 Define un área de texto para ingresar o mostrar el nombre del cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextArea txtDireccionCliente; // 00379223 Define un área de texto para ingresar o mostrar la dirección del cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextArea txtTelefonoCliente; // 00379223 Define un área de texto para ingresar o mostrar el teléfono del cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorIdCliente; // 00379223 Define una etiqueta para mostrar errores relacionados con el ID del cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorNumero; // 00379223 Define una etiqueta para mostrar errores relacionados con el número de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorFechaExp; // 00379223 Define una etiqueta para mostrar errores relacionados con la fecha de expiración de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorTipoTarjeta; // 00379223 Define una etiqueta para mostrar errores relacionados con el tipo de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorFacilitador; // 00379223 Define una etiqueta para mostrar errores relacionados con el facilitador de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private ComboBox<Facilitador> facilitador; // 00379223 Define un ComboBox para seleccionar un facilitador de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtIdCliente; // 00379223 Define un campo de texto para ingresar el ID del cliente
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtNumeroTarjeta; // 00379223 Define un campo de texto para ingresar el número de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private DatePicker dpFechaExp; // 00379223 Define un DatePicker para seleccionar la fecha de expiración de la tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private ToggleGroup tgTipoTarjeta; // 00379223 Define un ToggleGroup para agrupar los RadioButton de tipo de tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private RadioButton rbCredito; // 00379223 Define un RadioButton para seleccionar el tipo de tarjeta de crédito
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private RadioButton rbDebito; // 00379223 Define un RadioButton para seleccionar el tipo de tarjeta de débito
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorIdTarjeta; // 00379223 Define una etiqueta para mostrar errores relacionados con el ID de la tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorMonto; // 00379223 Define una etiqueta para mostrar errores relacionados con el monto de la transacción
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorFechaCompra; // 00379223 Define una etiqueta para mostrar errores relacionados con la fecha de la transacción
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private Label lblErrorDescripcionCompra; // 00379223 Define una etiqueta para mostrar errores relacionados con la descripción de la transacción
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtIdTarjeta; // 00379223 Define un campo de texto para ingresar el ID de la tarjeta
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtMonto; // 00379223 Define un campo de texto para ingresar el monto de la transacción
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private DatePicker dpFechaCompra; // 00379223 Define un DatePicker para seleccionar la fecha de la transacción
    @FXML // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextArea txtDescripcionCompra; // 00379223 Define un TextArea para ingresar la descripción de la transacción
    @FXML  // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtIdCliente2; //00379223 Define un TextField para ingresar el id del cLiente
    @FXML  // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtIdTarjeta2; //00379223 Define un TextField para ingresar el id de la tarjeta
    @FXML  // 00379223 Anotación FXML para inyectar los controles definidos en el archivo FXML
    private TextField txtIdTransaccion; //00379223 Define un TextField para ingresar el id de la transaccion

    private Stage stage; //00377723 Stage para generar la ventana
    private Scene scene; //00377723 Escena que carga dentro de la ventana de Stage
    private Parent root; //00377723 Almacena todos los Children de la escena

    @FXML
    public void cambiarEscenaReportes(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Reportes-view.fxml")); //00377723 Carga el archivo fxml de la escena principal
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //00377723 prepara la ventana
        scene = new Scene(root); //00377723 guarda la escena con el fxml de BancoCentral
        stage.setScene(scene); //00377723  agrega la escena a la ventana antes creada
        stage.show(); //00377723 Muestra la escena principal
    }



    @Override // 00379223 Sobrescribe el método initialize de la interfaz Initializable
    public void initialize(URL url, ResourceBundle resourceBundle) { // 00379223 Método llamado al inicializar el controlador
        connectDatabase(); // 00379223 Establece la conexión a la base de datos
        setupTableViewCliente(); // 00379223 Configura la TableView para mostrar clientes
        setupTableViewTarjeta(); // 00379223 Configura la TableView para mostrar tarjetas
        setupTableViewTransaccion(); // 00379223 Configura la TableView para mostrar transacciones

        setUpComboVox(); // 00379223 Configura el ComboBox para mostrar facilitadores
    }

    private void connectDatabase() { // 00379223 Método para conectar a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   // 00379223 Carga el controlador JDBC para MySQL
            connection = DatabaseConnection.getConnection(); // 00379223 Obtiene la conexión a la base de datos utilizando la clase DatabaseConnection
        } catch (ClassNotFoundException |
                 SQLException e) { // 00379223 Captura excepciones ClassNotFoundException y SQLException
            e.printStackTrace(); // 00379223 Imprime la traza de la excepción
        }
    }

    private void setupTableViewCliente() { // 00379223 Método para configurar la TableView de clientes
        idClienteColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // 00379223 Asigna la propiedad de fábrica para el ID del cliente
        nombreClienteColumn.setCellValueFactory(new PropertyValueFactory<>("nombre")); // 00379223 Asigna la propiedad de fábrica para el nombre del cliente
        direccionClienteColumn.setCellValueFactory(new PropertyValueFactory<>("direccion")); // 00379223 Asigna la propiedad de fábrica para la dirección del cliente
        telefonoClienteColumn.setCellValueFactory(new PropertyValueFactory<>("telefono")); // 00379223 Asigna la propiedad de fábrica para el teléfono del cliente
    }

    private void setupTableViewTarjeta() { // 00379223 Método para configurar la TableView de tarjetas
        idTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // 00379223 Asigna la propiedad de fábrica para el ID de la tarjeta
        numeroTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("numero")); // 00379223 Asigna la propiedad de fábrica para el número de la tarjeta
        fechaExpTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaExp")); // 00379223 Asigna la propiedad de fábrica para la fecha de expiración de la tarjeta
        tipoTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("tipo")); // 00379223 Asigna la propiedad de fábrica para el tipo de tarjeta
        idClienteTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("id_cliente")); // 00379223 Asigna la propiedad de fábrica para el ID de cliente de la tarjeta
        facilitadorTarjetaColumn.setCellValueFactory(new PropertyValueFactory<>("facilitador")); // 00379223 Asigna la propiedad de fábrica para el facilitador de la tarjeta
    }


    private void setupTableViewTransaccion() { // 00379223 Método para configurar la TableView de transacciones
        idTransaccionColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // 00379223 Asigna la propiedad de fábrica para el ID de la transacción
        fechaTransaccionColumn.setCellValueFactory(new PropertyValueFactory<>("fecha")); // 00379223 Asigna la propiedad de fábrica para la fecha de la transacción
        montoTransaccionColumn.setCellValueFactory(new PropertyValueFactory<>("monto")); // 00379223 Asigna la propiedad de fábrica para el monto de la transacción
        descripcionTransaccionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion")); // 00379223 Asigna la propiedad de fábrica para la descripción de la transacción
        idTarjetaTransaccionColumn.setCellValueFactory(new PropertyValueFactory<>("id_tarjeta")); // 00379223 Asigna la propiedad de fábrica para el ID de la tarjeta asociada a la transacción
    }


    private void setUpComboVox() { // 00379223 Método para configurar el ComboBox de facilitadores
        facilitador.getItems().clear(); // 00379223 Limpia los elementos existentes en el ComboBox

        try {
            Statement st = connection.createStatement(); // 00379223 Crea una declaración para ejecutar consultas SQL
            ResultSet rs = st.executeQuery("SELECT * FROM tbFacilitador"); // 00379223 Ejecuta una consulta para obtener los facilitadores desde la base de datos

            while (rs.next()) { // 00379223 Itera sobre los resultados de la consulta
                Facilitador facilitador1 = new Facilitador(rs.getInt("id"), rs.getString("nombre")); // 00379223 Crea un objeto Facilitador con los datos obtenidos de la consulta
                facilitador.getItems().add(facilitador1); // 00379223 Agrega el facilitador al ComboBox
            }

        } catch (Exception e) { // 00379223 Captura cualquier excepción y muestra un mensaje de error
            showAlertError("Error", "No se pudo obtener los facilitadores");//00379223  Muestra una alerta de error al no guardar los facilitadores
        }
    }
    private void showAlertError(String title, String content) { // 00379223 Muestra una alerta de error con el título y contenido especificados
        Alert alert = new Alert(Alert.AlertType.ERROR); //0037923 Crea una alerta de tipo ERROR
        alert.setTitle(title); //00379223 Establece el título
        alert.setContentText(content); //00379223 Establece el contenido
        alert.showAndWait(); //00379223 Muestra la alerta y espera
    }
    private void showAlertInformation(String title, String content) { // 00379223 Muestra una alerta de información con el título y contenido especificados
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // 00379223 Crea una alerta de tipo INFORMATION
        alert.setTitle(title); //00379223 Establece el título
        alert.setContentText(content); //00379223 Establece el contenido
        alert.showAndWait(); //00379223 Muestra la alerta y espera
    }

    private boolean clienteExiste(int idCliente) { // 00379223 Definición del método clienteExiste que recibe un idCliente y devuelve un booleano
        try (PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM tbCliente WHERE id = ?")) { // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL
            ps.setInt(1, idCliente); // 00379223 Asignar el valor de idCliente al primer parámetro de la consulta
            try (ResultSet rs = ps.executeQuery()) { // 00379223 Ejecutar la consulta y obtener el resultado en un ResultSet
                return rs.next(); // 00379223 Devolver true si hay un resultado (el cliente existe), false en caso contrario
            }
        } catch (SQLException e) { // 00379223 Capturar cualquier excepción SQL que ocurra
            return false; // 00379223 Devolver false si ocurre una excepción (el cliente no existe)
        }
    }

    private boolean tarjetaExiste(int idTarjeta) { // 00379223 Definición del método tarjetaExiste que recibe un idTarjeta y devuelve un booleano
        try (PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM tbTarjeta WHERE id_tarjeta = ?")) { // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL
            ps.setInt(1, idTarjeta); // 00379223 Asignar el valor de idTarjeta al primer parámetro de la consulta
            try (ResultSet rs = ps.executeQuery()) { // 00379223 Ejecutar la consulta y obtener el resultado en un ResultSet
                return rs.next(); // 00379223 Devolver true si hay un resultado (la tarjeta existe), false en caso contrario
            }
        } catch (SQLException e) { // 00379223 Capturar cualquier excepción SQL que ocurra
            return false; // 00379223 Devolver false si ocurre una excepción (la tarjeta no existe)
        }
    }

    private boolean transaccionExiste(int idTransaccion) { // 00379223 Definición del método transaccionExiste que recibe un idTransaccion y devuelve un booleano
        try (PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM tbTransaccion WHERE id = ?")) { // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL
            ps.setInt(1, idTransaccion); // 00379223 Asignar el valor de idTransaccion al primer parámetro de la consulta
            try (ResultSet rs = ps.executeQuery()) { // 00379223 Ejecutar la consulta y obtener el resultado en un ResultSet
                return rs.next(); // 00379223 Devolver true si hay un resultado (la transacción existe), false en caso contrario
            }
        } catch (SQLException e) { // 00379223 Capturar cualquier excepción SQL que ocurra
            return false; // 00379223 Devolver false si ocurre una excepción (la transacción no existe)
        }
    }

    @FXML
    private void agregarCliente() {// 00379223 Método controlador para agregar un cliente
        lblErrorDireccion.setText(""); // 00379223 Limpia el texto de error de dirección
        lblErrorNombre.setText(""); // 00379223 Limpia el texto de error de nombre
        lblErrorTelefono.setText(""); // 00379223 Limpia el texto de error de teléfono

        String nombre = txtNombreCliente.getText(); // 00379223 Obtiene el nombre del cliente desde el campo de texto
        String direccion = txtDireccionCliente.getText(); // 00379223 Obtiene la dirección del cliente desde el campo de texto
        String telefono = txtTelefonoCliente.getText(); // 00379223 Obtiene el teléfono del cliente desde el campo de texto

        boolean flag = false; // 00379223 Bandera para controlar si hay errores en los campos obligatorios

        if (nombre.equals("")) { // 00379223 Verifica si el nombre está vacío
            lblErrorNombre.setText("Campo Obligatorio"); // 00379223 Establece un mensaje de error en el campo de nombre
            flag = true; // 00379223 Activa la bandera indicando que hay un error
        }
        if (direccion.equals("")) { // 00379223 Verifica si la dirección está vacía
            lblErrorDireccion.setText("Campo Obligatorio"); // 00379223 Establece un mensaje de error en el campo de dirección
            flag = true; // 00379223 Activa la bandera indicando que hay un error
        }
        if (telefono.equals("")) { // 00379223 Verifica si el teléfono está vacío
            lblErrorTelefono.setText("Campo Obligatorio"); // 00379223 Establece un mensaje de error en el campo de teléfono
            flag = true; // 00379223 Activa la bandera indicando que hay un error
        }

        if (!flag) { // 00379223 Si no hay errores en los campos obligatorios
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO tbCliente(nombre, direccion, telefono) VALUES (?,?,?)"); // 00379223 Prepara la consulta SQL para insertar un nuevo cliente
                ps.setString(1, nombre); // 00379223 Establece el nombre del cliente en la consulta preparada
                ps.setString(2, direccion); // 00379223 Establece la dirección del cliente en la consulta preparada
                ps.setString(3, telefono); // 00379223 Establece el teléfono del cliente en la consulta preparada

                ps.executeUpdate(); // 00379223 Ejecuta la actualización para insertar el nuevo cliente en la base de datos

                showAlertInformation("Tabla Cliente modificada!", "Se agregó un nuevo cliente"); // 00379223 Muestra una alerta informativa de que se modificó la tabla de clientes

                txtNombreCliente.clear(); // 00379223 Limpia el campo de texto del nombre del cliente
                txtDireccionCliente.clear(); // 00379223 Limpia el campo de texto de la dirección del cliente
                txtTelefonoCliente.clear(); // 00379223 Limpia el campo de texto del teléfono del cliente

            } catch (Exception e) { // 00379223 Captura cualquier excepción y muestra un mensaje de error
                showAlertError("Error", "No se pudo agregar el cliente"); // 00379223 Muestra una alerta de error indicando que no se pudo agregar el cliente
            }
        }
    }

    @FXML // 00379223 Anotación para marcar el método como manejador de eventos en JavaFX
    public void agregarTarjetas() { // 00379223 Definición del método agregarTarjetas
        lblErrorIdCliente.setText(""); // 00379223 Limpiar el texto del label de error de ID de cliente
        lblErrorNumero.setText(""); // 00379223 Limpiar el texto del label de error de número de tarjeta
        lblErrorFechaExp.setText(""); // 00379223 Limpiar el texto del label de error de fecha de expiración
        lblErrorTipoTarjeta.setText(""); // 00379223 Limpiar el texto del label de error de tipo de tarjeta
        lblErrorFacilitador.setText(""); // 00379223 Limpiar el texto del label de error de facilitador

        boolean flag = false; // 00379223 Inicializar la bandera de error a false
        String numero = txtNumeroTarjeta.getText(); // 00379223 Obtener el texto del campo de número de tarjeta
        LocalDate fechaExp = dpFechaExp.getValue(); // 00379223 Obtener el valor del campo de fecha de expiración
        String tipoTarjeta = ""; // 00379223 Inicializar el tipo de tarjeta a una cadena vacía
        int idCliente = 0; // 00379223 Inicializar el ID del cliente a 0

        if (rbCredito.isSelected()) { // 00379223 Verificar si el radio button de crédito está seleccionado
            tipoTarjeta = "Credito"; // 00379223 Asignar "Credito" a tipoTarjeta
        } else if (rbDebito.isSelected()) { // 00379223 Verificar si el radio button de débito está seleccionado
            tipoTarjeta = "Debito"; // 00379223 Asignar "Debito" a tipoTarjeta
        } else { // 00379223 Si no hay ningún radio button seleccionado
            lblErrorTipoTarjeta.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de tipo de tarjeta
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (numero.equals("")) { // 00379223 Verificar si el número de tarjeta está vacío
            lblErrorNumero.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de número de tarjeta
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (dpFechaExp.getValue() == null) { // 00379223 Verificar si la fecha de expiración no está seleccionada
            lblErrorFechaExp.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de fecha de expiración
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (facilitador.getValue() == null) { // 00379223 Verificar si el facilitador no está seleccionado
            lblErrorFacilitador.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de facilitador
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            idCliente = Integer.parseInt(txtIdCliente.getText()); // 00379223 Intentar convertir el texto del campo ID de cliente a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de cliente válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!clienteExiste(idCliente)) { // 00379223 Verificar si el cliente no existe
            showAlertError("Error", "El ID de cliente no existe"); // 00379223 Mostrar un mensaje de alerta de error
            lblErrorIdCliente.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de ID de cliente
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (!flag) { // 00379223 Si no hay errores
            try { // 00379223 Bloque try para manejar posibles excepciones
                PreparedStatement ps = connection.prepareStatement("INSERT INTO tbTarjeta(numero_tarjeta, fechaExp, tipo, id_cliente, id_facilitador) VALUES (?,?,?,?,?)"); // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL de inserción
                ps.setString(1, numero); // 00379223 Asignar el valor de número de tarjeta al primer parámetro de la consulta
                ps.setDate(2, Date.valueOf(fechaExp)); // 00379223 Asignar el valor de fecha de expiración al segundo parámetro de la consulta
                ps.setString(3, tipoTarjeta); // 00379223 Asignar el valor de tipo de tarjeta al tercer parámetro de la consulta
                ps.setInt(4, idCliente); // 00379223 Asignar el valor de ID de cliente al cuarto parámetro de la consulta
                ps.setInt(5, facilitador.getValue().getId()); // 00379223 Asignar el valor de ID de facilitador al quinto parámetro de la consulta

                ps.executeUpdate(); // 00379223 Ejecutar la consulta de inserción

                showAlertInformation("Tabla Tarjeta modificada!", "Se agrego una nueva tarjeta"); // 00379223 Mostrar un mensaje de información de éxito

                txtIdCliente.clear(); // 00379223 Limpiar el campo de texto de ID de cliente
                txtNumeroTarjeta.clear(); // 00379223 Limpiar el campo de texto de número de tarjeta
                tgTipoTarjeta.getSelectedToggle().setSelected(false); // 00379223 Deseleccionar el toggle de tipo de tarjeta
                dpFechaExp.setValue(null); // 00379223 Limpiar el campo de fecha de expiración
            } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
                showAlertError("Error", "No se pudo agregar el cliente"); // 00379223 Mostrar un mensaje de alerta de error
            }
        }
    }

    @FXML
    private void realizarCompra() { // 00379223 Definición del método realizarCompra
        lblErrorIdTarjeta.setText(""); // 00379223 Limpiar el texto del label de error de ID de tarjeta
        lblErrorMonto.setText(""); // 00379223 Limpiar el texto del label de error de monto
        lblErrorFechaCompra.setText(""); // 00379223 Limpiar el texto del label de error de fecha de compra
        lblErrorDescripcionCompra.setText(""); // 00379223 Limpiar el texto del label de error de descripción de compra

        boolean flag = false; // 00379223 Inicializar la bandera de error a false
        int idTarjeta = 0; // 00379223 Inicializar el ID de la tarjeta a 0
        float monto = 0; // 00379223 Inicializar el monto a 0
        String descripcion = txtDescripcionCompra.getText(); // 00379223 Obtener el texto del campo de descripción de compra
        LocalDate fechaCompra = dpFechaCompra.getValue(); // 00379223 Obtener el valor del campo de fecha de compra

        try { // 00379223 Bloque try para manejar posibles excepciones
            monto = Float.parseFloat(txtMonto.getText()); // 00379223 Intentar convertir el texto del campo de monto a float
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un monto valido"); // 00379223 Mostrar un mensaje de alerta de error
            lblErrorMonto.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de monto
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (dpFechaCompra.getValue() == null) { // 00379223 Verificar si la fecha de compra no está seleccionada
            lblErrorFechaCompra.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de fecha de compra
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (descripcion.equals("")) { // 00379223 Verificar si la descripción de compra está vacía
            lblErrorDescripcionCompra.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de descripción de compra
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            idTarjeta = Integer.parseInt(txtIdTarjeta.getText()); // 00379223 Intentar convertir el texto del campo ID de tarjeta a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de tarjeta válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!tarjetaExiste(idTarjeta)) { // 00379223 Verificar si la tarjeta no existe
            showAlertError("Error", "El ID de Tarjeta no existe"); // 00379223 Mostrar un mensaje de alerta de error
            lblErrorIdTarjeta.setText("Campo Obligatorio"); // 00379223 Establecer mensaje de error en el label de ID de tarjeta
            flag = true; // 00379223 Establecer la bandera de error a true
        }

        if (!flag) { // 00379223 Si no hay errores
            try { // 00379223 Bloque try para manejar posibles excepciones
                PreparedStatement ps = connection.prepareStatement("INSERT INTO tbTransaccion(fecha, monto, descripcion, id_tarjeta) VALUES (?,?,?,?)"); // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL de inserción
                ps.setDate(1, Date.valueOf(fechaCompra)); // 00379223 Asignar el valor de fecha de compra al primer parámetro de la consulta
                ps.setFloat(2, monto); // 00379223 Asignar el valor de monto al segundo parámetro de la consulta
                ps.setString(3, descripcion); // 00379223 Asignar el valor de descripción al tercer parámetro de la consulta
                ps.setInt(4, idTarjeta); // 00379223 Asignar el valor de ID de tarjeta al cuarto parámetro de la consulta

                ps.executeUpdate(); // 00379223 Ejecutar la consulta de inserción

                showAlertInformation("Tabla Transaccion modificada!", "Se agrego una nueva transaccion"); // 00379223 Mostrar un mensaje de información de éxito

                txtIdTarjeta.clear(); // 00379223 Limpiar el campo de texto de ID de tarjeta
                txtMonto.clear(); // 00379223 Limpiar el campo de texto de monto
                txtDescripcionCompra.clear(); // 00379223 Limpiar el campo de texto de descripción de compra
                dpFechaCompra.setValue(null); // 00379223 Limpiar el campo de fecha de compra
            } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
                showAlertError("Error", "No se pudo realizar la compra"); // 00379223 Mostrar un mensaje de alerta de error
            }
        }
    }


    @FXML
    private void mostrarClientes() {// 00379223 Método para mostrar los clientes en la tabla
        tblCliente.getItems().clear(); // 00379223 Limpia los elementos actuales de la tabla de clientes

        try {
            Statement st = connection.createStatement(); // 00379223 Crea una declaración para ejecutar consultas SQL
            ResultSet rs = st.executeQuery("SELECT * FROM tbCliente"); // 00379223 Ejecuta una consulta para seleccionar todos los clientes de la tabla tbCliente

            while (rs.next()) { // 00379223 Itera sobre cada fila de resultados obtenida
                Cliente cliente = new Cliente( // 00379223 Crea un objeto Cliente con los datos obtenidos de la consulta
                        rs.getInt("id"), // 00379223 Obtiene el ID del cliente de la fila actual
                        rs.getString("nombre"), // 00379223 Obtiene el nombre del cliente de la fila actual
                        rs.getString("direccion"), // 00379223 Obtiene la dirección del cliente de la fila actual
                        rs.getString("telefono") // 00379223 Obtiene el teléfono del cliente de la fila actual
                );
                tblCliente.getItems().add(cliente); // 00379223 Agrega el cliente a la tabla de clientes
            }

        } catch (Exception e) { // 00379223 Captura cualquier excepción que ocurra durante la ejecución del bloque try
            System.out.println(e); // 00379223 Imprime la excepción en la consola (para depuración)
            showAlertError("Error", "No se pudieron obtener los clientes"); // 00379223 Muestra una alerta de error indicando que no se pudieron obtener los clientes
        }
    }

    @FXML
    private void mostrarIDClientes() { // 00379223 Definición del método mostrarIDClientes
        tblCliente.getItems().clear(); // 00379223 Limpiar los elementos de la tabla de clientes
        int idCliente = 0; // 00379223 Inicializar el ID del cliente a 0

        try { // 00379223 Bloque try para manejar posibles excepciones
            idCliente = Integer.parseInt(txtIdCliente2.getText()); // 00379223 Intentar convertir el texto del campo ID de cliente a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de cliente válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!clienteExiste(idCliente)) { // 00379223 Verificar si el cliente no existe
            showAlertError("Error", "El ID de cliente no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si el cliente no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            Statement st = connection.createStatement(); // 00379223 Crear un Statement para ejecutar una consulta SQL
            ResultSet rs = st.executeQuery("SELECT * FROM tbCliente WHERE id = '"+idCliente+"'"); // 00379223 Ejecutar una consulta SQL para obtener el cliente con el ID especificado

            while (rs.next()) { // 00379223 Iterar sobre los resultados de la consulta
                Cliente cliente = new Cliente( // 00379223 Crear un objeto Cliente con los datos obtenidos de la consulta
                        rs.getInt("id"), // 00379223 Obtener el ID del cliente
                        rs.getString("nombre"), // 00379223 Obtener el nombre del cliente
                        rs.getString("direccion"), // 00379223 Obtener la dirección del cliente
                        rs.getString("telefono") // 00379223 Obtener el teléfono del cliente
                );
                tblCliente.getItems().add(cliente); // 00379223 Agregar el cliente a la tabla de clientes
            }

        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            System.out.println(e); // 00379223 Imprimir la excepción en la consola
            showAlertError("Error", "No se pudieron obtener los clientes"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }

    @FXML
    private void mostrarTarjetas() {// 00379223 Método para mostrar las tarjetas en la tabla
        tblTarjeta.getItems().clear(); // 00379223 Limpia los elementos actuales de la tabla de tarjetas

        try {
            Statement st = connection.createStatement(); // 00379223 Crea una declaración para ejecutar consultas SQL
            ResultSet rs = st.executeQuery("SELECT * from tbTarjeta"); // 00379223 Ejecuta una consulta para seleccionar todas las tarjetas de la tabla tbTarjeta

            while (rs.next()) { // 00379223 Itera sobre cada fila de resultados obtenida
                int idFacilitador = rs.getInt("id_facilitador"); // 00379223 Obtiene el ID del facilitador de la tarjeta de la fila actual
                String facilitador = ""; //00379223 Se inicializa el facilitador con ""

                switch (idFacilitador) { // 00379223 Utiliza un switch para determinar el nombre del facilitador según su ID
                    case 1: //00379223 Caso si el id Facilitador de la tabla Tarjeta es 1
                        facilitador = "Visa"; //0379223 Se le asigna a facilitador el valor 'Visa'
                        break; //00379223 Se utiliza el break para cerrar el caso
                    case 2: //00379223 Caso si el id Facilitador de la tabla Tarjeta es 2
                        facilitador = "MasterCard"; //0379223 Se le asigna a facilitador el valor 'MasterCard'
                        break; //00379223 Se utiliza el break para cerrar el caso
                    case 3: //00379223 Caso si el id Facilitador de la tabla Tarjeta es 3
                        facilitador = "American Express"; //0379223 Se le asigna a facilitador el valor 'American Express'
                        break; //00379223 Se utiliza el break para cerrar el caso
                }

                Tarjeta tarjeta = new Tarjeta( // 00379223 Crea un objeto Tarjeta con los datos obtenidos de la consulta
                        rs.getInt("id_tarjeta"), // 00379223 Obtiene el ID de la tarjeta de la fila actual
                        rs.getString("numero_tarjeta"), // 00379223 Obtiene el número de tarjeta de la fila actual
                        rs.getDate("fechaExp").toLocalDate(), // 00379223 Obtiene la fecha de expiración de la tarjeta de la fila actual y la convierte a LocalDate
                        rs.getString("tipo"), // 00379223 Obtiene el tipo de tarjeta de la fila actual
                        rs.getInt("id_cliente"), // 00379223 Obtiene el ID del cliente asociado a la tarjeta de la fila actual
                        facilitador // 00379223 Usa el nombre del facilitador obtenido del switch como facilitador de la tarjeta
                );

                tblTarjeta.getItems().add(tarjeta); // 00379223 Agrega la tarjeta a la tabla de tarjetas
            }

        } catch (Exception e) { // 00379223 Captura cualquier excepción que ocurra durante la ejecución del bloque try
            System.out.println(e); // 00379223 Imprime la excepción en la consola (para depuración)
            showAlertError("Error", "No se pudieron obtener las tarjetas"); // 00379223 Muestra una alerta de error indicando que no se pudieron obtener las tarjetas
        }
    }

    @FXML
    private void mostrarIDTarjetas() { // 00379223 Definición del método mostrarIDTarjetas
        tblTarjeta.getItems().clear(); // 00379223 Limpiar los elementos de la tabla de tarjetas
        int idTarjeta; // 00379223 Declarar la variable idTarjeta

        try { // 00379223 Bloque try para manejar posibles excepciones
            idTarjeta = Integer.parseInt(txtIdTarjeta2.getText()); // 00379223 Intentar convertir el texto del campo ID de tarjeta a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de tarjeta válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!tarjetaExiste(idTarjeta)) { // 00379223 Verificar si la tarjeta no existe
            showAlertError("Error", "El ID de tarjeta no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si la tarjeta no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            Statement st = connection.createStatement(); // 00379223 Crear un Statement para ejecutar una consulta SQL
            ResultSet rs = st.executeQuery("SELECT * from tbTarjeta WHERE id_tarjeta = '"+idTarjeta+"'"); // 00379223 Ejecutar una consulta SQL para obtener la tarjeta con el ID especificado

            while (rs.next()) { // 00379223 Iterar sobre los resultados de la consulta
                int idFacilitador = rs.getInt("id_facilitador"); // 00379223 Obtener el ID del facilitador
                String faciltador = ""; // 00379223 Inicializar la variable facilitador a una cadena vacía

                switch (idFacilitador) { // 00379223 Seleccionar el nombre del facilitador basado en el ID
                    case 1: // 00379223 Caso 1: Visa
                        faciltador = "Visa"; // 00379223 Asignar "Visa" a facilitador
                        break; // 00379223 Romper el switch
                    case 2: // 00379223 Caso 2: MasterCard
                        faciltador = "MasterCard"; // 00379223 Asignar "MasterCard" a facilitador
                        break; // 00379223 Romper el switch
                    case 3: // 00379223 Caso 3: American Express
                        faciltador = "American Express"; // 00379223 Asignar "American Express" a facilitador
                }

                Tarjeta tarjeta = new Tarjeta( // 00379223 Crear un objeto Tarjeta con los datos obtenidos de la consulta
                        rs.getInt("id_tarjeta"), // 00379223 Obtener el ID de la tarjeta
                        rs.getString("numero_tarjeta"), // 00379223 Obtener el número de la tarjeta
                        rs.getDate("fechaExp").toLocalDate(), // 00379223 Obtener la fecha de expiración de la tarjeta y convertirla a LocalDate
                        rs.getString("tipo"), // 00379223 Obtener el tipo de tarjeta
                        rs.getInt("id_cliente"), // 00379223 Obtener el ID del cliente
                        faciltador // 00379223 Asignar el nombre del facilitador
                );

                tblTarjeta.getItems().add(tarjeta); // 00379223 Agregar la tarjeta a la tabla de tarjetas
            }

        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            System.out.println(e); // 00379223 Imprimir la excepción en la consola
            showAlertError("Error", "No se pudieron obtener las tarjetas"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }

    @FXML
    private void mostrarTransacciones() {// 00379223 Método para mostrar las transacciones en la tabla
        tblTransaccion.getItems().clear(); // 00379223 Limpia los elementos actuales de la tabla de transacciones

        try {
            Statement st = connection.createStatement(); // 00379223 Crea una declaración para ejecutar consultas SQL
            ResultSet rs = st.executeQuery("SELECT * from tbTransaccion"); // 00379223 Ejecuta una consulta para seleccionar todas las transacciones de la tabla tbTransaccion

            while (rs.next()) { // 00379223 Itera sobre cada fila de resultados obtenida
                Transaccion transaccion = new Transaccion( // 00379223 Crea un objeto Transaccion con los datos obtenidos de la consulta
                        rs.getInt("id"), // 00379223 Obtiene el ID de la transacción de la fila actual
                        rs.getDate("fecha").toLocalDate(), // 00379223 Obtiene la fecha de la transacción de la fila actual y la convierte a LocalDate
                        rs.getFloat("monto"), // 00379223 Obtiene el monto de la transacción de la fila actual
                        rs.getString("descripcion"), // 00379223 Obtiene la descripción de la transacción de la fila actual
                        rs.getInt("id_tarjeta") // 00379223 Obtiene el ID de la tarjeta asociada a la transacción de la fila actual
                );
                tblTransaccion.getItems().add(transaccion); // 00379223 Agrega la transacción a la tabla de transacciones
            }

        } catch (Exception e) { // 00379223 Captura cualquier excepción que ocurra durante la ejecución del bloque try
            System.out.println(e); // 00379223 Imprime la excepción en la consola (para depuración)
            showAlertError("Error", "No se pudieron obtener las transacciones"); // 00379223 Muestra una alerta de error indicando que no se pudieron obtener las transacciones
        }
    }

    @FXML
    private void mostrarIDTransacciones() { // 00379223 Definición del método mostrarIDTransacciones
        tblTransaccion.getItems().clear(); // 00379223 Limpiar los elementos de la tabla de transacciones
        int idTransaccion = 0; // 00379223 Inicializar el ID de la transacción a 0

        try { // 00379223 Bloque try para manejar posibles excepciones
            idTransaccion = Integer.parseInt(txtIdTransaccion.getText()); // 00379223 Intentar convertir el texto del campo ID de transacción a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de transacción válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!transaccionExiste(idTransaccion)) { // 00379223 Verificar si la transacción no existe
            showAlertError("Error", "El ID de transacción no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si la transacción no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            Statement st = connection.createStatement(); // 00379223 Crear un Statement para ejecutar una consulta SQL
            ResultSet rs = st.executeQuery("SELECT * from tbTransaccion WHERE id = '"+idTransaccion+"'"); // 00379223 Ejecutar una consulta SQL para obtener la transacción con el ID especificado

            while (rs.next()) { // 00379223 Iterar sobre los resultados de la consulta
                Transaccion transaccion = new Transaccion( // 00379223 Crear un objeto Transaccion con los datos obtenidos de la consulta
                        rs.getInt("id"), // 00379223 Obtener el ID de la transacción
                        rs.getDate("fecha").toLocalDate(), // 00379223 Obtener la fecha de la transacción y convertirla a LocalDate
                        rs.getFloat("monto"), // 00379223 Obtener el monto de la transacción
                        rs.getString("descripcion"), // 00379223 Obtener la descripción de la transacción
                        rs.getInt("id_tarjeta") // 00379223 Obtener el ID de la tarjeta asociada a la transacción
                );
                tblTransaccion.getItems().add(transaccion); // 00379223 Agregar la transacción a la tabla de transacciones
            }

        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            System.out.println(e); // 00379223 Imprimir la excepción en la consola
            showAlertError("Error", "No se pudieron obtener las transacciones"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }

    @FXML
    private void eliminarCliente() { // 00379223 Definición del método eliminarCliente
        int idCliente = 0; // 00379223 Inicializar el ID del cliente a 0

        try { // 00379223 Bloque try para manejar posibles excepciones
            idCliente = Integer.parseInt(txtIdCliente2.getText()); // 00379223 Intentar convertir el texto del campo ID de cliente a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de cliente válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!clienteExiste(idCliente)) { // 00379223 Verificar si el cliente no existe
            showAlertError("Error", "El ID de cliente no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si el cliente no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            PreparedStatement ps = connection.prepareStatement("DELETE from tbCliente where id = ?"); // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL de eliminación
            ps.setInt(1, idCliente); // 00379223 Asignar el valor de ID de cliente al primer parámetro de la consulta
            ps.executeUpdate(); // 00379223 Ejecutar la consulta de eliminación

            showAlertInformation("Tabla Cliente Modificada!", "Cliente eliminado"); // 00379223 Mostrar un mensaje de información de éxito
        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            showAlertError("Error", "No se pudo obtener los Clientes"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }

    @FXML
    private void eliminarTarjeta() { // 00379223 Definición del método eliminarTarjeta
        int idTarjeta = 0; // 00379223 Inicializar el ID de la tarjeta a 0

        try { // 00379223 Bloque try para manejar posibles excepciones
            idTarjeta = Integer.parseInt(txtIdTarjeta2.getText()); // 00379223 Intentar convertir el texto del campo ID de tarjeta a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de tarjeta válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!tarjetaExiste(idTarjeta)) { // 00379223 Verificar si la tarjeta no existe
            showAlertError("Error", "El ID de tarjeta no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si la tarjeta no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            PreparedStatement ps = connection.prepareStatement("DELETE from tbTarjeta where id_tarjeta = ?"); // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL de eliminación
            ps.setInt(1, idTarjeta); // 00379223 Asignar el valor de ID de tarjeta al primer parámetro de la consulta
            ps.executeUpdate(); // 00379223 Ejecutar la consulta de eliminación

            showAlertInformation("Tabla Tarjeta Modificada!", "Tarjeta eliminada"); // 00379223 Mostrar un mensaje de información de éxito
        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            showAlertError("Error", "No se pudo obtener las tarjetas"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }

    @FXML
    private void eliminarTransaccion() { // 00379223 Definición del método eliminarTransaccion
        int idTransaccion = 0; // 00379223 Inicializar el ID de la transacción a 0

        try { // 00379223 Bloque try para manejar posibles excepciones
            idTransaccion = Integer.parseInt(txtIdTransaccion.getText()); // 00379223 Intentar convertir el texto del campo ID de transacción a entero
        } catch (NumberFormatException e) { // 00379223 Capturar la excepción si la conversión falla
            showAlertError("Error", "Ingrese un ID de transacción válido"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si ocurre una excepción
        }

        if (!transaccionExiste(idTransaccion)) { // 00379223 Verificar si la transacción no existe
            showAlertError("Error", "El ID de transacción no existe"); // 00379223 Mostrar un mensaje de alerta de error
            return; // 00379223 Salir del método si la transacción no existe
        }

        try { // 00379223 Bloque try para manejar posibles excepciones
            PreparedStatement ps = connection.prepareStatement("DELETE from tbTransaccion where id = ?"); // 00379223 Crear un PreparedStatement para ejecutar una consulta SQL de eliminación
            ps.setInt(1, idTransaccion); // 00379223 Asignar el valor de ID de transacción al primer parámetro de la consulta
            ps.executeUpdate(); // 00379223 Ejecutar la consulta de eliminación

            showAlertInformation("Tabla Transaccion Modificada!", "Transacción eliminada"); // 00379223 Mostrar un mensaje de información de éxito
        } catch (Exception e) { // 00379223 Capturar cualquier excepción que ocurra
            showAlertError("Error", "No se pudo obtener las transacciones"); // 00379223 Mostrar un mensaje de alerta de error
        }
    }
}