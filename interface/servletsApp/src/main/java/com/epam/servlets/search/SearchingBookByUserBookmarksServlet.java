package com.epam.servlets.search;

import com.epam.library.business.SearchBookUtilImpl;
import com.epam.library.business.interfaces.ISearchBookService;
import com.epam.library.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search/searchBookByUserBookmarks")
public class SearchingBookByUserBookmarksServlet extends HttpServlet {

    private ISearchBookService bookService = new SearchBookUtilImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = "/searchresult.jsp";

        try {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().setAttribute("bookList", bookService.searchBooksByUserBookmark(user.getId()));
            request.getServletContext().getRequestDispatcher(path).forward(request, response);

        } catch (NumberFormatException | ServletException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
            printWriter.println("<a href=\"books\">Назад</a>");
        }
        finally {
            printWriter.close();
        }
    }
}
