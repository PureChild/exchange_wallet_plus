<%--
  User: SeungsooLee
  Date: 2019-08-08
  Time: 오후 8:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메뉴</title>
</head>
<body>
    <nav class="menu-bar">
        <img class="logo" alt="로고이미지"/>
        <ul class="menu">
            <li>
                <a href="/admin/reservation/history/1" class="menu-item">신청 내역</a>
            </li>
            <li>
                <a href="/admin/lookup" class="menu-item">환전 코드 조회</a>
            </li>
            <c:if test="${admin eq 'admin'}">
                <li>
                    <a href="/admin/logout" class="menu-item btn-logout">로그아웃</a>
                </li>
            </c:if>
        </ul>
    </nav>
</body>
</html>
