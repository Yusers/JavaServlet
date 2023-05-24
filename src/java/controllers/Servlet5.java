/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbaccess.CarDao;
import basicobject.Car;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author overw
 */
public class Servlet5 extends HttpServlet {

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
            String carID = request.getParameter("txtCarId");
            String txtCarName = request.getParameter("txtCarName");
            String description = request.getParameter("txtDescription");
            String priceParam = request.getParameter("txtPrice");
            double price = Double.parseDouble(priceParam);
            String speedParam = request.getParameter("txtSpeed");
            int speed = Integer.parseInt(speedParam);
            String statusParam = request.getParameter("txtStatus");
            boolean status = Boolean.parseBoolean(statusParam);
            Car car = CarDao.getCar(carID);
            if (car == null) {
                // ko co user trung lap
                int rs = CarDao.insertCar(carID, txtCarName, description, price, speed, status);
                if (rs > 0) {
                    out.print("inserted");
                } else {
                    out.print("fail");
                }
            } else {
                // userid nay
                out.print("<h1>Duplicate CarID</h1>");
                out.print("<p><a href='index.html'>back</a></p>");
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
