<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
    <link rel="stylesheet" type="text/css" href="../../resources/css/messageAddingStyle.css">
    <script src="../../resources/js/myScripts.js"></script>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container">

        <form:form class="message-form" modelAttribute="postForm" method="post">
            <h2 class="message-form-heading">Create Post</h2>
            <form:errors class="alert alert-danger center-block text-center" role="alert" path="message.text"/>

            <label for="inputPostText" class="sr-only">Text input</label>
            <form:textarea id="inputPostText" class="form-control" path="message.text"/>
            <div id="image">

            </div>
            <form:hidden id="imgSrc" path="message.imgUrl"></form:hidden>

            <button class="btn btn-lg add-image-button" type="button" onclick="imageLoad()">Add Image</button>
            <form:button class="btn btn-lg btn-primary submit-button" type="submit">Create</form:button>

        </form:form>

    </div>


</body>
</html>
