<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="fragments/link_header.jsp"/>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container-fluid">
        <div class="jumbotron">
            <h1 class="text-center"> Sorry user : <u>${username}</u> not exist :(</h1>
        </div>
    </div>

</body>
</html>
