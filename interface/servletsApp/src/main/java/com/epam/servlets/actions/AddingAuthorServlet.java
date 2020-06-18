package com.epam.servlets.actions;

import com.epam.library.business.AuthorUtilImpl;
import com.epam.library.business.interfaces.IAuthorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/actions/addAuthor")
public class AddingAuthorServlet extends HttpServlet {

    private IAuthorService authorService = new AuthorUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/authors";

        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String lastName = request.getParameter("last_name");
        Date dob = Date.valueOf(request.getParameter("dob"));

        authorService.addNewAuthor(firstName, secondName, lastName, dob);

        printWriter.println("<h3>Автор успешно добавлен!</h3>");
        printWriter.println("<a href=\"" + path + "\">Назад</a>");
        printWriter.close();
    }
}
