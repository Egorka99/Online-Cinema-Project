<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ page import = "com.epam.library.model.User"  %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Онлайн-кинотеатр</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
    <div class="container">
    <header class="header">
            <h1 class="header-title">Онлайн кинотеатр</h1>
            <ul class="header-list">
                            <li><a href="books">Фильмы</a></li>
                        </ul>
            <div class="header-profile">
                    <img src="/home/images/profile.png" alt="">
                    <span>Привет, ${user.getNickname()}</span>
            </div>
            <button><a href="<%=request.getContextPath()%>/logout">Выход</a></button>
    </header>
    <section>
        <h1>Оставить отзыв к фильму</h1>
        <form action="#" method="POST">
            Отзыв:<br><textarea></textarea>
            <br><br>
            Оценка:<br><input />
            <br><br>
            <input type="submit" value="Submit" />
        </form>
    </section>
</body>
</html>