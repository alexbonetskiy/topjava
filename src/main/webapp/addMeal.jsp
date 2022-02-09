<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Add Meal</title>
</head>
<body>

<c:set var="updatedMeal" value="${requestScope.updatedMeal}"/>
<fmt:parseDate value="${ updatedMeal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
               type="both"/>

<form method="POST" action='meals' name="frmAddMeal">
    Date : <label>
    <input
        type="datetime-local" name="date"
        value="<fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${parsedDateTime}" />"/>
</label> <br/>
    Description : <label>
    <input
        type="text" name="description"
        value="<c:out value="${updatedMeal.description}" />">
</label> <br/>
    Calories : <label>
    <input
        type="text" name="calories"
        value="<c:out value="${updatedMeal.calories}" />"/>
</label> <br/> <input
        type="submit" value="Submit"/>
</form>


</body>
</html>
