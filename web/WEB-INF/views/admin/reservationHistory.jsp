<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>신청 내역</title>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <table>
        <thead>
            <tr>
                <td>No.</td>
                <td>신청인</td>
                <td>금액</td>
                <td>마감일자</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="reservation" items="${reservationList}">
            <tr>
                <td>${reservation.num}</td>
                <td><a href="/admin/reservation/${reservation.num}">${reservation.applicant}</a></td>
                <td>${reservation.price}</td>
                <td>${reservation.departureDate}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
