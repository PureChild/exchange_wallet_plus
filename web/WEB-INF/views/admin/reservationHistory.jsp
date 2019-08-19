<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="service.PaginationService" %>
<% request.setCharacterEncoding("UTF-8");%>
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
            <table class="table ta-center admin-table">
                <thead class="colored-thead">
                    <tr>
                        <td>No.</td>
                        <td>신청인</td>
                        <td>국가</td>
                        <td>금액</td>
                        <td>마감일자</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="i" begin="1" end="${fn:length(reservationList)}">
                    <tr>
                        <c:set var="dataPerPage" value="<%= PaginationService.DATA_PER_PAGE %>"/>
                        <td class="no-column">${i + ((nowPageNum - 1) * dataPerPage)}</td>
                        <td class="name-column">
                            <a href="/admin/reservation/${reservationList[i - 1].num}">${customerList[i - 1].name}</a>
                        </td>
                        <td class="nation-column">${nationList[i - 1]}</td>
                        <td class="ta-right">
                            <fmt:formatNumber value="${reservationList[i - 1].price}" type="currency" />
                        </td>
                        <td class="date-column">${reservationList[i - 1].departureDate}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination-container ta-center">
                <c:forEach var="pageNum" begin="1" end="${numberOfPages}">
                    <a href="/admin/reservation/history/${pageNum}">
                        <span class="pagination-item <c:if test="${nowPageNum eq pageNum}">selected</c:if>"></span>
                    </a>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>
