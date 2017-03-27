<%--
  Created by IntelliJ IDEA.
  User: caesar-84
  Date: 3/26/17
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2><a href="<c:url value="users"/>">Users</a></h2>
<h2>Meal list</h2>
<jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"/>

<a href="meals?action=createOrUpdate&mealId=0">Insert new meal</a>
<br/>

<table>
    <tr bgcolor="#87cefa">
        <th width="150">Дата</th>
        <th width="350">Описание</th>
        <th width="80">Калории</th>
        <th width="75">Удалить</th>
        <th width="80">Редактировать</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <tr bgcolor="#f0f8ff" style="color: ${meal.exceed ? "red" : "black"}">
            <td>${meal.dateTimeString}</td>
            <td>${meal.description}</td>
            <td align="center">${meal.calories}</td>
            <td><a href="<c:url value="meals?action=delete&mealId=${meal.id}"/>">Удалить</a></td>
            <td><a href="<c:url value="meals?action=createOrUpdate&mealId=${meal.id}"/>">Редактировать</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>