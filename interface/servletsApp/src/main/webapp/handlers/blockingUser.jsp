<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Блокировка пользователя</title>
</head>
<body>
<h1>Заблокировать пользователя</h1>
<form action="<%=request.getContextPath()%>/actions/blockUser" method="POST">
    ID пользователя<br><input name="user_id" />
    <br><br>
    <input type="submit" value="Заблокировать пользователя" />
</form>
</body>
</html>