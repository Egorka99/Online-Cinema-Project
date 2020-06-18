<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Удаление автора с его книгами</title>
</head>
<body>
<h1>Удалить автора вместе с его книгами</h1>
<form action="<%=request.getContextPath()%>/actions/deleteAuthor" method="POST">
    ID автора<br><input name="author_id" />
    <br><br>
    <input type="submit" value="Удалить автора и книги" />
</form>
</body>
</html>