<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Получение истории пользователя</title>
</head>
<body>
<h1>Получить историю пользователя</h1>
<form action="<%=request.getContextPath()%>/search/searchUserHistory" method="POST">
     ID пользователя <br><input name="user_id" />
    <br><br>
    <input type="submit" value="Получить историю пользователя" />
</form>
</body>
</html>