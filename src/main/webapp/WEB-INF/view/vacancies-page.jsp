<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: i.busygin
  Date: 02.02.2022
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Vacancies</title>
</head>
<body>
<h2 align="center">Вакансии</h2>
<table align="center">
    <tr>
        <th>Название</th>
        <th>Город</th>
        <th>Компания</th>
        <th>Зарплата</th>
        <th>Дата публикации</th>
    </tr>
    <c:forEach var="vacancy" items="${allVacancy}">
        <tr>
            <td><a href="${vacancy.url}">${vacancy.title}</a></td>
            <td>${vacancy.city}</td>
            <td>${vacancy.companyName}</td>
            <td>${vacancy.salary}</td>
            <td>${vacancy.date}</td>
        </tr>
    </c:forEach>
</table>
<%--<h2>Введенные данные</h2>
Ключевое слово: ${configToSearch.keyWords}
<br/>
Выбранные площадки:
<c:forEach var="strat" items="${configToSearch.strategies}">
    ${strat}
</c:forEach>--%>
</body>
</html>
