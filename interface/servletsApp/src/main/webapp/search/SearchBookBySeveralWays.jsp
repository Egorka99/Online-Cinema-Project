<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Поиск книги по году, количеству страниц, наименованию</title>
</head>
<body>
<h1>Найти книгу по году, количеству страниц, наименованию</h1>
<form action="<%=request.getContextPath()%>/search/searchBookBySeveralWays" method="POST">
    Год выпуска<br><input name="year" />
    <br><br>
    Количество страниц<br><input name="page_count" />
    <br><br>
    Название книги<br><input name="book_name" />
    <br><br>
    <input type="submit" value="Найти книгу" />
</form>
</body>
</html>