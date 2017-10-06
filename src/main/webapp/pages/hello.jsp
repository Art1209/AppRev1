<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %><html>
<html>
<head>
    <title>Basic Hello page</title>
</head>
<body>
<h1>Hello /></h1>
<div>
    <div style="margin-top: 20px;">
        <h2>Тестовый сервис RS5</h2>
        <sec:authorize access="!isAuthenticated()">
            <p><a href="<c:url value="/login" />">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p> <a href="<c:url value="/logout" />">Выйти</a></p>
        </sec:authorize>
    </div>
</div>
</body>
</html>
