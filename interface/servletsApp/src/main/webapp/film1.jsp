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
        <h2>Побег из шоушенка</h2>
        <div>IMDB идентификатор: tt0111161</div>
        <div>Тип фильма: кино</div>
        <div>Жанр: драма</div>
        <div>Дата выхода: 10 сентября 1994</div>
        <div>Рейтинг: 9.3</div>
    </section>
    <section>
        <h3>Отзывы:</h3>
        <button><a href="review.jsp">Добавить отзыв</a></button>
        <br><br><br>
        <div>
            <div class="header-profile">
                 <img src="/home/images/profile.png" alt="">
                 <span>User1233123</span>
            </div>
                <p>Отличный фильм!</p>
                <p>Пользовательский рейтинг: 9.5</p>
        </div>
        <br><br><br>
        <div>
            <div class="header-profile">
                <img src="/home/images/profile.png" alt="">
                <span>User43543</span>
             </div>
            <p>Мне понравилось!</p>
            <p>Пользовательский рейтинг: 9.1</p>
        </div>
    </section>
</body>
</html>