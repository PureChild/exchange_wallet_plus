<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>신청 정보 수정</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <c:set var="targetDate" value="<%=new Date(new Date().getTime() - 60*60*24*1000)%>"/>
    <script id="api-nation" data-date="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyyMMdd"/>" data-nation="${ reservationInfo.nationCode }" src="/js/nation.js"></script>

    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <img id="nation-flag" src="/image/${ reservationInfo.nationCode }.png" alt="nation-flag"/>
        <form>
            <input type="hidden" name="reservationNum" value="${ reservationInfo.num }"><br/>
            국가 : <select id="nation" name="nationCode"></select>
            금액 : <input type="text" name="price" value="${ reservationInfo.price }"/><br/>

            <input type="submit" formmethod="post" formaction="/admin/update/reservation" value="수정">
            <input type="submit" formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제">
        </form>
    </main>
</body>
</html>
