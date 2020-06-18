<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ page import = "com.epam.library.model.User"  %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторы</title>
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
                <button><a href="<%=request.getContextPath()%>/handlers/addingAuthor.jsp">Добавить автора</a></button>
                <button><a href="<%=request.getContextPath()%>/handlers/deletingAuthor.jsp">Удалить автора с его книгами</a></button>
            </div>
        </div>
    </section>
     <section>
            <div>
                <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                    <thead>
                        <tr>
                            <th>Author id</th>
                            <th>Name</th>
                            <th>Second name</th>
                            <th>Last name</th>
                            <th>Date of birth</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="author" items="${authorList}" >
                            <tr>
                                <td><c:out value="${author.getId()}" /></td>
                                <td><c:out value="${author.getName()}" /></td>
                                <td><c:out value="${author.getSecondName()}" /></td>
                                <td><c:out value="${author.getLastName()}" /></td>
                                <td><c:out value="${author.getDob()}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                 </table>
            </div>
     </section>
    </body>
</body>
</html>