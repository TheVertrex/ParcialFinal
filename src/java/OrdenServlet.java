import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ordenes")
public class OrdenServlet extends HttpServlet {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/BD_FINAL_PROGRA";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String edad = request.getParameter("edad");
        String nombreClase = request.getParameter("nombreClase");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO ordenes (nombre, apellido, edad, nombreClase) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, edad);
            stmt.setString(4, nombreClase);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ordenes.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String[]> ordenes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM ordenes";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] orden = {rs.getString("nombre"), rs.getString("apellido"), rs.getString("edad"), rs.getString("nombreClase")};
                ordenes.add(orden);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("ordenes", ordenes);
        request.getRequestDispatcher("ordenes.jsp").forward(request, response);
    }
}
