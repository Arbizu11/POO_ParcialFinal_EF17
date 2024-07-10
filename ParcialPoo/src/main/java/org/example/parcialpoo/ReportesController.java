package org.example.parcialpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.parcialpoo.Clases.DatabaseConnection;
import org.example.parcialpoo.Clases.Tarjeta;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class ReportesController implements Initializable
{
    @FXML
    private RadioButton rbReporteA; //00377723 RadioButton para elegir el reporte A
    @FXML
    private RadioButton rbReporteB; //00377723 RadioButton para elegir el reporte B
    @FXML
    private RadioButton rbReporteC; //00377723 RadioButton para elegir el reporte C
    @FXML
    private RadioButton rbReporteD; //00377723 RadioButton para elegir el reporte D
    @FXML
    private TextField txtDescripcion; //00377723 Cuadro de texto donde se muestra la descripción del Reporte
    @FXML
    private TextField txtIDCliente; //00377723 Espacio donde se escribe el ID del cliente del que se desea hacer una consulta. Se usa para reporte A, B Y C
    @FXML
    private DatePicker dpFechaInicial; //00377723 Se usa para seleccionar la fecha inicial del rango de fechas para generar el reporte B
    @FXML
    private DatePicker dpFechaFinal; //00377723 Se usa para seleccionar la fecha final del rango de fechas para generar el reporte B
    @FXML
    private ComboBox<String> cbMes; //00377723 ComboBox para elegir el mes que se quiere
    private final String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; //00377723 Arreglo de meses para añadir en el ComboBox
    @FXML
    private ComboBox<Integer> cbAnio; //00377723 ComboBox para elegir el año que se quiere
    @FXML
    private TextField txtFacilitador; //00377723 Espacio donde se escribe el facilitador de tarjetas para generar el reporte D
    @FXML
    private TextArea txtReporte; //00377723 Cuadro de texto donde es escriben los reportes después de generarlos

    //Date
    String fechaActual = null; //00377723 Guarda la fecha actual para poner en el nombre del archivo del reporte
    //Archivos
    private final String ruta = "src/main/java/org/example/parcialpoo/Reportes/"; //00377723 Ruta donde se guardaran los archivos de reporte
    String reporte = null; //00377723 Se usa para juntar la ruta y el nombre del archivo para mayor simplicidad a la hora de crear y leer el archivo
    //Bases de datos
    Connection conn;
    String query = null; //00377723 Se usa para guardar la consulta que se desea realizar a la base de datos

    private Stage stage; //00377723 Stage para generar la ventana
    private Scene scene; //00377723 Escena que carga dentro de la ventana de Stage
    private Parent root; //00377723 Almacena todos los Children de la escena

    @FXML
    public void cambiarEscenaBanco(ActionEvent event) throws IOException //00377723 Cambia a la escena principal del programa
    {
        root = FXMLLoader.load(getClass().getResource("BancoCentral.fxml")); //00377723 Carga el archivo fxml de la escena principal
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //00377723 prepara la ventana
        scene = new Scene(root); //00377723 guarda la escena con el fxml de BancoCentral
        stage.setScene(scene); //00377723  agrega la escena a la ventana antes creada
        stage.show(); //00377723 Muestra la escena principal
    }

    private void connectDatabase() { // 00379223 Método para conectar a la base de datos
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 00379223 Carga el controlador JDBC para MySQL
            conn = DatabaseConnection.getConnection(); // 00379223 Obtiene la conexión a la base de datos utilizando la clase DatabaseConnection
        } catch (ClassNotFoundException |
                 SQLException e) { // 00379223 Captura excepciones ClassNotFoundException y SQLException
            e.printStackTrace(); // 00379223 Imprime la traza de la excepción
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //00377723 Corre al inicio del programa
        connectDatabase(); // 00379223 Establece la conexión a la base de datos
        deshabilitar(); //00377723 Se llama al método "deshabilitar" para que no se pueda modificar ningún campo si aún no se ha elegido un tipo de reporte
        ToggleGroup ReporteGroup = new ToggleGroup(); //00377723 Crea el ToggleGroup para no seleccionar más de un RadioButton a la vez
        rbReporteA.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte A en el ToggleGroup
        rbReporteB.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte B en el ToggleGroup
        rbReporteC.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte C en el ToggleGroup
        rbReporteD.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte D en el ToggleGroup
        cbMes.getItems().addAll(meses); //00377723 Añade el arreglo de meses al ComboBox
        for(int actualyear = Calendar.getInstance().get(Calendar.YEAR); actualyear>= 1970; actualyear--){ //00377723 Itera desde el año actual hasta 1970
            cbAnio.getItems().addAll(actualyear); //00377723 Añade los años al ComboBox
        }
    }

    public void deshabilitar() //00377723 Método para deshabilitar todos los filtros para crear los reportes
    {
        txtIDCliente.setDisable(true); //00377723 Deshabilita el TextField donde se escribe el ID del cliente
        dpFechaInicial.setDisable(true); //00377723 Deshabilita el DatePicker donde se selecciona la fecha de Inicio del rango
        dpFechaFinal.setDisable(true); //00377723 Deshabilita el DatePicker donde se selecciona la fecha de final del rango
        cbMes.setDisable(true); //00377723 Deshabilita el ComboBox donde se selecciona el mes
        cbAnio.setDisable(true); //00377723 Deshabilita el ComboBox donde se selecciona el año
        txtFacilitador.setDisable(true); //00377723 Deshabilita el TextField donde se escribe el Facilitador de tarjetas
    }

    @FXML
    public void prepararReporteA() //00377723 Acción de cuando se selecciona el RadioButton del reporte A, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama al método primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar las compras realizadas por un cliente en un periodo de tiempo determinado"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente
        dpFechaInicial.setDisable(false); //00377723 Habilita para poder seleccionar la fecha Inicial del rango que se busca
        dpFechaFinal.setDisable(false); //00377723 Habilita para poder seleccionar la fecha final del rango que se busca

    }
    @FXML
    public void prepararReporteB() //00377723 Acción de cuando se selecciona el RadioButton del reporte B, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama al método primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Imprimir el total de dinero gastado por un cliente en un mes específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente
        cbMes.setDisable(false); //00377723 Habilita para poder seleccionar la fecha inicial
        cbAnio.setDisable(false); //00377723 Habilita para poder seleccionar la fecha final

    }
    @FXML
    public void prepararReporteC() //00377723 Acción de cuando se selecciona el RadioButton del reporte C, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama al método primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar todas las tarjetas asociadas a un cliente específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente

    }
    @FXML
    public void prepararReporteD() //00377723 Acción de cuando se selecciona el RadioButton del reporte D, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama al método primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar clientes que han realizado compras con un facilitador de tarjeta específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtFacilitador.setDisable(false); //00377723 Habilita para poder escribir el Facilitador de tarjeta

    }

    public void escribirArchivo(TextArea txtReporte) //00377723 Método para escribir los archivos con los reportes
    {
        try{ //00377723 Para captar un error a la hora de escribir el archivo
            FileWriter writer = new FileWriter(reporte); //00377723 Inicializa el  FileWritter para escribir el reporte en un archivo .txt
            writer.write(txtReporte.getText()); //00377723 Escribe en el archivo la consulta a la base de datos
            writer.close(); //00377723 Cierra el writter cuando se deja de usar pra no gastar recursos


        }catch (IOException e){ //00377723 En caso da error al crear el archivo
            e.printStackTrace(); //00377723 Imprime el error
        }
    }

    public void generarReporteA() { //00377723 Metodo que genera el reporte A mediante una consulta sql
        try { //00377723 Try para evaluar que no haya un error
            //Connection conn = DriverManager.getConnection(url, user, password); //00377723 Conecta a la base de datos
            Statement st = conn.createStatement(); //00377723 Crea un Statement para ejecutar la consulta

            reporte = ruta + "ReporteA-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte A

            query = "SELECT t.id, t.fecha, t.monto, t.descripcion, c.nombre " +
                    "FROM tbTransaccion t " +
                    "INNER JOIN tbTarjeta b ON t.id_tarjeta = b.id_tarjeta " +
                    "INNER JOIN tbCliente c ON b.id_cliente = c.id " +
                    "WHERE c.id = " + txtIDCliente.getText() + " AND t.fecha BETWEEN '" + dpFechaInicial.getValue().toString() + "' AND '" + dpFechaFinal.getValue().toString() + "'"; //00377723 guarda la consulta en un string
            ResultSet rs = st.executeQuery(query); //00377723 Ejecuta la consulta

            // Formato para Reporte A 00064122
            txtReporte.appendText("-------Reporte A-------------\n"); //00064122 Agrega encabezado del reporte
            txtReporte.appendText("Cliente: " + txtIDCliente.getText() + "\n\n"); //00064122 Agrega el ID del cliente
            txtReporte.appendText("Compras realizadas:\n"); //00064122 Agrega el encabezado de compras realizadas

            while (rs.next()) { //00064122 Itera sobre los resultados de la consulta
                txtReporte.appendText("Descripcion: " + rs.getString("t.descripcion") + "\t Monto: " + rs.getFloat("t.monto") + "\t Fecha: " + rs.getDate("t.fecha") + "\n"); //00064122 Formatea y agrega cada transaccion
            } //00064122 Fin de la iteracion
            escribirArchivo(txtReporte); // Fin de formato para Reporte A 00064122 Llama al metodo escribirArchivo para guardar el reporte

        } catch (Exception e) { //00377723 En caso de error
            System.out.println("Fallo al generar reporteA"); //00377723 Imprime mensaje de error
        }
    }

    public void generarReporteB() { //00377723 Metodo que genera el reporte B mediante una consulta sql
        try {
            //Connection conn = DriverManager.getConnection(url, user, password); //00377723 Conecta a la base de datos
            Statement st = conn.createStatement(); //00377723 Crea un Statement para ejecutar la consulta
            reporte = ruta + "ReporteB-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte B

            query = "SELECT SUM(t.monto) AS totalGastado, c.nombre " +
                    "FROM tbTransaccion t " +
                    "INNER JOIN tbTarjeta b ON t.id_tarjeta = b.id_tarjeta " +
                    "INNER JOIN tbCliente c ON b.id_cliente = c.id " +
                    "WHERE c.id = " + txtIDCliente.getText() + " AND MONTH(t.fecha) = " + (cbMes.getSelectionModel().getSelectedIndex() + 1) + " AND YEAR(t.fecha) = " + cbAnio.getValue() + " GROUP BY c.nombre"; //00377723 guarda la consulta en un string
            ResultSet rs = st.executeQuery(query); //00377723 Ejecuta la consulta

            // Formato para Reporte B 00064122
            if (rs.next()) { //00064122 Verifica si hay resultados
                txtReporte.appendText("----------Reporte B-------------\n"); //00064122 Agrega encabezado del reporte
                txtReporte.appendText("Cliente: " + rs.getString("c.nombre") + "\n"); //00064122 Agrega el nombre del cliente
                txtReporte.appendText("Fecha: " + cbMes.getValue() + " " + cbAnio.getValue() + "\n"); //00064122 Agrega la fecha del reporte
                txtReporte.appendText("Total gastado: " + rs.getFloat("totalGastado") + "\n"); //00064122 Agrega el total gastado
            } //00064122 Fin de la condicion

            escribirArchivo(txtReporte); // Fin de formato para Reporte B 00064122 Llama al metodo escribirArchivo para guardar el reporte

        } catch (Exception e) { //00377723 En caso de error
            System.out.println("Fallo al generar reporte B"); //00377723 Imprime mensaje de error
        }
    }

    public void generarReporteC() { //00377723 Metodo que genera el reporte C mediante una consulta sql
        try {
            //Connection conn = DriverManager.getConnection(url, user, password); //00377723 Conecta a la base de datos
            Statement st = conn.createStatement(); //00377723 Crea un Statement para ejecutar la consulta

            reporte = ruta + "ReporteC-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte C

            // Obtener el nombre del cliente 00064122
            String clienteNombreQuery = "SELECT nombre FROM tbCliente WHERE id = " + txtIDCliente.getText(); //00064122 Consulta para obtener el nombre del cliente
            ResultSet clienteRs = st.executeQuery(clienteNombreQuery); //00064122 Ejecuta la consulta
            clienteRs.next(); //00064122 Pasa al siguiente resultado
            String clienteNombre = clienteRs.getString("nombre"); //00064122 Obtiene el nombre del cliente

            query = "SELECT numero_tarjeta, tipo FROM tbTarjeta WHERE id_cliente = " + txtIDCliente.getText(); //00064122 Consulta para obtener las tarjetas de un cliente especifico
            ResultSet rs = st.executeQuery(query); //00064122 Ejecuta la consulta
            List<Tarjeta> cards = new ArrayList<>(); // 00064122 Crea una lista para almacenar las tarjetas
            while (rs.next()) { // 00064122 Itera sobre los resultados de la consulta
                Tarjeta card = new Tarjeta(); // 00064122 Crea una nueva instancia de Card
                card.setNumero_tarjeta(rs.getString("numero_tarjeta")); // 00064122 Establece el numero de la tarjeta
                card.setTipo(rs.getString("tipo")); // 00064122 Establece el tipo de tarjeta
                cards.add(card); // 00064122 Anade la tarjeta a la lista
            } //00064122 Fin de la iteracion

            // Formato para Reporte C 00064122
            txtReporte.appendText("-------Reporte C-------------\n"); //00064122 Agrega encabezado del reporte
            txtReporte.appendText("Cliente: " + clienteNombre + "\n\n"); //00064122 Agrega el nombre del cliente
            txtReporte.appendText(formatCards(cards)); //00064122 Formatea y agrega las tarjetas al reporte

            escribirArchivo(txtReporte); // 00064122 Llama al metodo escribirArchivo para guardar el reporte

        } catch (Exception e) { //00377723 En caso de error
            System.out.println("Fallo al generar reporte C"); //00377723 Imprime mensaje de error
        }
    }

    // Metodo para formatear las tarjetas
    public String formatCards(List<Tarjeta> cards) {
        StringBuilder report = new StringBuilder(); // 00064122 Crea un StringBuilder para crear el contenido del reporte
        report.append("Tarjetas de credito:\n"); // 00064122 Anade un encabezado para las tarjetas de crédito
        boolean hasCreditCards = false; // 00064122 Verifica si hay tarjetas de crédito
        boolean hasDebitCards = false; // 00064122 Verifica si hay tarjetas de débito

        for (Tarjeta card : cards) { //00064122 Itera sobre la lista de tarjetas
            // 00064122 Censura el número de la tarjeta: toma los ultimos 4 digitos y los precede con "XXXX XXXX XXXX "
            String maskedNumber = "XXXX XXXX XXXX " + card.getNumero_tarjeta().substring(card.getNumero_tarjeta().length() - 4);
            if (card.getTipo().equalsIgnoreCase("Credito")) { //00064122 Si la tarjeta es de credito
                report.append(maskedNumber).append("\n");  // 00064122 Anade el numero de tarjeta censurado al reporte
                hasCreditCards = true; // 00064122 Indica que hay tarjetas de credito
            }
        }

        if (!hasCreditCards) { // 00064122 Si no hay tarjetas de credito
            report.append("N/A\n"); // 00064122 Anade "N/A" al reporte indicando que no hay tarjetas de credito
        }

        report.append("Tarjetas de Debito:\n"); // 00064122 Anade un encabezado para las tarjetas de debito
        for (Tarjeta card : cards) { //00064122 Itera sobre la lista de tarjetas
            // 00064122 Censura el número de la tarjeta: toma los ultimos 4 digitos y los precede con "XXXX XXXX XXXX "
            String maskedNumber = "XXXX XXXX XXXX " + card.getNumero_tarjeta().substring(card.getNumero_tarjeta().length() - 4);
            if (card.getTipo().equalsIgnoreCase("Debito")) { //00064122 Si la tarjeta es de debito
                report.append(maskedNumber).append("\n"); // 00064122 Anade el numero de tarjeta censurado al reporte
                hasDebitCards = true; //00064122 Indica que hay tarjetas de débito
            }
        }

        if (!hasDebitCards) { // 00064122 Si no hay tarjetas de debito
            report.append("N/A\n"); // 00064122 Añade "N/A" al reporte indicando que no hay tarjetas de debito
        }

        return report.toString(); //00064122 Devuelve la información del reporte como String
    }

    public void generarReporteD() { //00377723 Método que genera el reporte D mediante una consulta sql
        try {
            //Connection conn = DriverManager.getConnection(url, user, password); //00377723 Conecta a la base de datos
            Statement st = conn.createStatement(); //00377723 Crea un Statement para ejecutar la consulta
            reporte = ruta + "ReporteD-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte D

            query = "SELECT c.nombre, COUNT(t.id) AS cantidadCompras, SUM(t.monto) AS totalGastado " +
                    "FROM tbTransaccion t " +
                    "INNER JOIN tbTarjeta b ON t.id_tarjeta = b.id_tarjeta " +
                    "INNER JOIN tbCliente c ON b.id_cliente = c.id " +
                    "WHERE b.facilitador = '" + txtFacilitador.getText() + "' " +
                    "GROUP BY c.nombre"; //00377723 guarda la consulta en un string
            ResultSet rs = st.executeQuery(query); //00377723 Ejecuta la consulta

            // Formato para Reporte D 00064122
            txtReporte.appendText("----------Reporte D-----------------\n"); //00064122 Agrega encabezado del reporte
            txtReporte.appendText("Facilitador: " + txtFacilitador.getText() + "\n\n"); //00064122 Agrega el facilitador de la tarjeta

            while (rs.next()) { //00064122 Itera sobre los resultados de la consulta
                txtReporte.appendText("Cliente: " + rs.getString("c.nombre") + "\tCantidad de compras: " + rs.getInt("cantidadCompras") + "\tTotal gastado: " + rs.getFloat("totalGastado") + "\n"); //00064122 Formatea y agrega cada transaccion
            } //00064122 Fin de la iteracion

            escribirArchivo(txtReporte); // Fin de formato para Reporte D 00064122 Llama al metodo escribirArchivo para guardar el reporte

        } catch (Exception e) { //00377723 En caso de error
            System.out.println("Fallo al generar reporte D"); //00377723 Imprime mensaje de error
        }
    }


    @FXML
    public void generarReporte() //00377723 Acción del botón generar reporte. Evalúa errores y manda a llamar al método de escribirArchivo
    {
        txtReporte.clear(); //00377723 Limpia el TextArea en caso de haber texto previo
        fechaActual = new SimpleDateFormat("(dd-MM-yyyy_HH;mm;ss)").format(new Date()); //00377723 Añade la fecha y hora actual al atributo antes creado

        //Algún espacio vacio
        Alert alertVacio = new Alert(Alert.AlertType.WARNING); //00377723 Instancia la clase Alert para tirar un mensaje en caso de que falte por rellenar un espacio para la creación del reporte
        alertVacio.setTitle("Reportes Advertencia"); //00377723 le da nombre a la ventana de Warning
        alertVacio.setContentText("Debe rellenar todos los espacios!"); //00377723 Establece el texto de aviso en la ventana de Warning


        //ReporteA
        if(rbReporteA.isSelected()) //00377723 Evalúa si el RadioButton del reporte A está seleccionado
             {
                 if(txtIDCliente.getText().isEmpty() || dpFechaInicial.getValue() == null || dpFechaFinal.getValue() == null) //00377723 Evalúa que no falte ningún espacio por rellenar para generar el reporte
                {
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                 else if (dpFechaFinal.getValue().isBefore(dpFechaInicial.getValue())){ //00377723 Evalúa si la fecha final seleccionada es menor a la fecha inicial
                    Alert alertFecha = new Alert(Alert.AlertType.ERROR); //00377723 Instancia la clase Alert para tirar un mensaje en caso de que la fecha final sea antes que la fecha inicial
                    alertFecha.setTitle("Reportes Error");//00377723 Le da nombre a la ventana de Error
                    alertFecha.setContentText("La fecha final no puede ser antes que la fecha inicial!"); //00377723 Establece el texto de aviso en la ventana de Error
                    alertFecha.showAndWait(); //00377723 Muestra la pantalla de Error en caso de que la fecha final seleccionada sea antes que le fecha inicial
                }
                else{ //00377723 En caso de no encontrar errores
                    generarReporteA(); //00377723 Llama al método generarReporteA para realizar la consulta y escribir el reporte
                }
            }

        //ReporteB
        if (rbReporteB.isSelected())//00377723 Evalúa si el RadioButton del reporte B está seleccionado
            {
                if (txtIDCliente.getText().isEmpty() || cbMes.getValue() == null || cbAnio.getValue() == null){ //00377723 Evalúa que no falte ningún espacio por rellenar para generar el reporte
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    generarReporteB(); //00377723 Llama al método generarReporteB para realizar la consulta y escribir el reporte
                }
            }

        //ReporteC
        if (rbReporteC.isSelected()) //00377723 Evalúa si el RadioButton del reporte C está seleccionado
            {
                if (txtIDCliente.getText().isEmpty()){
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    generarReporteC(); //00377723 Llama al método generarReporteC para realizar la consulta y escribir el reporte

                }
            }

        //ReporteD
        if (rbReporteD.isSelected()) //00377723 Evalúa si el RadioButton del reporte D está seleccionado
            {
                if (txtFacilitador.getText().isEmpty()){
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    generarReporteD(); //00377723 Llama al método generarReporteD para realizar la consulta y escribir el reporte
                }
            }

    }

}