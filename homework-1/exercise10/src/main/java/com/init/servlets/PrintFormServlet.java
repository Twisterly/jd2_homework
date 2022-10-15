package com.init.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class PrintFormServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
            IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String tnumber = request.getParameter("tnumber");
        String mail = request.getParameter("email");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (name.isEmpty()) {
            out.write("Error. Write your name, please");
        } else if (tnumber.isEmpty() && mail.isEmpty()) {
            out.write("Error. Write your telephone number or email, please");
        } else if (tnumber.isEmpty()) {
            out.println("<html><body><h4>Name: " + name + "</h4>");
            out.println("<h4>Email: " + mail + "</h4>");
            out.println("<br></body></html>");
        } else if (mail.isEmpty()) {
            out.println("<html><body><h4>Name: " + name + "</h4>");
            out.println("<h4>Telephone number: " + tnumber + "</h4>");
            out.println("<br></body></html>");
        } else {
            out.println("<html><body><h4>Name: " + name + "</h4>");
            out.println("<h4>Telephone number: " + tnumber + "</h4>");
            out.println("<h4>Email: " + mail + "</h4>");
            out.println("<br></body></html>");
        }

    }
}
