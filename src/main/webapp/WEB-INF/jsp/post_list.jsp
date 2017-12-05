<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
    <link rel="stylesheet" type="text/css" href="../../resources/css/messageAddingStyle.css">
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div>
        <c:forEach items="${postList}" var="post">
            <jsp:include page="fragments/post_content.jsp">
                <jsp:param name="date" value="${post.message.formattedDate}"/>
                <jsp:param name="user" value="${post.postAuthor.username}"/>
                <jsp:param name="image" value="${post.message.imgUrl}"/>
                <jsp:param name="text" value="${post.message.text}"/>
                <jsp:param name="postId" value="${post.id}"/>
            </jsp:include>
        </c:forEach>
    </div>


    <jsp:include page="fragments/pagination.jsp">
        <jsp:param name="pagesNumber" value="${pagesNumber}"/>
    </jsp:include>
</body>
</html>
