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
        국가 : ${ reservationInfo.nationCode }<br>
        금액 : ${ reservationInfo.price }<br>
        환전 일자 : <input type="text"><br>
        <input type="submit" formmethod="post" formaction="" value="저장"/>
        <input type="submit" formmethod="get" formaction="/admin/update/reservation/${ reservationInfo.num }" value="수정"/>
        <input type="submit" formmethod="get" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제"/>
    </form>
</body>
</html>
