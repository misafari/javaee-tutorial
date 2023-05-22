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
                String responseBody = "<html><body><h1>Hi :D</h1></body></html>";
                writer.println(responseBody);
        }
}
