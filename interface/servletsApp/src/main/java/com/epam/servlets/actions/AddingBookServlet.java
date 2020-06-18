package com.epam.servlets.actions;

import com.epam.library.business.BookUtilImpl;
import com.epam.library.business.exceptions.BookOperationException;
import com.epam.library.business.interfaces.IBookService;
import com.epam.library.dao.AuthorAccessImpl;
import com.epam.library.dao.interfaces.IBaseAccessService;
import com.epam.library.model.Author;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/actions/addBook")
public class AddingBookServlet extends HttpServlet {

    private IBookService bookService = new BookUtilImpl();
    private IBaseAccessService<Author> authorAccessService = new AuthorAccessImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        String path = request.getContextPath() + "/books";

        try {
            String bookTitle = request.getParameter("book_name");
            int releaseYear = Integer.parseInt(request.getParameter("release_year"));
            String isbn = request.getParameter("isbn");
            String publisher = request.getParameter("publisher");
            int pageCount = Integer.parseInt(request.getParameter("page_count"));
            int authorId = Integer.parseInt(request.getParameter("author_id"));

            bookService.addNewBook(bookTitle,releaseYear,isbn,publisher,pageCount,authorAccessService.getEntity(authorId));

            printWriter.println("<h3>Книга успешно добавлена!</h3>");
        } catch ( BookOperationException e) {
            printWriter.
                    println("<h3>Ошибка: " + e.getMessage() + "</h3>");
        }
        finally {
            printWriter.println("<a href=\""+path+"\">Назад</a>");
            printWriter.close();
        }
    }
}
