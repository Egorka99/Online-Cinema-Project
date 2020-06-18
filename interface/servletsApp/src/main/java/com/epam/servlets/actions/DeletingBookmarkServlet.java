package com.epam.servlets.actions;

import com.epam.library.business.BookmarkUtilImpl;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookmarkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/deleteBookmark")
public class DeletingBookmarkServlet extends HttpServlet {

    private IBookmarkService bookmarkService = new BookmarkUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/bookmarks";

        try {
            int bookmarkId = Integer.parseInt(request.getParameter("bookmark_id"));

            bookmarkService.deleteBookmark(bookmarkId);
            printWriter.println("<h3>Закладка успешно удалена!</h3>");

        } catch (NumberFormatException | BookOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}