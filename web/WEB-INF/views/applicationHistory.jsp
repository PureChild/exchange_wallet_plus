<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오전 10:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>신청 내역</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content table-content">
            <div class="ta-right">
                <a href="/exchange/apply" class="btn btn-sm btn-hana">신규</a>
            </div>
            <table class="table ta-center">
                <thead class="colored-thead">
                    <tr>
                        <td>No.</td>
                        <td>국가</td>
                        <td>비고</td>
                        <td>신청일자</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>테스트</td>
                        <td><a href="#">테스트</a></td>
                        <td>테스트</td>
                        <td>테스트</td>
                    </tr>
                <%--<c:forEach var="reservation" items="${reservationList}">--%>
                    <%--<tr>--%>
                        <%--<td>${reservation.num}</td>--%>
                        <%--<td><a href="#">${reservation.applicant}</a></td>--%>
                        <%--<td>${reservation.price}</td>--%>
                        <%--<td>${reservation.departureDate}</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
