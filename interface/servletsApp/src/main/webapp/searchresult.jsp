<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Результаты поиска</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
    <h1>Результаты поиска: </h1>
    <div class="container">
    <section>
        <div>
            <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                <thead>
                    <tr>
                        <th>Book id</th>
                        <th>Book name</th>
                        <th>Release Year</th>
                        <th>Page Count</th>
                        <th>ISBN</th>
                        <th>Publisher</th>
                        <th>Author id</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${bookList}" >
                        <tr>
                            <td><c:out value="${book.getId()}" /></td>
                            <td><c:out value="${book.getBookName()}" /></td>
                            <td><c:out value="${book.getReleaseYear()}" /></td>
                            <td><c:out value="${book.getPageCount()}" /></td>
                            <td><c:out value="${book.getISBN()}" /></td>
                            <td><c:out value="${book.getPublisher()}" /></td>
                            <td><c:out value="${book.getAuthor().getId()}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
             </table>
             <a href="<%=request.getContextPath()%>/books">Назад</a>
        </div>
    </section>
</body>
</html>