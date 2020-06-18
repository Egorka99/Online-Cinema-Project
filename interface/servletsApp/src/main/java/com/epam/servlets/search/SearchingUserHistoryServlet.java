package com.epam.servlets.search;

import com.epam.library.business.AdminUtilImpl;
import com.epam.library.business.SearchBookUtilImpl;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IAdminService;
import com.epam.library.business.interfaces.ISearchBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/search/searchUserHistory")
public class SearchingUserHistoryServlet extends HttpServlet {

    private IAdminService adminService = new AdminUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = "/searchResultHistory.jsp";

        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));

            request.getSession().setAttribute("historyList", adminService.getUserHistory(userId));
            request.getServletContext().getRequestDispatcher(path).forward(request, response);
        } catch (NumberFormatException | ServletException | UserOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
            printWriter.println("<a href=\"admin\">Назад</a>");
        }
        finally {
            printWriter.close();
        }
    }
}
