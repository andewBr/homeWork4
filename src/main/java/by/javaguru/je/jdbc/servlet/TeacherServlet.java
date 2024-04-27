package by.javaguru.je.jdbc.servlet;


import by.javaguru.je.jdbc.entity.Teacher;
import by.javaguru.je.jdbc.servise.TeacherServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {

    // Создание экземпляра класса TeacherService
    private final TeacherServiceImpl teacherService = TeacherServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        List<Teacher> teachers = teacherService.findAll();

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head><title>Список учителей</title></head><body>");
        htmlBuilder.append("<h1>Список учителей:</h1>");
        htmlBuilder.append("<table border=\"1\">");
        htmlBuilder.append("<tr><th>ID</th><th>Имя</th><th>Subject ID</th></tr>");
        for (Teacher teacher : teachers) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(teacher.getId()).append("</td>");
            htmlBuilder.append("<td>").append(teacher.getName()).append("</td>");
            htmlBuilder.append("<td>").append(teacher.getsubjectId()).append("</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");
        htmlBuilder.append("</body></html>");

        response.getWriter().println(htmlBuilder.toString());
    }
}

