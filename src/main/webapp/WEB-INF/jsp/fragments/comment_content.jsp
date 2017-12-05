<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form id="deleteComment" action="${pageContext.request.contextPath}/post/comment/delete" method="post">
    <input hidden name="commentId" value="${param.commentId}">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<script>
    function deleteComment() {
        document.getElementById("deleteComment").submit();
    }

</script>

<div class="container-fluid comment-container">
    <div class="row heading-row">
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name == param.user}">
                <p class="delete-post-sign"><a class="glyphicon glyphicon-remove-sign" href="javascript:deleteComment()"></a></p>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasRole('ADMIN')">
                    <p class="delete-post-sign"><a class="glyphicon glyphicon-remove-sign" href="javascript:deleteComment()"></a></p>
                </sec:authorize>
            </c:otherwise>
        </c:choose>

        <p class="date">Added: ${param.date}</p>
        <p class="author">By: <a href="/user/${param.user}">${param.user}</a></p>
    </div>
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
</div>