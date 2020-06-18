package com.epam.servlets.search;

import com.epam.library.business.SearchBookUtilImpl;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.ISearchBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search/searchBookByReleaseYearRange")
public class SearchingBookByReleaseYearRangeServlet extends HttpServlet {

    private ISearchBookService bookService = new SearchBookUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = "/searchresult.jsp";

        try {
            int startYear = Integer.parseInt(request.getParameter("start_year"));
            int endYear = Integer.parseInt(request.getParameter("end_year"));

            request.getSession().setAttribute("bookList", bookService.searchBooksByReleaseYearRange(startYear,endYear));
            request.getServletContext().getRequestDispatcher(path).forward(request, response);

        } catch (NumberFormatException | ServletException | BookOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
            printWriter.println("<a href=\"books\">Назад</a>");
        }
        finally {
            printWriter.close();
        }
    }
}
