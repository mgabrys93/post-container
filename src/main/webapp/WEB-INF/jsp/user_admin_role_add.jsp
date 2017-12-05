<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/loginStyle.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <form class="form-login" action="/admin/user/role/addAdmin" method="post">
        <c:if test="${not empty usernameNotExist}">
            <p class="alert alert-danger text-center">${usernameNotExist}</p>
        </c:if>
        <h2>Set Admin Role</h2>
        <input class="form-control" list="users" placeholder="Search user..." name="username">
        <datalist id="users">
            <c:forEach var="user" items="${usernameList}">
                <option value="${user}">
            </c:forEach>
        </datalist>

        <button class="btn btn-lg btn-block btn-primary" type="submit">Add</button>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>

</body>
</html>
