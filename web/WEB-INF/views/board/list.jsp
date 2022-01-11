<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <h1 class="center">보드 리스트</h1>
    <c:if test="${sessionScope.loginUser != null}">
        <div class="centerdt">
        <a href="/board/write?icategory=${requestScope.icategory}"><input type="button" value="글쓰기"></a>
        </div>
    </c:if>
    <div class="center">
        <c:choose>
            <c:when test="${fn:length(requestScope.list) == 0}">
                글이 없습니다.
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>no</th>
                        <th>title</th>
                        <th>hits</th>
                        <th>writer</th>
                        <th>reg date</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="item">
                        <tr class="record" data-iboard="${item.iboard}" >
                            <td>${item.iboard}</td>
                            <td><c:out value="${item.title}"/></td>
                            <td>${item.hits}</td>
                            <td>${item.writernm}</td>
                            <td>${item.rdt}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script src="/res/js/board/list.js"></script>