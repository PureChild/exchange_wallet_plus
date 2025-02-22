<%--
  User: SeungsooLee
  Date: 2019-08-12
  Time: 오전 9:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>환율 정보</title>
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

    <main class="none-img">
        <div class="main-content">
            <img id="nationFlag" src="/image/flags/${ reservationInfo.nationCode }.png" class="flag btn-speech" data-speech="false" alt="nation-flag"/>
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
            <ul id="tabMenu" class="tab-menu">
                <li class="tab-item" data-content="info">환율 정보</li>
                <li class="tab-item" data-content="issue">이슈</li>
            </ul>
            <div class="tab-content-container">
                <div id="exchangeInfoContainer">
                    <table id="exchangeInfo" class="table table-sm ta-center">
                        <tr>
                            <td class="tlabel">매매기준율</td>
                            <td><p id="dealBaseRate"></p></td>
                        </tr>
                        <tr>
                            <td class="tlabel">송금 보내실 때</td>
                            <td><p id="tts"></p></td>
                        </tr>
                        <tr>
                            <td class="tlabel">송금 받으실 때</td>
                            <td><p id="ttb"></p></td>
                        </tr>
                    </table>
                </div>
                <div id="issueContainer">
                    <img src="/image/loading.gif" class="img-loader issue-loader" alt="로딩이미지"/>
                    <p class="text-warning">최근 이슈를 불러오는 중입니다.</p>
                </div>
            </div>
        </div>
    </main>
</body>
<script type="text/template" id="issue">
    <div class="issue">
        {{thumb}}{{title}}
    </div>
</script>
</html>
