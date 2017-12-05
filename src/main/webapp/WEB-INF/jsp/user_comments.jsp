<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/messageAddingStyle.css">
    <jsp:include page="fragments/link_header.jsp"/>
</head>
<body>

<jsp:include page="fragments/header.jsp"/>


    <c:forEach items="${comments}" var="comment">

        <jsp:include page="fragments/post_content.jsp">
            <jsp:param name="date" value="${comment.post.message.formattedDate}"/>
            <jsp:param name="user" value="${comment.post.postAuthor.username}"/>
            <jsp:param name="image" value="${comment.post.message.imgUrl}"/>
            <jsp:param name="text" value="${comment.post.message.text}"/>
            <jsp:param name="postId" value="${comment.post.id}"/>
        </jsp:include>

        <jsp:include page="fragments/comment_content.jsp">
            <jsp:param name="date" value="${comment.message.formattedDate}"/>
            <jsp:param name="user" value="${comment.commentAuthor.username}"/>
            <jsp:param name="image" value="${comment.message.imgUrl}"/>
            <jsp:param name="text" value="${comment.message.text}"/>
            <jsp:param name="commentId" value="${comment.id}"/>
        </jsp:include>
    </c:forEach>

    <jsp:include page="fragments/pagination.jsp">
        <jsp:param name="pagesNumber" value="${pagesNumber}"/>
    </jsp:include>




</body>
</html>
