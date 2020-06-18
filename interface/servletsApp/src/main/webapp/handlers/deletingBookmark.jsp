<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=utf-8" %>
<title>Удаление закладки</title>
</head>
<body>
<h1>Удалить закладку</h1>
<form action="<%=request.getContextPath()%>/actions/deleteBookmark" method="POST">
    ID закладки<br><input name="bookmark_id" />
    <br><br>
    <input type="submit" value="Удалить закладку" />
</form>
</body>
</html>