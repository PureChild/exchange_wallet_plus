<%--
  User: SeungsooLee
  Date: 2019-08-08
  Time: 오후 9:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>환전 코드 조회</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="/js/lookup.js"></script>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <form id="lookupForm" method="get">
        <p>환전 코드를 입력해주세요</p>
        <input type="text" id="exchangeCode"/>
    </form>

    ${result}
</body>
</html>
