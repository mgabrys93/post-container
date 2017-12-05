<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
    <script src="../../resources/js/myScripts.js"></script>
    <link rel="stylesheet" type="text/css" href="../../resources/css/messageAddingStyle.css">
</head>
<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="container">
        <form:form class="message-form" modelAttribute="commentForm" method="post">

            <form:errors path="message.text" class="alert alert-danger center-block text-center" role="alert"/>
            <label for="commentTextArea" class="sr-only">Comment text</label>
            <form:textarea id="commentTextArea" class="form-control" path="message.text" placeholder="Please write your comment..."/>
            <div id="image">

            </div>
            <button type="button" class="btn btn-lg btn-secondary add-image-button" onclick="imageLoad()">Add Image</button>

            <form:button class="btn btn-lg btn-primary submit-button" type="submit">Add Comment</form:button>
            <form:hidden id="imgSrc" path="message.imgUrl"/>
        </form:form>
    </div>

    <hr>

    <jsp:include page="fragments/post_content.jsp">
        <jsp:param name="date" value="${post.message.formattedDate}"/>
        <jsp:param name="user" value="${post.postAuthor.username}"/>
        <jsp:param name="image" value="${post.message.imgUrl}"/>
        <jsp:param name="text" value="${post.message.text}"/>
        <jsp:param name="postId" value="${post.id}"/>
    </jsp:include>


    <c:forEach items="${post.comments}" var="comment">
        <jsp:include page="fragments/comment_content.jsp">
            <jsp:param name="date" value="${comment.message.formattedDate}"/>
            <jsp:param name="user" value="${comment.commentAuthor.username}"/>
            <jsp:param name="image" value="${comment.message.imgUrl}"/>
            <jsp:param name="text" value="${comment.message.text}"/>
            <jsp:param name="commentId" value="${comment.id}"/>
        </jsp:include>
        
    </c:forEach>





</body>
</html>
