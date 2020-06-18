<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Регистрация нового пользователя</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="<%=request.getContextPath()%>/actions/registerNewUser" method="POST">
    Логин пользователя:<br><input name="login" />
    <br><br>
    Пароль пользователя:<br><input type="password" name="password" />
    <br><br>
    <input type="submit" value="Submit" />
</form>
</body>
</html>