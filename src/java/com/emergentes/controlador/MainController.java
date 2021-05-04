package com.emergentes.controlador;

import com.emergentes.ConexionDB;
import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 try {
            String op;
            op = (request.getParameter("op") != null)? request.getParameter("op"):"listado";
            ArrayList<Tarea> lista = new ArrayList<Tarea>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if (op.equals("listado")) {
                //para listar los datos
                String sql = "select * from tareas";
                // para consultar de seleccion y almacenar el una coleccion
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Tarea tar = new Tarea();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));
                    tar.setCompletado(rs.getInt("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                //enviar a index.jsp para mostrar la informacion
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            if (op.equals("nuevo")) {
                //instanciar un objeto de la clase Libro
                Tarea ta = new Tarea();                
                //El objeto se pone como tributo de request
                request.setAttribute("tarea", ta);
                //redireccionar a llenado.jsp
                request.getRequestDispatcher("llenado.jsp").forward(request, response);
            }
            
            if (op.equals("eliminar")) {
                //obtener el id
                int id = Integer.parseInt(request.getParameter("id"));
                //realizar la aeliminacion en la base de datos
                String sql = "delete from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                //redireccionar a MainController
                response.sendRedirect("MainController");
            }
            
            if (op.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                //para listar los datos
                String sql = "select * from tareas where id=?";
                // para consultar de seleccion y almacenar el una coleccion
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Tarea tar = new Tarea();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));
                    tar.setCompletado(rs.getInt("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                //enviar a index.jsp para mostrar la informacion
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA CONEXION:" + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            String tarea = request.getParameter("tarea");
            int prioridad = Integer.parseInt(request.getParameter("prioridad"));
            int completado = Integer.parseInt(request.getParameter("completado"));
            
            Tarea tar = new Tarea();
            tar.setId(id);
            tar.setTarea(tarea);
            tar.setPrioridad(prioridad);
            tar.setCompletado(completado);
            
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if (id == 0) {
                // nuevo registro
                String sql = "insert into tareas (tarea, prioridad, completado) values (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, tar.getTarea());
                ps.setInt(2, tar.getPrioridad());
                ps.setInt(3, tar.getCompletado());
                ps.executeUpdate();
            }else{
                // edicion de registro
                String sql = "UPDATE tareas SET tarea = ? , prioridad = ? , completado = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, tar.getTarea());
                ps.setInt(2, tar.getPrioridad());
                ps.setInt(3, tar.getCompletado());
                ps.setInt(4, tar.getId());
                ps.executeUpdate();
            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            System.out.println("ERROR EN SQL: " + ex.getMessage());
        }
    }
}
