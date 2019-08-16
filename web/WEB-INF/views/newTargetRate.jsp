<%--
  User: SeungsooLee
  Date: 2019-08-14
  Time: 오전 9:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <c:choose>
            <c:when test="${mode == 'insert'}">
                목표 환율 신규
            </c:when>
            <c:when test="${mode == 'update'}">
                목표 환율 수정
            </c:when>
        </c:choose>
    </title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="/js/inputForm.js"></script>
    <c:set var="targetDate" value="<%=new Date(new Date().getTime() - 60*60*24*1000)%>"/>
    <script id="api-nation"
            data-date="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyyMMdd"/>"
            data-nation="${ nationCode }"
            src="/js/nation.js"></script>
    <script src="/js/exchangeInfo.js"></script>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form action="/${mode}/exchange/rate" method="post" class="info-form">
            <img id="nationFlag" src="/image/flags/${ nationCode }.png" class="flag" alt="nation-flag"/>
            <input type="hidden" name="originNationCode" value="${ nationCode }"/>
            <table class="table table-sm ta-center">
                <tr>
                    <td>국가</td>
                    <td>
                        <div id="nationContainer">
                            <p class="text-warning text-sm">
                                <fmt:formatDate type="date" value="${targetDate}" pattern="yyyy년MM월dd일"/>의 국가 목록을 불러올 수 없습니다.
                            </p>
                            <label>영업일자
                                <input type="date" id="businessDate" class="form-control ta-center input-sm"
                                       max="<fmt:formatDate type="date" value="${targetDate}" pattern="yyyy-MM-dd"/>"/>
                            </label>
                            <button type="button" id="btnGetNations" class="btn btn-sm btn-outline-hana">국가 목록 불러오기</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>목표환율</td>
                    <td><input type="text" class="form-control ta-center input-sm" name="rate" value="${ targetRate.rate }"/></td>
                </tr>
            </table>

            <input type="submit" class="btn btn-sm btn-hana" value="저장">
        </form>
    </main>
</body>
</html>