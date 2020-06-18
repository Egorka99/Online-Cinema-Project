package com.epam.servlets.actions;


import com.epam.library.business.AdminUtilImpl;
import com.epam.library.business.AuthorUtilImpl;
import com.epam.library.business.exceptions.AuthorOperationException;
import com.epam.library.business.interfaces.IAdminService;
import com.epam.library.business.interfaces.IAuthorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/blockUser")
public class BlockingUserServlet extends HttpServlet {

    private IAdminService adminService = new AdminUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/admin";

        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));

            adminService.blockUser(userId);
            printWriter.println("<h3>Пользователь успешно заблокирован!</h3>");
        } catch (NumberFormatException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}