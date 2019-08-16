<%--
  User: SeungsooLee
  Date: 2019-08-08
  Time: 오후 9:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>환전 코드 조회</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/lookup.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form id="lookupForm" class="lookup-form" method="get">
            <p>환전 코드를 입력해주세요</p>
            <input type="text" class="form-control" id="exchangeCode"/>

            <%-- jstl 데이터 타입 확인 중!! --%>
            <c:if test="${not empty result}">
                <c:choose>
                    <c:when test="${result.getClass().simpleName == 'ReservationInfo'}">
                        <input type="hidden" id="reservationNum" value="${result.num}">
                        <table class="table table-sm ta-center">
                            <tr>
                                <td>신청인</td>
                                <td>${result.applicant}</td>
                            </tr>
                            <tr>
                                <td>국가</td>
                                <td>${nation}</td>
                            </tr>
                            <tr>
                                <td>금액</td>
                                <td>${result.price}</td>
                            </tr>
                        </table>
                        <input type="button" id="btnExchange" class="btn btn-hana" value="환전 완료"/>
                    </c:when>
                    <c:otherwise>
                        <p class="text-warning">해당 결과가 존재하지 않습니다.</p>
                        <a href="/admin/reservation/history/1" class="btn btn-hana">목록으로</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </form>
    </main>
</body>
</html>
