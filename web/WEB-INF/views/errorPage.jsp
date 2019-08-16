<%--
  User: SeungsooLee
  Date: 2019-08-16
  Time: 오후 1:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>!!Error!!</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main class="none-img">
        <div class="main-content">
            <img src="/image/problem.png" class="icon-warning">
            <c:choose>
                <c:when test="${errorMsg eq null}">
                    <p class="text-warning ta-center">죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
                </c:when>
                <c:otherwise>
                    <p class="text-warning ta-center">${errorMsg}</p>
                </c:otherwise>
            </c:choose>
            <p class="ta-center">문의 : 010-0000-0000</p>
            <a href="/" class="btn btn-sm btn-hana">메인으로</a>
        </div>
    </main>
</body>
</html>
