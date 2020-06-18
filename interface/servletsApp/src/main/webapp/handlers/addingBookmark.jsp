<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Добавление закладки</title>
</head>
<body>
<h1>Добавить закладку</h1>
<form action="<%=request.getContextPath()%>/actions/addBookmark" method="POST">
    ID пользователя <br><input name="user_id" />
    <br><br>
    ID книги <br><input name="book_id" />
    <br><br>
    Номер страницы <br><input name="page_number" />
    <br><br>
    <input type="submit" value="Добавить закладку" />
</form>
</body>
</html>