<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container">
        <div class="jumbotron text-center">
            <h4>Username: ${user.username}</h4>
            <h4>Posts: <a href="${requestScope['javax.servlet.forward.request_uri']}/posts">Posts(${user.posts.size()})</a></h4>
            <h4>Comments: <a href="${requestScope['javax.servlet.forward.request_uri']}/comments">Comments(${user.comments.size()})</a></h4>
        </div>


    </div>

</body>
</html>
