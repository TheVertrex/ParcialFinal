<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Órdenes de Alumnos</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Órdenes de Alumnos</h1>

    <!-- Formulario para agregar nueva orden -->
    <form action="ordenes" method="post">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" required>
        
        <label for="apellido">Apellido</label>
        <input type="text" id="apellido" name="apellido" required>
        
        <label for="edad">Edad</label>
        <input type="number" id="edad" name="edad" required>
        
        <label for="nombreClase">Clase</label>
        <input type="text" id="nombreClase" name="nombreClase" required>
        
        <button type="submit">Agregar Orden</button>
    </form>

    <!-- Tabla para mostrar órdenes -->
    <table>
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Edad</th>
                <th>Clase</th>
            </tr>
        </thead>
        <tbody>
            <%
                ArrayList<String[]> ordenes = (ArrayList<String[]>) request.getAttribute("ordenes");
                if (ordenes != null) {
                    for (String[] orden : ordenes) {
            %>
                <tr>
                    <td><%= orden[0] %></td>
                    <td><%= orden[1] %></td>
                    <td><%= orden[2] %></td>
                    <td><%= orden[3] %></td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
