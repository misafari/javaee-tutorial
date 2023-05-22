package com.safari.task.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "hello",
//        value = "hello",
        loadOnStartup = 1,
        urlPatterns = {
                "/hello",
                "/hi"
        }
)
public class HelloServletAnnotation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        var writer = resp.getWriter();
        writer.println("<html><body>");

        var responseMessage = "Hello";

        if (req.getRequestURI().equals("/hi")) {
            responseMessage = "Hi";
        }

        writer.println(String.format("<h1>%s :D</h1>", responseMessage));

        var parameterMap = req.getParameterMap();

        String[] ids = parameterMap.get("id");
        for (var id : ids) {
            writer.println(String.format("<h2>id: %s</h2>", id));
        }

        writer.println("</body></html>");
    }
}
