package org.example.parcialpoo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;

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
    private ChoiceBox cbMes;
    @FXML
    private ChoiceBox cbAnio;
    @FXML
    private TextField txtFacilitador; //00377723 Espacio donde se escribe el facilitador de tarjetas para generar el reporte D
    @FXML
    private TextArea txtReporte; //00377723 Cuadro de texto donde es escriben los reportes después de generarlos

    //Date
    String fechaActual = new SimpleDateFormat("(dd-MM-yyyy_HH;mm;ss)").format(new Date()); //00377723 Guarda la fecha actual para poner en el nombre del archivo del reporte
    //Archivos
    private String ruta = "src/main/java/org/example/parcialpoo/Reportes/"; //00377723 Ruta donde se guardaran los archivos de reporte
    String reporte = null; //00377723 Se usa para juntar la ruta y el nombre del archivo para mayor simplicidad a la hora de crear y leer el archivo
    //Bases de datos
    String query = null; //00377723 Se usa para guardar la consulta que se desea realizar a la base de datos

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //00377723 Corre al inicio del programa
        deshabilitar(); //00377723 Se llama a la función "deshabilitar" para que no se pueda modificar ningún campo si aún no se ha elegido un tipo de reporte
        ToggleGroup ReporteGroup = new ToggleGroup(); //00377723 Crea el ToggleGroup para no seleccionar más de un RadioButton a la vez
        rbReporteA.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte A en el ToggleGroup
        rbReporteB.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte B en el ToggleGroup
        rbReporteC.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte C en el ToggleGroup
        rbReporteD.setToggleGroup(ReporteGroup); //00377723 Se mete al RadioButton del reporte D en el ToggleGroup
    }

    public void deshabilitar() //00377723 Función para deshabilitar todos los filtros para crear los reportes
    {
        txtIDCliente.clear();
        txtIDCliente.setDisable(true); //00377723 Deshabilita el TextField donde se escribe el ID del cliente
        dpFechaInicial.setDisable(true); //00377723 Deshabilita el DatePicker donde se selecciona la fecha de Inicio del rango
        dpFechaFinal.setDisable(true); //00377723 Deshabilita el DatePicker donde se selecciona la fecha de final del rango
        cbMes.setDisable(true);
        cbAnio.setDisable(true);
        txtFacilitador.clear();
        txtFacilitador.setDisable(true); //00377723 Deshabilita el TextField donde se escribe el Facilitador de tarjetas
    }

    @FXML
    public void prepararReporteA() //00377723 Acción de cuando se selecciona el RadioButton del reporte A, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama a la función primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar las compras realizadas por un cliente en un periodo de tiempo determinado"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente
        dpFechaInicial.setDisable(false); //00377723 Habilita para poder seleccionar la fecha Inicial del rango que se busca
        dpFechaFinal.setDisable(false); //00377723 Habilita para poder seleccionar la fecha final del rango que se busca

    }
    @FXML
    public void prepararReporteB() //00377723 Acción de cuando se selecciona el RadioButton del reporte B, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama a la función primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Imprimir el total de dinero gastado por un cliente en un mes específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente
        cbMes.setDisable(false); //00377723 Habilita para poder seleccionar la fecha inicial
        cbAnio.setDisable(false); //00377723 Habilita para poder seleccionar la fecha final

    }
    @FXML
    public void prepararReporteC() //00377723 Acción de cuando se selecciona el RadioButton del reporte C, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama a la función primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar todas las tarjetas asociadas a un cliente específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtIDCliente.setDisable(false); //00377723 Habilita para poder escribir el ID del cliente

    }
    @FXML
    public void prepararReporteD() //00377723 Acción de cuando se selecciona el RadioButton del reporte D, para preparar la consulta a la base de datos
    {
        deshabilitar(); //00377723 Se llama a la función primero para asegurarse que todas estén deshabilitadas y así solo queden las que se usarán para este reporte
        txtDescripcion.setText("Listar clientes que han realizado compras con un facilitador de tarjeta específico"); //00377723 Escribe la descripción del reporte para entender que es lo que hace
        txtFacilitador.setDisable(false); //00377723 Habilita para poder escribir el Facilitador de tarjeta

    }

    public void escribirArchivo(TextArea txtReporte) //00377723 Función para escribir los archivos con los reportes
    {
        try{ //00377723 Para captar un error a la hora de escribir el archivo
            FileWriter writer = new FileWriter(reporte); //00377723 Inicializa el  FileWritter para escribir el reporte en un archivo .txt
            writer.write(txtReporte.getText()); //00377723 Escribe en el archivo la consulta a la base de datos
            writer.close(); //00377723 Cierra el writter cuando se deja de usar pra no gastar recursos


        }catch (IOException e){ //00377723 En caso da error al crear el archivo
            e.printStackTrace(); //00377723 Imprime el error
        }
    }


    @FXML
    public void generarReporte() //00377723 Acción del botón generar reporte. Evalúa errores y manda a llamar a la función de escribirArchivo
    {
        txtReporte.clear(); //00377723 Limpia el TextArea en caso de haber texto previo
        fechaActual = new SimpleDateFormat("(dd-MM-yyyy_HH;mm;ss)").format(new Date());

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbBancoCentral", "root", "josearbizu");
            Statement st = conn.createStatement();

            //Algún espacio vacio
            Alert alertVacio = new Alert(Alert.AlertType.WARNING); //00377723 Instancia la clase Alert para tirar un mensaje en caso de que falte por rellenar un espacio para la creación del reporte
            alertVacio.setTitle("Reportes Advertencia"); //00377723 le da nombre a la ventana de Warning
            alertVacio.setContentText("Debe rellenar todos los espacios!"); //00377723 Establece el texto de aviso en la ventana de Warning


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
                    reporte = ruta+"ReporteA-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte A

                    query = "SELECT t.id, t.fecha, t.monto, t.descripcion, c.nombre " +
                            "FROM tbTransaccion t " +
                            "INNER JOIN tbTarjeta b ON t.id_tarjeta = b.id_tarjeta "+
                            "INNER JOIN tbCliente c ON b.id_cliente = c.id "+
                            "WHERE c.id = "+ txtIDCliente.getText() + " AND t.fecha BETWEEN '"+ dpFechaInicial.getValue().toString() + "' AND '" + dpFechaFinal.getValue().toString() +"'";
                    ResultSet rs = st.executeQuery(query);
                    while(rs.next()){
                        txtReporte.appendText("ID: "+ rs.getInt("t.id") +  " Fecha: "+rs.getDate("t.fecha") + " Monto: "+ rs.getFloat("t.monto") +" Descripcion: "+rs.getString("t.descripcion") +" Nombre: "+ rs.getString("c.nombre") + "\n");
                    }
                    escribirArchivo(txtReporte); //00377723 Llama a la función escribirArchivo para generar el reporte
                }
            }

            if (rbReporteB.isSelected())//00377723 Evalúa si el RadioButton del reporte B está seleccionado
            {
                if (txtIDCliente.getText().isEmpty() || cbMes.getValue() == null || cbAnio.getValue() == null){ //00377723 Evalúa que no falte ningún espacio por rellenar para generar el reporte
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    reporte = ruta+"ReporteB-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte B

                    escribirArchivo(txtReporte); //00377723 Llama a la función escribirArchivo para generar el reporte
                }
            }
            if (rbReporteC.isSelected()) //00377723 Evalúa si el RadioButton del reporte C está seleccionado
            {
                if (txtIDCliente.getText().isEmpty()){
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    reporte = ruta+"ReporteC-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte C

                    query = "SELECT numero_tarjeta, tipo FROM tbTarjeta WHERE id_cliente = " + txtIDCliente.getText();
                    ResultSet rs = st.executeQuery(query);
                    txtReporte.appendText("ID cliente: "+ txtIDCliente.getText() + "\n");
                    while(rs.next()){
                        txtReporte.appendText(rs.getString("numero_tarjeta") + "\n" + rs.getString("tipo") );
                    }
                    escribirArchivo(txtReporte); //00377723 Llama a la función escribirArchivo para generar el reporte
                }
            }

            if (rbReporteD.isSelected()) //00377723 Evalúa si el RadioButton del reporte D está seleccionado
            {
                if (txtFacilitador.getText().isEmpty()){
                    alertVacio.showAndWait(); //00377723 Muestra la pantalla de Advertencia en caso de que falte algo
                }
                else { //00377723 En caso de no encontrar errores
                    reporte = ruta+"ReporteD-" + fechaActual+".txt"; //00377723 Guarda la ruta y el nombre del archivo para el reporte D

                    escribirArchivo(txtReporte); //00377723 Llama a la función escribirArchivo para generar el reporte
                }
            }
        }catch (Exception e){
            System.out.println(query);
            System.out.println("Fallo al conectar");
        }
    }

}