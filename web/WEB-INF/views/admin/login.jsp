<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 10:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자 로그인</title>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <p>${msg}</p>
    <form action="/admin/loginConfirm" method="post">
        <input type="text" name="admId"/>
        <input type="password" name="admPassword">
        <input type="submit" value="로그인">
    </form>
</body>
</html>
