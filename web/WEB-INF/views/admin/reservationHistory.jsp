<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
            <table class="table ta-center">
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
                        <td>${reservationList[i - 1].num}</td>
                        <td><a href="/admin/reservation/${reservationList[i - 1].num}">${reservationList[i - 1].applicant}</a></td>
                        <td>${nationList[i - 1]}</td>
                        <td>${reservationList[i - 1].price}</td>
                        <td>${reservationList[i - 1].departureDate}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
