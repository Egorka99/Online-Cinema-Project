<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Регистрация</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
<h1>Регистрация</h1>
<form action="register" method="POST">
    Ваш логин:<br><input name="login" />
    <br><br>
    Ваш пароль:<br><input type="password" name="password" />
    <br><br>
    <input type="submit" value="Submit" />
    <a style="margin-left: 15px;" href="index.jsp">Вход в систему</a>
</form>
</body>
</html>