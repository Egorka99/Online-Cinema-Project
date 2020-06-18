package com.epam.servlets.actions;


import com.epam.library.business.AdminUtilImpl;
import com.epam.library.business.UserUtilImpl;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IAdminService;
import com.epam.library.business.interfaces.IUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/registerNewUser")
public class RegisterNewUserServlet extends HttpServlet {

    private IAdminService adminService = new AdminUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/admin";

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            adminService.registerNewUser(login,password);

            printWriter.println("Пользователь успешно зарегистрирован");
        } catch (UserOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}
