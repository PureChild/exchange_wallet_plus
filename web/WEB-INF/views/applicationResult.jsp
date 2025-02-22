<%--
  User: SeungsooLee
  Date: 2019-08-14
  Time: 오후 3:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신청 결과</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery-barcode.js"></script>
    <script type="text/javascript" src="/js/application.js"></script>

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
                    <td>
                        <fmt:formatNumber value="${ reservationInfo.price }" type="currency" />
                    </td>
                </tr>
                <tr>
                    <td class="tlabel">환전코드</td>
                    <td>
                        <input type="hidden" id="exchangeCode" value="${ confirmedExchangeInfo.exchangeCode }">
                        <div id="barcodeContainer" class="margin-center" data-toggle="modal" data-target="#barcodeEnlargementModal"></div>
                    </td>
                </tr>
                <tr>
                    <td class="tlabel">환전일자</td>
                    <td>${ confirmedExchangeInfo.exchangeDate }</td>
                </tr>
            </table>
            <a href="/application/history/1" class="btn btn-sm btn-hana">목록으로</a>
        </div>
    </main>
    <div id="barcodeEnlargementModal" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">환전 코드 - ${nation}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="barcodeModal" class="margin-center"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
