package com.epam.servlets;

import com.epam.library.model.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

@WebFilter(
        urlPatterns = {"/authors","/books","/bookmarks","/admin"},
        initParams = @WebInitParam(name = "active", value = "true")
)
public class SecurityFilter implements Filter
{
    private FilterConfig config = null;
    private boolean active = false;

    public void init (FilterConfig config) throws ServletException
    {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException, ServletException
    {
        if (active) {
            HttpServletRequest req =
                    (HttpServletRequest) request;
            HttpSession session = req.getSession(false);
            if (session != null) {
                 User user = (User) session.getAttribute("user");
                if (user != null) {
                    chain.doFilter(request, response);
                }
                else {
                    throw new ServletException("You shall not pass!");
                }
            }
            else {
                throw new ServletException("You shall not pass!");
            }
        }
    }

    public void destroy()
    {
        config = null;
    }
}
