<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
div {
display: flex;
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
<div>
    <form:form action="showVacancies" modelAttribute="configToSearch" method="get">
        <div>
            <div style="width: 260px">Введите ключевые слова для поиска:</div>
            <div style="width: 200px"><form:input path="keyWords"/></div>
        </div>
        <div>
        <form:errors style="color: red;" id="error" path="keyWords"/>
        </div>
        <div>
            <div style="width: 260px">Введите город для поиска:</div>
            <div style="width: 200px"><form:input path="city"/></div>
        </div>
        <div>
            <div style="width: 460px">Выберите площадки для поиска вакансий:</div>
        </div>
        <div>
            <div style="width: 460px; text-align: center">
                <form:checkboxes path="strategies" items="${configToSearch.strategiesList}"/>
            </div>
        </div>
        <div>
            <form:errors style="color: red;" id="error" path="strategies"/>
        </div>
        <div>
        <input style="width: 460px" type="submit" value="OK">
        </div>
    </form:form>
</div>
</body>
</html>
