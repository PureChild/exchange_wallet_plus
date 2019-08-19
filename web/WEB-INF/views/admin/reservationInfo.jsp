<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 11:29
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.Date, java.util.concurrent.ThreadLocalRandom, java.sql.Timestamp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신청 정보</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <%-- 구글 차트 --%>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="/js/chart.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main class="none-img">
        <form class="info-form chart-form">
            <input type="hidden" id="nationCode" value="${ reservationInfo.nationCode }">
            <img src="/image/flags/${ reservationInfo.nationCode }.png" class="flag" alt="nation-flag"/>

            <div id="Line_Controls_Chart" class="chart-container">
                <div id="msgBeforeDrawChart" class="loading-msg-container">
                    <img src="/image/loader.gif" class="img-loader" alt="로딩이미지"/>
                    <p class="text-warning">환율 정보를 불러오는 중입니다.</p>
                </div>
                <!-- 라인 차트 생성할 영역 -->
                <div id="lineChartArea" class="chart-area"></div>
                <!-- 컨트롤바를 생성할 영역 -->
                <div id="controlsArea" class="chart-control-area"></div>
            </div>

            <table class="table table-sm ta-center">
                <tr>
                    <td>국가</td>
                    <td>${ nation }</td>
                </tr>
                <tr>
                    <td>금액</td>
                    <td>${ reservationInfo.price }</td>
                </tr>
                <tr>
                    <td>환전 일자</td>
                    <td>
                        <c:set var="targetDate" value="<%=new Date(new Date().getTime())%>"/>
                        <c:set var="targetLong" value="<%=new Date().getTime()%>"/>
                        <fmt:formatDate var="departureDate" value="${ reservationInfo.departureDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <c:set var="departureLong" value="<%=Timestamp.valueOf((String)pageContext.getAttribute(\"departureDate\")).getTime()%>"/>
                        <c:set var="randomDate" value="<%=new Date(ThreadLocalRandom.current().nextLong((Long)pageContext.getAttribute(\"targetLong\"), (Long)pageContext.getAttribute(\"departureLong\") + 24*60*60*1000))%>"/>
                        <fmt:formatDate type="date" value="${randomDate}" pattern="yyyy년 MM월 dd일" var="recommendedDate"/>
                        <p class="text-warning">추천 일자는 ${recommendedDate}입니다.</p>
                        <input type="date" class="form-control ta-center input-sm"
                                name="exchangeDate"
                                min="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"
                                max="${ reservationInfo.departureDate }"
                                value="<fmt:formatDate type="date" value="${randomDate}" pattern="yyyy-MM-dd"/>">
                    </td>
                </tr>
            </table>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="post" formaction="/admin/confirm/exchange/${ reservationInfo.num }" value="저장"/>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="post" formaction="/admin/update/reservation/${ reservationInfo.num }" value="수정"/>
            <input type="submit" class="btn btn-sm btn-hana"
                   formmethod="post" formaction="/admin/delete/reservation/${ reservationInfo.num }" value="삭제"/>
        </form>
    </main>
</body>
</html>
