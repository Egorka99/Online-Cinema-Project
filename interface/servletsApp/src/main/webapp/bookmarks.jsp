<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ page import = "com.epam.library.model.User"  %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Закладки</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
    <div class="container">
    <header class="header">
            <h1 class="header-title">Библиотека</h1>
            <ul class="header-list">
                <li><a href="books">Книги</a></li>
                <li><a href="authors">Авторы</a></li>
                <li><a href="bookmarks">Закладки</a></li>
                 <%
                    User user = (User)request.getSession().getAttribute("user");
                    if(user.isAdmin()) {
                        out.println("<li><a href=\"admin\">Администрирование</a></li>");
                    }
                 %>
            </ul>
            <div class="header-profile">
                <img src="<%=request.getContextPath()%>/images/profile.png" alt="">
                <span>Привет, ${user.getNickname()}</span>
            </div>
            <button><a href="<%=request.getContextPath()%>/logout">Выход</a></button>
    </header>
    <section class="control">
        <div class="container">
            <div class="control-buttons__operation">
                <h2>Действия:</h2>
                <button><a href="<%=request.getContextPath()%>/handlers/addingBookmark.jsp">Добавить закладку</a></button>
                <button><a href="<%=request.getContextPath()%>/handlers/deletingBookmark.jsp">Удалить закладку</a></button>
            </div>
        </div>
    </section>
     <section>
        <div>
            <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                <thead>
                    <tr>
                        <th>Bookmark id</th>
                        <th>User id</th>
                        <th>Book id</th>
                        <th>Page Number</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bookmark" items="${bookmarkList}" >
                        <tr>
                            <td><c:out value="${bookmark.getId()}" /></td>
                            <td><c:out value="${bookmark.getUserId()}" /></td>
                            <td><c:out value="${bookmark.getBookId()}" /></td>
                            <td><c:out value="${bookmark.getPageNumber()}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
             </table>
        </div>
     </section>
    </body>
</body>
</html>