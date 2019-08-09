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
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <img src="/image/flags/${ reservationInfo.nationCode }.png" alt="nation-flag"/>
        <form>
            국가 : ${ nation }
            금액 : ${ reservationInfo.price }
            <c:set var="targetDate" value="<%=new Date(new Date().getTime())%>"/>
            환전 일자 : <input type="date"
                                name="exchangeDate"
                                min="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"
                                max="${ reservationInfo.departureDate }">
            <input type="submit" formmethod="post" formaction="/admin/confirm/exchange/${ reservationInfo.num }" value="저장"/>
            <input type="submit" formmethod="get" formaction="/admin/update/reservation/${ reservationInfo.num }" value="수정"/>
            <input type="submit" formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제"/>
        </form>
    </main>
</body>
</html>
