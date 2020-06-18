<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Добавление книг из JSON</title>
</head>
<body>
<h1>Добавить книги из JSON</h1>
<form action="<%=request.getContextPath()%>/actions/addBooksFromJson" method="POST">
    <input type="file" name="json_file" accept="application/json" />
    <br><br>
    <input type="submit" value="Добавить книги" />
</form>
</body>
</html>