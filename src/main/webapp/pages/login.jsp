<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2 >Пожалуйста, авторизуйтесь</h2>
        <input type="text" name="j_username" placeholder="Email address" >
        <input type="password" name="j_password" placeholder="Password" >
        <button type="submit">Войти</button>
    </form>
</div>

</body>
</html>
