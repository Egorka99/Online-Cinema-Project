package com.epam.servlets;

import com.epam.library.business.AuthorUtilImpl;
import com.epam.library.business.interfaces.IAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authors")
public class AuthorsServlet extends HttpServlet {

    private String path = "/authors.jsp";
    private IAuthorService authorService = new AuthorUtilImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("authorList", authorService.getAllAuthors());
        request.getServletContext().getRequestDispatcher(path).forward(request, response);
    }
}
