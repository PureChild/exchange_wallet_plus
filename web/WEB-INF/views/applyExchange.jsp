<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오전 9:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>환전 신청</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <c:set var="targetDate" value="<%=new Date(new Date().getTime() - 60*60*24*1000)%>"/>
    <script id="api-nation" data-date="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyyMMdd"/>" data-nation="${ reservationInfo.nationCode }" src="/js/nation.js"></script>
    <script src="/js/exchangeInfo.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="info-form" action="/make/reservation" method="post">
            <img id="nationFlag" src="/image/flags/${ reservationInfo.nationCode }.png" class="flag" alt="nation-flag"/>
            <table class="table table-sm ta-center">
                <tr>
                    <td class="tlabel-sm">국가</td>
                    <td class="tcontent-apply-nation">
                        <div id="nationContainer">
                            <p class="text-warning text-sm">
                                <fmt:formatDate type="date" value="${targetDate}" pattern="yyyy년MM월dd일"/>의 국가 목록을 불러올 수 없습니다.
                            </p>
                            <label>영업일자
                                <input type="date" id="businessDate" class="form-control ta-center"
                                       max="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"/>
                            </label>
                            <button type="button" id="btnGetNations" class="btn btn-sm btn-outline-hana">국가 목록 불러오기</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="tlabel-sm">금액</td>
                    <td>
                        <input type="text" class="form-control ta-center" name="price"/>
                    </td>
                </tr>
                <tr>
                    <td class="tlabel-sm">여행 출발 일자</td>
                    <td>
                        <c:set var="targetDate" value="<%=new Date(new Date().getTime())%>"/>
                        <input type="date" class="form-control ta-center" name="departureDate"
                               min="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"/>
                    </td>
                </tr>
            </table>
            <a href="/application/history/1" class="btn btn-sm btn-hana">취소</a>
            <input type="submit" class="btn btn-sm btn-hana" value="예약"/>
        </form>
    </main>

</body>
</html>