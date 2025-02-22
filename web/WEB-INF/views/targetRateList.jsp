<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오후 2:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="service.PaginationService" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목표 환율</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content table-content">
            <div class="btn-container ta-right">
                <a href="/insert/exchange/rate" class="btn btn-sm btn-outline-hana btn-hana">신규</a>
            </div>
            <table class="table ta-center taget-rate-table">
                <thead class="colored-thead">
                <tr>
                    <td>No.</td>
                    <td class="nation-column">국가</td>
                    <td>목표환율</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" begin="1" end="${fn:length(targetRates)}">
                    <tr>
                        <c:set var="dataPerPage" value="<%= PaginationService.DATA_PER_PAGE %>"/>
                        <td>${i + ((nowPageNum - 1) * dataPerPage)}</td>
                        <td class="nation-column"><a href="/exchange/rate/${targetRates[i - 1].nationCode}">${nationList[i - 1]}</a></td>
                        <td class="ta-right">
                            &#8361;<fmt:formatNumber value="${targetRates[i - 1].rate}" pattern="#,###.00"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination-container ta-center">
                <c:forEach var="pageNum" begin="1" end="${numberOfPages}">
                    <a href="/exchange/rates/${pageNum}">
                        <span class="pagination-item <c:if test="${nowPageNum eq pageNum}">selected</c:if>"></span>
                    </a>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>
