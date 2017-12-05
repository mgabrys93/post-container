<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/loginStyle.css">
    <jsp:include page="fragments/link_header.jsp"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container">

        <form:form class="form-login" modelAttribute="userForm" method="post">
            <h2 class="form-login-heading">Create Account</h2>
            <form:errors path="username" class="alert alert-danger text-center" role="alert"/>

            <label for="inputUsername" class="sr-only">Username</label>
            <form:input id="inputUsername" path="username" type="text" class="form-control" placeholder="Username"/>

            <form:errors path="password" class="alert alert-danger text-center" role="alert"/>

            <label for="registrationInputPassword" class="sr-only">Password</label>
            <form:input id="registrationInputPassword" path="password" type="password" class="form-control" placeholder="Password"/>

            <label for="confirmPassword" class="sr-only">Confirm password</label>
            <form:input id="confirmPassword" path="confirmPassword" type="password" class="form-control" placeholder="Confirm Password"/>

            <form:button class="btn btn-lg btn-block btn-primary" type="submit">Sign up</form:button>
        </form:form>

    </div>

</body>
</html>
