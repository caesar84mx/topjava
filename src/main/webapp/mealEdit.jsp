<%--
  Created by IntelliJ IDEA.
  User: caesar-84
  Date: 3/27/17
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit/Add Meal</title>
</head>
<body>
<h2>Edit/Add Meal</h2>
</body>
<%--<jsp:useBean id="editedMeal" class="ru.javawebinar.topjava.model.Meal" scope="request"/>--%>
<form method="post">
    <input type="hidden" name="id" value="${editedMeal.id}">
    <table>
        <tr>
            <td>Дата и время:</td>
            <td><input type="datetime-local" name="dateTime" value="${editedMeal.dateTime}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type="text" name="description" value="${editedMeal.description}"></td>
        </tr>
        <tr>
            <td>Калорийность:</td>
            <td><input type="number" name="calories" value="${editedMeal.calories}"></td>
        </tr>
        <tr>
            <td colspan="1"><input type="submit" value="Отправить"></td>
        </tr>
    </table>
</form>
</html>
