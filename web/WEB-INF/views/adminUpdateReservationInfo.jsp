<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>신청 내역</title>
</head>
<body>
    <img src="/image/${ reservationInfo.nationCode }.png" alt="nation-flag"/>
    <form>
        <input type="hidden" name="reservationNum" value="${ reservationInfo.num }"><br/>
        국가 : <input type="text" name="nationCode" value="${ reservationInfo.nationCode }"/><br/>
        금액 : <input type="text" name="price" value="${ reservationInfo.price }"/><br/>

        <input type="submit" formmethod="post" formaction="/admin/update/reservation" value="수정">
        <input type="submit" formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제">
    </form>
</body>
</html>
