<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>신청 정보</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="info-form">
            <img src="/image/flags/${ reservationInfo.nationCode }.png" class="flag" alt="nation-flag"/>
            <table class="table table-sm ta-center">
                <tr>
                    <td>국가</td>
                    <td>${ nation }</td>
                </tr>
                <tr>
                    <td>금액</td>
                    <td>${ reservationInfo.price }</td>
                </tr>
                <tr>
                    <td>환전 일자</td>
                    <td>
                        <c:set var="targetDate" value="<%=new Date(new Date().getTime())%>"/>
                        <input type="date" class="form-control ta-center input-sm"
                               name="exchangeDate"
                               min="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"
                               max="${ reservationInfo.departureDate }">
                    </td>
                </tr>
            </table>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="post" formaction="/admin/confirm/exchange/${ reservationInfo.num }" value="저장"/>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="get" formaction="/admin/update/reservation/${ reservationInfo.num }" value="수정"/>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제"/>
        </form>
    </main>
</body>
</html>
