package by.javaguru.je.jdbc.servlet;

import by.javaguru.je.jdbc.entity.SchoolSubjects;
import by.javaguru.je.jdbc.servise.SubjectsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {

    private final SubjectsServiceImpl subjectService = SubjectsServiceImpl.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        List<SchoolSubjects> subjects = subjectService.findAll();

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<table>");
        htmlBuilder.append("<tr><th>ID</th><th>Предмет</th></tr>");
        for (SchoolSubjects subject : subjects) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(subject.getId()).append("</td>");
            htmlBuilder.append("<td>").append(subject.getSubject()).append("</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");

        response.getWriter().println(htmlBuilder.toString());
    }


}
