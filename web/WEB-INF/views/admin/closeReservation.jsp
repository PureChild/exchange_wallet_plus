<%--
  User: SeungsooLee
  Date: 2019-08-09
  Time: 오전 9:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>환전 완료</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <div class="main-content">
            <p>환전이 완료되었습니다.</p>
            <a href="/admin/reservation/history" class="btn btn-hana">목록으로</a>
        </div>
    </main>
</body>
</html>
