<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Поиск книги по диапазону годов выпуска</title>
</head>
<body>
<h1>Найти книгу по диапазону годов выпуска</h1>
<form action="<%=request.getContextPath()%>/search/searchBookByReleaseYearRange" method="POST">
    Начальный год<br><input name="start_year" />
    <br><br>
    Конечный год<br><input name="end_year" />
    <br><br>
    <input type="submit" value="Найти книгу" />
</form>
</body>
</html>