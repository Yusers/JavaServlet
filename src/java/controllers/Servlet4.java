/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import basicobject.Car;
import dbaccess.CarDao;
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
public class Servlet4 extends HttpServlet {

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

//            Manage Car
            String carID = request.getParameter("txtcarid");
            String selectedButton = request.getParameter("btn");
            boolean status;
            if (selectedButton != null && selectedButton.equals("Disable")) {
                status = true;
                int rs = CarDao.updateStatus(carID, status);
                if (rs < 0) {
                    out.print("fail");
                }
            } else {
                status = false;
                int rs = CarDao.updateStatus(carID, status);
                if (rs < 0) {
                    out.print("fail");
                }
            }
            ArrayList<Car> list = CarDao.getAllUsers();
            if (list != null) {
                out.print("<head>");
                out.print("<style>\n"
                        + "            table, th, td {\n"
                        + "                border: 1px solid black;\n"
                        + "                border-collapse: collapse;\n"
                        + "            }\n"
                        +" th, td {"
                        + "padding: 10px"
                        + "}"
                        + "        </style>");
                out.print("</head>");
                out.print("<table>");
                out.print("<tr>"
                        + "<th>id</th>"
                        + "<th>name</th>"
                        + "<th>description</th>"
                        + "<th>price</th>"
                        + "<th>speed</th>"
                        + "<th>status</th>"
                        + "<th>action</th>"
                        + "</tr>");
                for (Car car : list) {
                    out.print("<form action='Servlet4' method='post'>");
                    out.print("<input type='hidden' name='txtcarid' value='" + car.getCarID() + "'>");
                    out.print("<tr>");
                    out.print("<td>" + car.getCarID() + "</td>");
                    out.print("<td>" + car.getCarName() + "</td>");
                    out.print("<td>" + car.getDescription() + "</td>");
                    out.print("<td>" + car.getPrice() + "</td>");
                    out.print("<td>" + car.getSpeed() + "</td>");
                    out.print("<td>" + car.isStatus() + "</td>");
                    out.print("<td><input type='submit' value=\"" + (car.isStatus() ? "Disable" : "Enable") + "\"name='btn'></td>");
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
