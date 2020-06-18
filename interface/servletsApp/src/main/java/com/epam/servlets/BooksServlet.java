package com.epam.servlets;

import com.epam.library.business.BookUtilImpl;
import com.epam.library.business.interfaces.IBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    private String path = "/books.jsp";
    private IBookService bookService = new BookUtilImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("bookList", bookService.getAllBooks());
        request.getServletContext().getRequestDispatcher(path).forward(request, response);
    }

}
