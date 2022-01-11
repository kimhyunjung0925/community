<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${sessionScope.loginUser.iuser == data.iuser}">
    <div class="detaildt">
        <button id="modBtn">수정</button>
        <button id="delBtn">삭제</button>
    </div>
</c:if>

<div id="data" data-icategory="${data.icategory}" data-iboard="${data.iboard}"></div>

<div class="detaildt">
    <div>카테고리 : ${data.categorynm}</div>
    <hr>
    <div>조회수: ${data.hits}</div>
    <div>글쓴이: ${data.writernm}</div>
    <div>제목: <c:out value="${data.title}" /></div>
    <div>등록일시: ${data.rdt}</div>
    <hr>
    <div>========내용========</div>
    <div><c:out value="${data.ctnt}" /></div>
</div>