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
    <script src="/js/jquery-3.4.1.min.js"></script>
    <c:set var="targetDate" value="<%=new Date(new Date().getTime() - 60*60*24*1000)%>"/>
    <script id="api-nation" data-date="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyyMMdd"/>" data-nation="${ reservationInfo.nationCode }" src="/js/nation.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="info-form">
            <img id="nation-flag" src="/image/flags/${ reservationInfo.nationCode }.png" class="flag" alt="nation-flag"/>
            <input type="hidden" name="reservationNum" value="${ reservationInfo.num }"><br/>
            <table class="table table-sm ta-center">
                <tr>
                    <td>국가</td>
                    <td><select id="nation" class="form-control ta-center input-sm" name="nationCode"></select></td>
                </tr>
                <tr>
                    <td>금액</td>
                    <td><input type="text" class="form-control ta-center input-sm" name="price" value="${ reservationInfo.price }"/></td>
                </tr>
            </table>

            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="post" formaction="/admin/update/reservation" value="수정">
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제">
        </form>
    </main>
</body>
</html>
