package com.epam.servlets.actions;

import com.epam.library.business.BookUtilImpl;
import com.epam.library.business.interfaces.IBookService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/deleteBook")
public class DeletingBookServlet extends HttpServlet {

    private IBookService bookService = new BookUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/books";

        try {
            int bookId = Integer.parseInt(request.getParameter("book_id"));

            bookService.deleteBook(bookId);
            printWriter.println("<h3>Книга успешно удалена!</h3>");

        } catch ( NumberFormatException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}