<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="text-center">
    <ul class="pagination pagination-lg">
        <c:if test="${param.page != null && param.page > 1}">
            <li>
                <a href="${requestScope['javax.servlet.forward.request_uri']}?page=${param.page - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach items="${pagesInterval}" var="pageNum">
            <c:if test="${param.page == null}">
                <c:choose>
                    <c:when test="${pageNum == 1}">
                        <li class="active"><a href="${requestScope['javax.servlet.forward.request_uri']}?page=1">1</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?page=${pageNum}">${pageNum}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:if test="${param.page != null}">
                <c:choose>
                    <c:when test="${pageNum == param.page}">
                        <li class="active"><a href="${requestScope['javax.servlet.forward.request_uri']}?page=${pageNum}">${pageNum}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?page=${pageNum}">${pageNum}</a></li>
                    </c:otherwise>
                </c:choose>

            </c:if>
        </c:forEach>

        <c:if test="${empty param.page && param.pagesNumber > 1}">
            <li>
                <a href="${requestScope['javax.servlet.forward.request_uri']}?page=2" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${param.page != null && param.page < param.pagesNumber}">

            <li>
                <a href="${requestScope['javax.servlet.forward.request_uri']}?page=${param.page + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

    </ul>

</div>