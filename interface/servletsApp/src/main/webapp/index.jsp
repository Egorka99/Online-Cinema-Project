<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Вход в систему</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
<h1>Вход в систему</h1>
<form action="login" method="POST">
    Логин:<br><input name="login" />
    <br><br>
    Пароль:<br><input type="password" name="password" />
    <br><br>
    <input type="submit" value="Submit" />
    <a style="margin-left: 15px;" href="register.jsp">Регистрация</a>
</form>
</body>
</html>