package com.epam.servlets.actions;

import com.epam.library.business.BookUtilImpl;
import com.epam.library.business.BookmarkUtilImpl;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.business.interfaces.IBookmarkService;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Author;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/addBookmark")
public class AddingBookmarkServlet extends HttpServlet {

    private IBookmarkService bookmarkService = new BookmarkUtilImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/bookmarks";

        int userId = Integer.parseInt(request.getParameter("user_id"));
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        int pageNumber = Integer.parseInt(request.getParameter("page_number"));

        try {
            bookmarkService.addNewBookmark(userId,bookId,pageNumber);
            printWriter.println("<h3>Закладка успешно добавлена!</h3>");

        } catch (BookOperationException e) {
            printWriter.println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\"" + path + "\">Назад</a>");
            printWriter.close();
        }

    }
}
