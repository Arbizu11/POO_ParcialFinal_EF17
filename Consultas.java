
import java.math.BigDecimal; //00064122 Importa la clase BigDecimal para manejar números grandes
import java.sql.*; //00064122 Importa todas las clases de java.sql para manejar la base de datos
import java.util.ArrayList; //00064122 Importa la clase ArrayList para manejar las listas

public class Consultas {

    // Reporte A: Listar las compras realizadas por un cliente en un periodo de tiempo determinado
    public List<Transaction> getTransactionsByCustomerAndDateRange(int customerId, Date startDate, Date endDate) {
        //00064122 Consulta SQL para obtener transacciones de un cliente en un rango de fechas
        String query = "SELECT * FROM Transactions WHERE tarjeta_id IN (SELECT id FROM Cards WHERE cliente_id = ?) AND fecha_compra BETWEEN ? AND ?";
        List<Transaction> transactions = new ArrayList<>(); //00064122 Lista para almacenar las transacciones recuperadas

        try (Connection conn = DatabaseConnection.getConnection(); //00064122 Establece conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) { //00064122 Preparamos la consulta SQL
            stmt.setInt(1, customerId); //00064122 Establecemos el ID del cliente en la consulta
            stmt.setDate(2, startDate); //00064122 Establecemos la fecha de inicio en la consulta
            stmt.setDate(3, endDate); //00064122 Establecemos la fecha de fin en la consulta

            ResultSet rs = stmt.executeQuery(); //00064122 Ejecuta la consulta
            while (rs.next()) { //00064122 Itera sobre los resultados de la consulta
                Transaction transaction = new Transaction(); //00064122 Crea una nueva instancia de Transaction
                transaction.setId(rs.getInt("id")); //00064122 Establece el ID de la transacción
                transaction.setFechaCompra(rs.getDate("fecha_compra")); //00064122 Establece la fecha de la transacción
                transaction.setMontoTotal(rs.getBigDecimal("monto_total")); //00064122 Establece el monto total de la transacción
                transaction.setDescripcion(rs.getString("descripcion")); //00064122 Establece la descripción de la transacción
                transaction.setTarjetaId(rs.getInt("tarjeta_id")); //00064122 Establece el ID de la tarjeta utilizada
                transactions.add(transaction); //00064122 Añadimos la transacción a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); //00064122 Maneja cualquier excepción SQL
        }

        return transactions; //00064122 Devuelve la lista de transacciones
    }

    // Reporte B: Imprimir el total de dinero gastado por un cliente en un mes específico
    public BigDecimal getTotalSpentByCustomerInMonth(int customerId, int month, int year) {
        //00064122 Consulta SQL para obtener el total gastado por un cliente en un mes y año específicos
        String query = "SELECT SUM(monto_total) FROM Transactions WHERE tarjeta_id IN (SELECT id FROM Cards WHERE cliente_id = ?) AND MONTH(fecha_compra) = ? AND YEAR(fecha_compra) = ?";
        BigDecimal totalSpent = BigDecimal.ZERO; //00064122 Inicializa el total gastado a cero

        try (Connection conn = DatabaseConnection.getConnection(); //00064122 Establece conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) { //00064122 Prepara la consulta SQL
            stmt.setInt(1, customerId); //00064122 Establece el ID del cliente en la consulta
            stmt.setInt(2, month); //00064122 Establece el mes en la consulta
            stmt.setInt(3, year); //00064122 Establece el año en la consulta

            ResultSet rs = stmt.executeQuery(); //00064122 Ejecuta la consulta
            if (rs.next()) { // Si hay resultados
                totalSpent = rs.getBigDecimal(1); //00064122 Establece el total gastado a partir del resultado
            }
        } catch (SQLException e) {
            e.printStackTrace(); //00064122 Maneja cualquier excepción SQL
        }

        return totalSpent; //00064122 Devolvemos el total gastado
    }

    // Reporte C: Listar todas las tarjetas asociadas a un cliente específico
    public List<Card> getCardsByCustomerId(int customerId) {
        //00064122 Consulta SQL para obtener todas las tarjetas de un cliente específico
        String query = "SELECT * FROM Cards WHERE cliente_id = ?";
        List<Card> cards = new ArrayList<>(); //00064122 Lista para almacenar las tarjetas recuperadas

        try (Connection conn = DatabaseConnection.getConnection(); //00064122 Establece conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) { //00064122 Prepara la consulta SQL
            stmt.setInt(1, customerId); //00064122 Establece el ID del cliente en la consulta

            ResultSet rs = stmt.executeQuery(); //00064122 Ejecutar la consulta
            while (rs.next()) { //00064122 Itera sobre los resultados de la consulta
                Card card = new Card(); //00064122 Crea una nueva instancia de Card
                card.setId(rs.getInt("id")); //00064122 Establece el ID de la tarjeta
                card.setNumeroTarjeta(rs.getString("numero_tarjeta")); //00064122 Establece el número de la tarjeta
                card.setFechaExpiracion(rs.getDate("fecha_expiracion")); //00064122 Establece la fecha de expiración de la tarjeta
                card.setTipoTarjeta(rs.getString("tipo_tarjeta")); //00064122 Establece el tipo de tarjeta
                card.setFacilitadorId(rs.getInt("facilitador_id")); //00064122 Establece el ID del facilitador de la tarjeta
                card.setClienteId(rs.getInt("cliente_id")); //00064122 Establece el ID del cliente asociado
                cards.add(card); //00064122 Añadir la tarjeta a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); //00064122 Usa cualquier excepción SQL
        }

        return cards; //00064122 Devuelve la lista de tarjetas
    }

    // Reporte D: Clientes que han realizado compras con un facilitador de tarjeta específico
    public List<Customer> getCustomersByCardFacilitator(String facilitator) {
        //00064122 Consulta SQL para obtener todos los clientes que han realizado compras con un facilitador específico
        String query = "SELECT DISTINCT c.* FROM Customers c " +
                "JOIN Cards ca ON c.id = ca.cliente_id " +
                "JOIN Facilitador f ON ca.facilitador_id = f.id " +
                "JOIN Transactions t ON ca.id = t.tarjeta_id " +
                "WHERE f.nombre = ?";
        List<Customer> customers = new ArrayList<>(); //00064122 Lista para almacenar los clientes recuperados

        try (Connection conn = DatabaseConnection.getConnection(); //00064122 Establece conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(query)) { //00064122 Prepara la consulta SQL
            stmt.setString(1, facilitator); //00064122 Establece el facilitador en la consulta

            ResultSet rs = stmt.executeQuery(); //00064122 Ejecuta la consulta
            while (rs.next()) { //00064122 Itera sobre los resultados de la consulta
                Customer customer = new Customer(); //00064122 Crea una nueva instancia de Customer
                customer.setId(rs.getInt("id")); //00064122 Establece el ID del cliente
                customer.setNombreCompleto(rs.getString("nombre_completo")); //00064122 Establece el nombre completo del cliente
                customer.setDireccion(rs.getString("direccion")); //00064122 Establece la dirección del cliente
                customer.setTelefono(rs.getString("telefono")); //00064122 Establece el teléfono del cliente
                customers.add(customer); //00064122 Añade el cliente a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); //00064122 Usa cualquier excepción SQL
        }

        return customers; //00064122 Devuelve la lista de clientes
    }
}
