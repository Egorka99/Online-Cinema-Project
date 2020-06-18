<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Добавление автора</title>
</head>
<body>
<h1>Добавить автора</h1>
<form action="<%=request.getContextPath()%>/actions/addAuthor" method="POST">
    Имя <br><input name="first_name" />
    <br><br>
    Отчество <br><input name="second_name" />
    <br><br>
    Фамилия <br><input name="last_name" />
    <br><br>
    Дата рождения(yyyy-mm-dd) <br><input name="dob" />
    <br><br>
    <input type="submit" value="Добавить автора" />
</form>
</body>
</html>