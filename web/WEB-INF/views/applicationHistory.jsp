<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오전 10:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신청 내역</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content table-content">
            <div class="btn-container ta-right">
                <a href="/exchange/apply" class="btn btn-sm btn-hana">신규</a>
            </div>
            <table class="table ta-center">
                <thead class="colored-thead">
                    <tr>
                        <td>No.</td>
                        <td>국가</td>
                        <td>비고</td>
                        <td>여행 출발 일자</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" begin="1" end="${fn:length(applicationList)}">
                        <tr>
                            <td>${i}</td>
                            <td>${nationList[i - 1]}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${applicationList[i - 1].progress == 1}">
                                        <a href="/application/result/${applicationList[i - 1].nationCode}">${progressList[i - 1]}</a>
                                    </c:when>
                                    <c:otherwise>
                                        ${progressList[i - 1]}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${applicationList[i - 1].departureDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination-container ta-center">
                <c:forEach var="pageNum" begin="1" end="${numberOfPages}">
                    <a href="/application/history/${pageNum}">
                        <span class="pagination-item <c:if test="${nowPageNum eq pageNum}">selected</c:if>"></span>
                    </a>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>
