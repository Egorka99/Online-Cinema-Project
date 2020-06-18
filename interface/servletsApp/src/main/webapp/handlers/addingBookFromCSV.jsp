<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Добавление книг из CSV</title>
</head>
<body>
<h1>Добавить книги из CSV</h1>
<form action="<%=request.getContextPath()%>/actions/addBooksFromCSV" method="POST">
    <input type="file" name="csv_file" accept=".csv" />
    <br><br>
    <input type="submit" value="Добавить книги" />
</form>
</body>
</html>