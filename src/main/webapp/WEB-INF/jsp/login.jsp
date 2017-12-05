<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/loginStyle.css">
    <jsp:include page="fragments/link_header.jsp"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<c:if test="${not empty error}">
    <div class="alert alert-danger text-center" role="alert">
            <%--<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>--%>
        <span class="sr-only">Error:</span>
            ${error}
    </div>
</c:if>
<c:if test="${not empty logout}">
    <div class="alert alert-info text-center" role="alert">
        <span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
        <span class="sr-only">Logout:</span>
            ${logout}
    </div>
</c:if>
<c:url value="/login" var="loginUrl"/>
<div class="container">
    <form class="form-login" name="loginForm" action="${loginUrl}" method="post">
        <h2 class="form-login-heading">Sign in</h2>

        <label for="inputUsername" class="sr-only">Username</label>
        <input id="inputUsername" type="text" class="form-control" placeholder="Username" name="username" required>

        <label for="loginInputPassword" class="sr-only"></label>
        <input id="loginInputPassword" type="password" class="form-control" placeholder="Password" name="password"
               required>

        <button class="btn btn-lg btn-block btn-primary" type="submit" name="submit">Sign in</button>
        <input type="hidden"
               name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

</body>
</html>
