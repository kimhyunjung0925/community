<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:importAttribute name="menuList"/>

<c:set var="currentPagePath" value="${requestScope['javax.servlet.forward.request_uri']}"/> <!--저 var에 uri 넣음 -->
<c:set var="lastPath" value="0"/> <!--정수라서 에러터짐? 그래서 추가해줌-->
<c:if test="${fn:contains(currentPagePath,'/board/list')}"> <!--주소값에 list 가 있을 경우만 실행 아니면 value는 0-->
    <c:set var="splitURI" value="${fn:split(currentPagePath,'/')}"/> <!-- /이걸 기준으로 문자열을 자르자 -->
    <c:set var="lastPath" value="${splitURI[fn:length(splitURI)-1]}"/> <!-- 마지막을 뜻함(0부터 시작하기때문에 길이 -1 은 무조건 마지막칸) -->
</c:if>

<header class="h50">
    <div class="flex-container flex-align-center p-lr-20">
        <div class="list">
            <c:forEach items="${menuList}" var="item">
                <!-- ''.concat(item.icategory)로도 해결 가능! -->
                <div class="m-r-20 ${lastPath == item.icategory ? 'menu-selected' : ''}"> <!-- 클릭하면 클래스이름 menu-selected 안하면 빈칸 -->
                    <a href="/board/list/${item.icategory}" class="font-color-white">${item.nm}</a></div>
            </c:forEach>
        </div>
        <div class="my">
        <c:choose>
            <c:when test="${sessionScope.loginUser == null}">
                <div class="m-r-20"><a href="/user/login" class="font-color-white">로그인</a> </div>
            </c:when>
            <c:otherwise>
                <div class="m-r-20"><a href="/user/mypage/profile" class="font-color-white">마이페이지</a> </div>
                <div class="m-r-20"><a href="/user/logout" class="font-color-white">로그아웃</a> </div>
            </c:otherwise>
        </c:choose>

        </div>
    </div>
</header>
