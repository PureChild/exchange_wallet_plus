<%--
  User: SeungsooLee
  Date: 2019-08-13
  Time: 오후 4:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>목표 환율 상세</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/targetRate.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content">
            <img id="nationFlag" src="/image/flags/${ targetRateInfo.nationCode }.png" class="flag" alt="nation-flag"/>
            <table class="table table-sm ta-center">
                <tr>
                    <td class="tlabel">국가</td>
                    <td>${nation}</td>
                </tr>
                <tr>
                    <td class="tlabel">목표환율</td>
                    <td>
                        <input type="hidden" id="targetRate" value="${ targetRateInfo.rate }"/>
                        ${ targetRateInfo.rate }
                    </td>
                </tr>
            </table>
            <div id="compareResult">
                <c:set var="targetDate" value="<%=new Date(new Date().getTime() - 60*60*24*1000)%>"/>
                <input type="hidden" id="targetDate" value="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyyMMdd"/>"/>
                <input type="hidden" id="targetNation" value="${ targetRateInfo.nationCode }"/>
                <p class="text-warning text-sm">
                    <fmt:formatDate type="date" value="${targetDate}" pattern="yyyy년MM월dd일"/>의 국가 목록을 불러올 수 없습니다.
                </p>
                <label>영업일자
                    <input type="date" id="businessDate" class="form-control ta-center input-sm"
                           max="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"/>
                </label>
                <button type="button" id="btnGetRates" class="btn btn-sm btn-outline-hana">환율 정보 불러오기</button>
            </div>
        </div>
    </main>
</body>
</html>
