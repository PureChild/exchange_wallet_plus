<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오후 2:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>목표 환율</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content table-content">
            <div class="ta-right">
                <a href="/insert/exchange/rate" class="btn btn-sm btn-hana">신규</a>
            </div>
            <table class="table ta-center">
                <thead class="colored-thead">
                <tr>
                    <td>No.</td>
                    <td>국가</td>
                    <td>목표환율</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" begin="1" end="${fn:length(targetRates)}">
                    <tr>
                        <td>${i}</td>
                        <td><a href="/exchange/rate/${targetRates[i - 1].nationCode}">${nationList[i - 1]}</a></td>
                        <td>${targetRates[i - 1].rate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
