<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Добавление книги</title>
</head>
<body>
<h1>Добавить книгу</h1>
<form action="<%=request.getContextPath()%>/actions/addBook" method="POST">
    Название книги<br><input name="book_name" />
    <br><br>
    Год издания<br><input name="release_year" />
    <br><br>
    ISBN<br><input name="isbn" />
    <br><br>
    Издатель<br><input name="publisher" />
    <br><br>
    Количество страниц<br><input name="page_count" />
    <br><br>
    ID автора<br><input name="author_id" />
    <br><br>
    <input type="submit" value="Добавить книгу" />
</form>
</body>
</html>