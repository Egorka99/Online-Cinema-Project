<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Удаление книги</title>
</head>
<body>
<h1>Удалить книгу</h1>
<form action="<%=request.getContextPath()%>/actions/deleteBook" method="POST">
    ID книги<br><input name="book_id" />
    <br><br>
    <input type="submit" value="Удалить книгу" />
</form>
</body>
</html>