<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Результаты поиска</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
    <h1>Результаты поиска: </h1>
    <div class="container">
    <section>
        <div>
            <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                <thead>
                    <tr>
                        <th>History ID </th>
                        <th>User ID</th>
                        <th>Action Text</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="history" items="${historyList}" >
                        <tr>
                            <td><c:out value="${history.getId()}" /></td>
                            <td><c:out value="${history.getUserId()}" /></td>
                            <td><c:out value="${history.getActionText()}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
             </table>
             <a href="<%=request.getContextPath()%>/admin">Назад</a>
        </div>
    </section>
</body>
</html>