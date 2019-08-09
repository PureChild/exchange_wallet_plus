<%--
  User: SeungsooLee
  Date: 2019-08-08
  Time: 오후 9:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <%-- jstl 데이터 타입 확인 중!! --%>
    <c:if test="${not empty result}">
        <c:choose>
            <c:when test="${result.getClass().simpleName == 'String'}">
                ${result}
                <a href="/admin/reservation/history">목록으로</a>
            </c:when>
            <c:otherwise>
                신청인 : ${result.applicant}
                국가 : ${result.nationCode}
                금액 : ${result.price}
                <input type="button" value="환전 완료"/>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
