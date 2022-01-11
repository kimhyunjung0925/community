<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="submenu-section">
    <div class="p10">
        <ul class="bckcolor">
            <li><a href="">프로필</a> </li>
            <li><a href="">비밀번호 변경</a> </li>
        </ul>
    </div>
    <div class="p10">
        <tiles:insertAttribute name="content"/>
    </div>
</div>