/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import basicobject.User;
import dbaccess.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author overw
 */
public class Servlet1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<User> list = UserDao.getAllUsers();
            if (list != null) {
                out.print("<table>");
                out.print("<tr>"
                        + "<th>id</th>"
                        + "<th>name</th>"
                        + "<th>role</th>"
                        + "<th>action</th>"
                        + "</tr>");
                for (User us : list) {
                    out.print("<form action='Servlet2' method='post'>");
                    out.print("<input type='hidden' name='txtuserid' value='" + us.getUserid() + "'>");
                    out.print("<tr>");
                    out.print("<td>" + us.getUserid() + "</td>");
                    out.print("<td>" + us.getFullname() + "</td>");
                    out.print("<td>" + us.getRole() + "</td>");
                    if (!us.getRole().equalsIgnoreCase("ad")) {
                        out.print("<td>"
                                + "<input type='submit' value='remove' name='btn'>"
                                + "<input type='submit' value='reset pass' name='btn'>"
                                + "</td>");
                    }
                    out.print("</tr>");
                    out.print("</form>");
                }
                out.print("</table>");
            } else {
                out.print("<h1> no data </h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
