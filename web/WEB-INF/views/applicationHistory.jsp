<%@ page import="java.util.Date" %><%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오전 10:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="service.PaginationService" %>
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
                <a href="/exchange/apply" class="btn btn-sm btn-outline-hana btn-hana">신규</a>
            </div>
            <table class="table ta-center resv-info-table">
                <thead class="colored-thead">
                    <tr>
                        <td>No.</td>
                        <td class="nation-column">국가</td>
                        <td class="progress-column">비고</td>
                        <td>여행 출발 일자</td>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="yesterday" value="<%=new Date(new Date().getTime() - 60*60*24*1000) %>" />
                    <c:forEach var="i" begin="1" end="${fn:length(applicationList)}">
                        <tr>
                            <c:set var="dataPerPage" value="<%= PaginationService.DATA_PER_PAGE %>"/>
                            <td>${i + ((nowPageNum - 1) * dataPerPage)}</td>
                            <td>${nationList[i - 1]}</td>
                            <td class="progress-column">
                            <c:choose>
                                <c:when test="${applicationList[i - 1].progress == 2}">
                                    ${progressList[i - 1]}
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${applicationList[i - 1].departureDate > yesterday}">
                                            <c:choose>
                                                <c:when test="${applicationList[i - 1].progress == 1}">
                                                            <a href="/application/result/${applicationList[i - 1].num}">${progressList[i - 1]}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    ${progressList[i - 1]}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-warning">기간 만료</span>
                                        </c:otherwise>
                                    </c:choose>
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
