package com.epam.servlets.actions;

import com.epam.library.business.BookUtilImpl;
import com.epam.library.business.JSONParser;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.business.interfaces.Parser;
import com.opencsv.exceptions.CsvException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;


@WebServlet("/actions/addBooksFromJson")
public class AddingBooksFromJsonServlet extends HttpServlet {

    private Parser parser = new JSONParser();
    private IBookService bookService = new BookUtilImpl();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = httpServletResponse.getWriter();
        String path = httpServletRequest.getContextPath() + "/books";

        try {
            bookService.addBooksFromList(parser.parse(httpServletRequest.getParameter("json_file")));
            printWriter.println("<h3>Книги успешно добавлены!</h3>");
        } catch (CsvException | ParseException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\"" + path + "\">Назад</a>");
            printWriter.close();
        }
    }
}
