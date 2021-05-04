<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Tarea"%>
<%
        List<Tarea> lista = (List<Tarea>)request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edicion de Tareas</h1>
        <form action="MainController" method="POST">
            <table>               
                <c:forEach var="item" items="${lista}">
                     <input type="hidden" name="id" value="${item.id}">
                    <tr>
                        <td>Tarea</td>
                        <td><input type="text" name="tarea" value="${item.tarea}"></td>
                    </tr>
                    <tr>
                        <td>Prioridad</td>
                        <td>
                            <select name="prioridad">
                                <c:if test="${item.prioridad == 1}">
                                    <option value="1">Alta</option>
                                    <option value="2">Media</option>
                                    <option value="3">Baja</option>
                                </c:if>
                                <c:if test="${item.prioridad == 2}">
                                    <option value="2">Media</option>
                                    <option value="1">Alta</option>
                                    <option value="3">Baja</option>
                                </c:if>
                                <c:if test="${item.prioridad == 3}">
                                    <option value="3">Baja</option>
                                    <option value="1">Alta</option>
                                    <option value="2">Media</option>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Completado</td>
                        <td>
                            <select name="completado">
                                <c:if test="${item.completado == 1}">
                                <option value="1">Concluido</option>
                                <option value="2">Pendiente</option>
                                </c:if>
                                <c:if test="${item.completado == 2}">
                                <option value="2">Pendiente</option>
                                <option value="1">Concluido</option> 
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Enviar"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>  
    </body>
</html>