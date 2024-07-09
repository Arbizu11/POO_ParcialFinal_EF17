package org.example.parcialpoo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class HelloController implements Initializable
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
    String url = "jdbc:mysql://localhost:3306/dbBancoCentral"; //00377723 url del driver para conectar a la base de datos
    String user = "root"; //00377723 usuario que se utiliza para conectarse a la base de datos
    String password = "josearbizu"; //00377723 contraseña del usuario
    String query = null; //00377723 Se usa para guardar la consulta que se desea realizar a la base de datos

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //00377723 Corre al inicio del programa
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

    public void generarReporteA() //00377723 Método que genera el reporte A mediante una consulta sql
    {
        try { //00377723 Try para evaluar que no haya un error
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();

            reporte = ruta + "ReporteA-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte A

            query = "SELECT t.id, t.fecha, t.monto, t.descripcion, c.nombre " +
                    "FROM tbTransaccion t " +
                    "INNER JOIN tbTarjeta b ON t.id_tarjeta = b.id_tarjeta " +
                    "INNER JOIN tbCliente c ON b.id_cliente = c.id " +
                    "WHERE c.id = " + txtIDCliente.getText() + " AND t.fecha BETWEEN '" + dpFechaInicial.getValue().toString() + "' AND '" + dpFechaFinal.getValue().toString() + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                txtReporte.appendText("ID: " + rs.getInt("t.id") + " Fecha: " + rs.getDate("t.fecha") + " Monto: " + rs.getFloat("t.monto") + " Descripcion: " + rs.getString("t.descripcion") + " Nombre: " + rs.getString("c.nombre") + "\n");
            }
            escribirArchivo(txtReporte); //00377723 Llama al Método escribirArchivo para generar el reporte
            conn.close(); //00377723 Cierra la conexión a la base de datos
        }catch (Exception e){
            System.out.println("Fallo al generar reporteA");        }
    }

    public void generarReporteB()
    {
        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            reporte = ruta+"ReporteB-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte B

             query = "";




            escribirArchivo(txtReporte); // 00064122 Guarda el reporte en un archivo
            conn.close(); //00377723 Cierra la conexión a la base de datos

        }catch (Exception e) {
            System.out.println("Fallo al generar reporte B");
        }
    }

    public void generarReporteC()
    {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();

            reporte = ruta + "ReporteC-" + fechaActual + ".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte C

            query = "SELECT numero_tarjeta, tipo FROM tbTarjeta WHERE id_cliente = " + txtIDCliente.getText(); // Consulta para obtener las tarjetas de un cliente específico
            ResultSet rs = st.executeQuery(query);
            List<Card> cards = new ArrayList<>(); // 00064122 Lista que almacena las tarjetas
            while (rs.next()) { // 00064122 Itera sobre los resultados de la consulta
                Card card = new Card(); // 00064122 Crea una nueva instancia de Card
                card.setNumero_tarjeta(rs.getString("numero_tarjeta")); // 00064122 Establece el número de la tarjeta
                card.setTipo(rs.getString("tipo")); // 00064122 Establece el tipo de tarjeta
                cards.add(card); // 00064122 Añade la tarjeta a la lista
            }

            txtReporte.setText(formatCards(cards)); // 00064122 Formatea las tarjetas utilizando el método formatCards y establecemos el contenido formateado en el TextArea
            escribirArchivo(txtReporte); // 00064122 Guarda el reporte en un archivo
            conn.close(); //00377723 Cierra la conexión a la base de datos

        }catch (Exception e){
            System.out.println("Fallo al generar reporteC");
        }

    }

    // Método para formatear las tarjetas
    public String formatCards(List<Card> cards) {
        StringBuilder report = new StringBuilder(); // 00064122 StringBuilder para crear el contenido del reporte
        report.append("Tarjetas de crédito:\n"); // 00064122 Añade un encabezado para las tarjetas de crédito
        boolean hasCreditCards = false; // 00064122 Verifica si hay tarjetas de crédito
        boolean hasDebitCards = false; // 00064122 Verifica si hay tarjetas de débito

        for (Card card : cards) { //00064122 Itera sobre la lista de tarjetas
            // 00064122 censura el número de la tarjeta: agarra los últimos 4 dígitos y los cabía por "XXXX XXXX XXXX "
            String maskedNumber = "XXXX XXXX XXXX " + card.getNumero_tarjeta().substring(card.getNumero_tarjeta().length() - 4);

            if (card.getTipo().equalsIgnoreCase("Credito")) { //00064122 Si la tarjeta es de crédito
                report.append(maskedNumber).append("\n");  // 00064122 añadimos el número de tarjeta censurando al reporte
                hasCreditCards = true;// 00064122 indica que hay tarjetas de crédito
            }
        }

        if (!hasCreditCards) { // 00064122 Si no hay tarjetas de crédito
            report.append("N/A\n"); // 00064122 Añadimos "N/A" al reporte indicando que no hay tarjetas de crédito
        }

        report.append("Tarjetas de Débito:\n"); // 00064122 encábezado para las tarjetas de débito
        for (Card card : cards) {// 00064122 itera sobre la lista de tarjetas
            // 00064122 censura  el número de la tarjeta: agarra los últimos 4 dígitos y los ca,bia por "XXXX XXXX XXXX "
            String maskedNumber = "XXXX XXXX XXXX " + card.getNumero_tarjeta().substring(card.getNumero_tarjeta().length() - 4);
            if (card.getTipo().equalsIgnoreCase("Debito")) { // 00064122 si la tarjeta es de debito
                report.append(maskedNumber).append("\n"); // 00064122 añadimos el número de tarjeta censurando al reporte
                hasDebitCards = true; // 00064122 indica que hay tarjetas de débito
            }
        }

        if (!hasDebitCards) { // 00064122 Si no hay tarjetas de debito
            report.append("N/A\n"); // 00064122 Añadimos "N/A" al reporte indicando que no hay tarjetas de debito
        }

        return report.toString(); //00064122 Devuelve la info del reporte como String
    }

    public void generarReporteD()
    {
        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            reporte = ruta+"ReporteD-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte B

            query = "";




            escribirArchivo(txtReporte); // 00064122 Guarda el reporte en un archivo
            conn.close(); //00377723 Cierra la conexión a la base de datos

        }catch (Exception e) {
            System.out.println("Fallo al generar reporte B");
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
                    generarReporteD(); //00377723 Llama al método generarReporteC para realizar la consulta y escribir el reporte
                }
            }

    }

}