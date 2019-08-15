<%--
  User: SeungsooLee
  Date: 2019-08-14
  Time: 오후 3:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신청 결과</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content">
            <img id="nationFlag" src="/image/flags/${ reservationInfo.nationCode }.png" class="flag" alt="nation-flag"/>
            <table class="table table-sm ta-center">
                <tr>
                    <td class="tlabel">국가</td>
                    <td>${nation}</td>
                </tr>
                <tr>
                    <td class="tlabel">금액</td>
                    <td>${ reservationInfo.price }</td>
                </tr>
                <tr>
                    <td class="tlabel">환전코드</td>
                    <td>${ confirmedExchangeInfo.exchangeCode }</td>
                </tr>
                <tr>
                    <td class="tlabel">환전일자</td>
                    <td>${ confirmedExchangeInfo.exchangeDate }</td>
                </tr>
            </table>
            <a href="/application/history/1" class="btn btn-sm btn-hana">목록으로</a>
        </div>
    </main>
</body>
</html>
