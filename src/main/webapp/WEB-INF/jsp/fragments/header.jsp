<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<script src="../../../resources/js/myScripts.js"></script>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }

    $(document).ready(function () {
        userAutocomplete("${pageContext.request.contextPath}", "user-search");
    });
</script>

<nav style="z-index: 1" class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#my-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="my-navbar-collapse">
            <ul class="nav navbar-nav" >
                <li><a href="${pageContext.request.contextPath}/post/list">Home</a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="${pageContext.request.contextPath}/post/create">Create Post</a></li>
                    <li>
                        <div class="navbar-form header-form">
                            <form action="${pageContext.request.contextPath}/user/find" method="post">
                                <div class="form-group ui-widget">
                                    <input id="user-search" type="search" class="form-control" placeholder="Search user..." name="username">
                                </div>
                                <button type="submit" class="btn btn-default">Search</button>
                                <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
                            </form>
                        </div>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Account<span class="caret"></span></a>
                        <c:set var="userPath" scope="session" value="${pageContext.request.contextPath}/user"/>
                        <c:set var="myAccountPath" scope="session" value="${userPath}/${pageContext.request.userPrincipal.name}"/>
                        <ul class="dropdown-menu">
                            <li><a href="${myAccountPath}">My Account</a></li>
                            <li><a href="${myAccountPath}/posts">My Posts</a></li>
                            <li><a href="${myAccountPath}/comments">My Comments</a></li>
                            <li><a href="${userPath}/password/change">Change Password</a></li>
                        </ul>
                    </li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin Panel<span class="caret"></span></a>
                            <c:set var="adminPath" scope="session" value="${pageContext.request.contextPath}/admin/user"/>
                            <ul class="dropdown-menu">
                                <li><a href="${adminPath}/role/addAdmin">Add Admin Role</a></li>
                                <li><a href="${adminPath}/role/deleteAdmin">Delete Admin Role</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                </c:if>
            </ul>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="javascript:formSubmit()">Logout</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/registration">Sing up</a></li>
                        <li><a href="${pageContext.request.contextPath}/login">Sign in</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</nav>
