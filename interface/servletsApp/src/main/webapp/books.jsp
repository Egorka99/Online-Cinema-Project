<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page contentType="text/html;charset=utf-8" %>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ page import = "com.epam.library.model.User"  %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Фильмы</title>
    <link rel="stylesheet" href="style.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
</head>
<body>
    <div class="container">
    <header class="header">
            <h1 class="header-title">Онлайн кинотеатр</h1>
            <div class="header-profile">
                    <img src="/home/images/profile.png" alt="">
                    <span>Привет, ${user.getNickname()}</span>
            </div>
            <button><a href="<%=request.getContextPath()%>/logout">Выход</a></button>
    </header>
    <section class="control">
        <div class="container">
            <div class="control-buttons__search">
                <h2>Поиск:</h2>
                <button><a href="<%=request.getContextPath()%>/search/searchBookByName.jsp">Найти фильм по идентификатору</a></button>
                <button><a href="<%=request.getContextPath()%>/search/SearchBookByAuthorName.jsp">Найти фильм по названию</a></button>
                <button><a href="<%=request.getContextPath()%>/search/SearchBookByISBN.jsp">Найти фильм по дате выхода</a></button>
            </div>
        </div>
    </section>
    <section>
        <div>
            <table class="table" border=1 style="width: 100%; margin-top: 20px;text-align: center;">
                <thead>
                    <tr>
                        <th>IMDB идентификатор</th>
                        <th>Тип фильма</th>
                        <th>Название</th>
                        <th>Жанр</th>
                        <th>Дата выхода</th>
                        <th>Рейтинг</th>
                        <th>Описание</th>
                    </tr>
                </thead>
                <tbody>
                        <tr>
                            <td>tt0111161</td>
                            <td>Кино</td>
                            <td><a href="film1.jsp">Побег из Шоушенка</a></td>
                            <td>драма</td>
                            <td>10 сентября 1994</td>
                            <td>9.3</td>
                            <td>Бухгалтер Энди Дюфрейн обвинён в убийстве собственной жены и её любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решётки...</td>
                        </tr>
                        <tr>
                            <td>tt0120689</td>
                            <td>Кино</td>
                            <td><a href="">Зелёная миля</a></td>
                            <td>фантастика</td>
                            <td>6 декабря 1999</td>
                            <td>8.6</td>
                            <td>Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы...</td>
                        </tr>
                        <tr>
                            <td>tt0110357</td>
                            <td>Мультфильм</td>
                            <td><a href="">Король Лев</a></td>
                            <td>мюзикл,драма</td>
                            <td>7 мая 1994</td>
                            <td>8.5</td>
                            <td>У величественного Короля-Льва Муфасы рождается наследник по имени Симба. Уже в детстве любознательный малыш становится жертвой интриг своего завистливого дяди Шрама, мечтающего о власти...</td>
                        </tr>
                        <tr>
                            <td>tt01310357</td>
                            <td>Сериал</td>
                            <td><a href="">Сверхъестественное</a></td>
                            <td>фэнтези</td>
                            <td>2005</td>
                            <td>8.2</td>
                            <td>Сериал рассказывает о приключениях братьев Сэма и Дина Винчестеров, которые путешествуют по Соединённым Штатам на чёрном автомобиле Chevrolet Impala 1967 года, расследуют паранормальные явления, многие из которых основаны на американских городских легендах и фольклоре, и сражаются с порождениями зла, такими как демоны и призраки.</td>
                        </tr>
                        <tr>
                            <td>tt017770357</td>
                            <td>Сериал</td>
                            <td><a href="">Игра престолов</a></td>
                            <td>фэнтези</td>
                            <td>2011</td>
                            <td>9.0</td>
                            <td>К концу подходит время благоденствия, и лето, длившееся почти десятилетие, угасает. Вокруг средоточия власти Семи королевств, Железного трона, зреет заговор, и в это непростое время король решает искать поддержки у друга юности Эддарда Старка.</td>
                        </tr>
                </tbody>
             </table>
        </div>
    </section>
</body>
</html>