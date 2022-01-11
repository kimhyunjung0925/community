<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>회원가입</h1>
<form action="/user/join" method="post" id="join-frm" >
    <div><lable>id: <input type="text" name="uid" required ></lable></div>
    <div><input type="button" value="아이디 중복 체크" id="id-btn-chk"><span id="id-chk-msg"></span></div>
    <div><lable>pw: <input type="password" name="upw" required></lable></div>
    <div><lable>pw-check: <input type="password" id="upw-chk" required></lable></div>
    <div><lable>name: <input type="text" name="nm" required></lable></div>
    <div>
        <label>female <input type="radio" name="gender" value="2" checked></label>
        <label>male <input type="radio" name="gender" value="1"></label>
    </div>
    <div>
        <input type="submit" value="JOIN">
        <input type="reset" value="RESET">
    </div>
</form>

<!-- id로 하는 이유는 값이 날아가지 말라고? -->