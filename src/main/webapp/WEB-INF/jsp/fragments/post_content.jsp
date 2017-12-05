<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<form action="${pageContext.request.contextPath}/post/delete" id="deletePost" method="post">
    <input type="hidden" name="postId" value="${param.postId}">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<script>
    function deletePost() {
        document.getElementById("deletePost").submit();
    }
</script>

<div class="container-fluid post-container">

    <div class="row heading-row">
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name == param.user}">
                <p class="delete-post-sign"><a class="glyphicon glyphicon-remove-sign" href="javascript:deletePost()"></a></p>
            </c:when>
            <c:otherwise>
                <security:authorize access="hasRole('ADMIN')">
                    <p class="delete-post-sign"><a class="glyphicon glyphicon-remove-sign" href="javascript:deletePost()"></a></p>
                </security:authorize>
            </c:otherwise>
        </c:choose>

        <p class="date">Added: ${param.date}</p>
        <p class="author">By: <a href="/user/${param.user}">${param.user}</a></p>
    </div>
    <c:if test="${!fn:endsWith(requestScope['javax.servlet.forward.request_uri'], 'post/' += param.postId)}">
        <a href="/post/${param.postId}">
    </c:if>
        <div class="row">
            <c:choose>
                <c:when test="${not empty param.image}">
                    <img class="display-image" src="${param.image}">
                    <label class="message-view-with-image">${param.text}</label>
                </c:when>
                <c:otherwise>
                    <label class="message-view">${param.text}</label>
                </c:otherwise>
            </c:choose>
        </div>
    <c:if test="${!fn:endsWith(requestScope['javax.servlet.forward.request_uri'], param.postId)}">
        </a>
    </c:if>
</div>
