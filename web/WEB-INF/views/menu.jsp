<%--
  User: SeungsooLee
  Date: 2019-08-11
  Time: 오후 5:59
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
        <a href="/"><img class="logo" alt="로고이미지"/></a>
        <ul class="menu">
            <li>
                <a href="/exchange/info" class="menu-item">환율 정보</a>
            </li>
            <li>
                <a href="/exchange/apply" class="menu-item">환전 예약 신청</a>
            </li>
            <li>
                <a href="/application/history/1" class="menu-item">신청 내역</a>
            </li>
            <li>
                <a href="/exchange/rates" class="menu-item">목표 환율 설정</a>
            </li>
            <c:if test="${loginUser ne null}">
                <li>
                    <a href="/logout" class="menu-item btn-logout">로그아웃</a>
                </li>
            </c:if>
        </ul>
    </nav>
</body>
</html>
