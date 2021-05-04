<%@page import="com.emergentes.modelo.Tarea"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Tarea tar =(Tarea)request.getAttribute("tarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nueva Tarea</h1>
        <form action="MainController" method="POST">
            <table>
                <input type="hidden" name="id" value="${tarea.id}">
                    <tr>
                        <td>Tarea</td>
                        <td><input type="text" name="tarea" value="${tarea.tarea}"></td>
                    </tr>
                    <tr>
                        <td>Prioridad</td>
                        <td>
                            <select name="prioridad">
                                <option value="1">Alta</option>
                                <option value="2">Media</option>
                                <option value="3">Baja</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Completado</td>
                        <td>
                            <select name="completado">
                                <option value="1">Concluido</option>
                                <option value="2">Pendiente</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enviar"></td>
                    </tr>
            </table>
        </form>
    </body>
</html>