<%--
  User: SeungsooLee
  Date: 2019-08-07
  Time: 오전 10:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>관리자 로그인</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="login-form" action="/admin/loginConfirm" method="post">
            <h1 class="page-title">관리자 로그인</h1>

            <p class="text-warning">${msg}</p>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-input-label" id="basic-addon1">ID</span>
                </div>
                <input type="text" class="form-control" name="admId" placeholder="관리자 ID" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-input-label" id="basic-addon2">PW</span>
                </div>
                <input type="password" class="form-control" name="admPassword" placeholder="관리자 비밀번호" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <input type="submit" class="btn btn-hana" value="로그인">
        </form>
    </main>
</body>
</html>
