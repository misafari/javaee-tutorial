package com.safari.task.servlet;

import com.safari.task.dao.TaskDataAccess;
import com.safari.task.entity.Task;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tasks/create")
public class TaskCreateServlet extends HttpServlet {
    private TaskDataAccess dataAccess = null;

    @Override
    public void init() {
        dataAccess = TaskDataAccess.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        var writer = resp.getWriter();
        String responseBody = "<!DOC TYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Create New Task</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/tasks/create\" method=\"post\">\n" +
                "    <field-set>\n" +
                "        <legend>New Task</legend>\n" +
                "        <label for=\"taskName\">Task name: </label>\n" +
                "        <input type=\"text\" id=\"taskName\" name=\"taskName\" required>\n" +
                "        <br>\n" +
                "        <br>\n" +
                "        <input type=\"submit\" value=\"Save\">\n" +
                "        <input type=\"reset\" value=\"Reset\">\n" +
                "    </field-set>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
        writer.println(responseBody);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var taskName = req.getParameter("taskName");

        if (!taskName.isBlank()) {
            dataAccess.updateOrSave(new Task(taskName));
        }

        resp.setContentType("text/html");

        var writer = resp.getWriter();
        writer.println("<html><body>");
        writer.println("<h1>Done</h1>");
        writer.println("</body></html>");
    }
}
