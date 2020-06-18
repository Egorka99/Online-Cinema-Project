<!DOCTYPE html>
 <html lang="en">
 <head>
     <%@ page contentType="text/html;charset=utf-8" %>
     <%@ page isELIgnored="false" %>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Админ-Панель</title>
     <link rel="stylesheet" href="style.css">
     <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.png" type="image/png">
 </head>
 <body>
     <div class="container">
     <header class="header">
             <h1 class="header-title">Библиотека</h1>
             <ul class="header-list">
                 <li><a href="books">Книги</a></li>
                 <li><a href="authors">Авторы</a></li>
                 <li><a href="bookmarks">Закладки</a></li>
                 <li><a href="admin">Администрирование</a></li>
             </ul>
             <div class="header-profile">
                 <img src="images/admin.png" alt="">
                 <span>Привет, ${user.getNickname()}</span>
             </div>
             <button><a href="<%=request.getContextPath()%>/logout">Выход</a></button>
     </header>
     <section class="control">
         <div class="container">
             <div class="control-buttons__operation">
                 <h2>Действия:</h2>
                 <button><a href="<%=request.getContextPath()%>/handlers/registrationNewUser.jsp">Зарегистрировать нового пользователя</a></button>
                 <button><a href="<%=request.getContextPath()%>/handlers/blockingUser.jsp">Заблокировать пользователя</a></button>
                 <button><a href="<%=request.getContextPath()%>/search/SearchUserHistory.jsp">Смотреть историю действий пользователя</a></button>
             </div>
         </div>
     </section>
     </body>
 </body>
 </html>