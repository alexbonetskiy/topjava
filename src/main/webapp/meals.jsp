<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<style>

 table {
 border-collapse: collapse}

 td, th { border: 1px solid black;
       text-align: center;
      vertical-align: center;
       width: 200px;
      padding: 5px}

 th {font-weight: bold;
     font-size: larger;
     padding: 15px}

</style>


<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>


<c:set var="listOfMealTo" value="${requestScope.listOfMealTo}" />

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

<c:forEach items="${listOfMealTo}" var="mealTo">


        <tr style = "color: ${mealTo.excess ? "red" : "green"}">
            <td><fmt:parseDate value="${ mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></td>
            <td> <c:out value="${mealTo.description}"/> </td>
            <td> <c:out value="${mealTo.calories}"/> </td>
            <td></td>
            <td></td>
        </tr>
</c:forEach>
        </tbody>
    </table>


</body>
</html>