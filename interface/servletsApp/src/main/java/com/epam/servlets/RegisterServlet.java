package com.epam.servlets;

import com.epam.library.business.UserUtilImpl;
import com.epam.library.business.exceptions.UserOperationException;
import com.epam.library.business.interfaces.IUserService;
import com.epam.library.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private IUserService userService = new UserUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            userService.registerNewUser(login, password);
            printWriter.println("Пользователь успешно зарегистрирован");
            printWriter.println("<a href=\"index.jsp\">Войти в систему</a>");
        } catch (UserOperationException e) {
            printWriter.println("<h3>Ошибка: "+ e.getMessage()+"</h3>");
            printWriter.println("<a href=\"register.jsp\">Назад</a>");
        }
        finally {
            printWriter.close();
        }
    }
}
