package com.epam.servlets.actions;

import com.epam.library.business.AuthorUtilImpl;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.interfaces.IAuthorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/deleteAuthor")
public class DeletingAuthorServlet extends HttpServlet {

    private IAuthorService authorService = new AuthorUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/authors";

        try {
            int authorId = Integer.parseInt(request.getParameter("author_id"));

            authorService.deleteAuthorWithHisBooks(authorId);
            printWriter.println("<h3>Автор успешно удален!</h3>");
        } catch (NumberFormatException | AuthorOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}