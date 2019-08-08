<%--
  User: SeungsooLee
  Date: 2019-08-08
  Time: 오후 9:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>환전 코드 조회</title>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <form method="get">
        <p>환전 코드를 입력해주세요</p>
        <input type="text" id="exchangeCode"/>
    </form>
</body>
</html>
