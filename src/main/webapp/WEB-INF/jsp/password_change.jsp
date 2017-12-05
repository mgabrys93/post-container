<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
    <link rel="stylesheet" href="../../resources/css/loginStyle.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container-fluid">
        <form:form class="form-login" modelAttribute="passwordChange" method="post">
            <form:errors path="oldPassword" class="alert alert-danger text-center" role="alert"/>
            <form:input path="oldPassword" class="form-control" type="password" placeholder="Old Password..."/>

            <form:errors path="newPassword" class="alert alert-danger text-center" role="alert"/>
            <form:input path="newPassword" class="form-control" type="password" placeholder="New Password..."/>

            <form:errors path="repeatNewPassword" class="alert alert-danger text-center" role="alert"/>
            <form:input path="repeatNewPassword" class="form-control" type="password" placeholder="Repeat New Password..."/>

            <form:button class="btn btn-lg btn-block btn-primary" type="submit">Change</form:button>
        </form:form>
    </div>


</body>
</html>
