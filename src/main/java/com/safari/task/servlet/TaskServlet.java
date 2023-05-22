package com.safari.task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "task-servlet", value = "/tasks")
public class TaskServlet extends HttpServlet {
    // Lifecycle ---- initial ------
    static {
        // class loader
        System.out.println("(Static Block) Task Servlet has been created");
    }

    public TaskServlet() {
        // creating object
        System.out.println("(Constructor) Task Servlet has been created");
    }

    public void init() {
        // call by servlet container
        System.out.println("(Init) Task Servlet has been created");
    }

    // Lifecycle ---- during running ------

   // it will accept all servlets such as ftp servlet (plz read the implementation)
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    // it will accept only http servlet request
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            resp.setContentType("text/html");

            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Ronin" + "</h1>");
            out.println("</body></html>");
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//
//        PrintWriter out = resp.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + "Ronin2" + "</h1>");
//        out.println("</body></html>");
//    }

    // Lifecycle ---- I am dying ------
    public void destroy() {
        // call by servlet container
        System.out.println("(Destroy) Task servlet is dying");
    }
}
