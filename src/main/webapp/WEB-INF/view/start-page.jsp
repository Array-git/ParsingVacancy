<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
form#configToSearch {
align-content: center;
align-items: center;
object-position: center;
}

div {

justify-content: center;
}
</style>
<%--
  Created by IntelliJ IDEA.
  User: i.busygin
  Date: 02.02.2022
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Start Page</title>
</head>
<body>
<h2 align="center">Укажите какие вакансии вы хотите найти</h2>
<div style="justify-content: center">
    <form id="configToSearch" action="showVacancies" method="get">
        <div style="display: flex">
            <div style="width: 260px">Введите ключевые слова для поиска:</div>
            <div style="width: 200px"><input id="keyWords" name="keyWords" type="text" value=""></div>
        </div>
        <div style="display: flex">
            <div style="width: 260px">Введите город для поиска:</div>
            <div style="width: 200px"><input id="city" name="city" type="text" value=""></div>
        </div>
        <div style="display: flex">
            <div style="width: 460px; text-align: center">Выберите площадки для поиска вакансий:</div>
        </div>
        <div style="display: flex">
            <div style="width: 460px; text-align: center">
                <span><input id="strategies1" name="strategies" type="checkbox" value="career.habr.com"><label for="strategies1">Хабр Карьера</label></span><span><input id="strategies2" name="strategies" type="checkbox" value="hh.ru"><label for="strategies2">Head Hunter</label></span><input type="hidden" name="_strategies" value="on">
            </div>
        </div>
        <div style="display: flex">
            <input style="width: 460px; text-align: center" type="submit" value="OK">
        </div>
    </form>
</div>
</body>
</html>
