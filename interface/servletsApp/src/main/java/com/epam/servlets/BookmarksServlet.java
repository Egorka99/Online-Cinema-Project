package com.epam.servlets;

import com.epam.library.business.BookmarkUtilImpl;
import com.epam.library.business.interfaces.IBookmarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookmarks")
public class BookmarksServlet extends HttpServlet {

    private String path = "/bookmarks.jsp";
    private IBookmarkService bookmarkService = new BookmarkUtilImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("bookmarkList", bookmarkService.getAllBookmarks());
        request.getServletContext().getRequestDispatcher(path).forward(request, response);
    }
}
