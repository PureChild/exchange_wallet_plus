<%--
  User: SeungsooLee
  Date: 2019-08-10
  Time: 오후 1:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="login-form" action="/loginConfirm" method="post">
            <h1 class="page-title">로그인</h1>

            <p class="text-warning">${msg}</p>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-join-label" id="basic-addon1">ID</span>
                </div>
                <input type="text" class="form-control" name="id" placeholder="ID" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-join-label" id="basic-addon2">PW</span>
                </div>
                <input type="password" class="form-control" name="pw" placeholder="비밀번호" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <input type="submit" class="btn btn-hana" value="로그인">

            <p><a href="/join">아직 회원이 아니신가요?</a></p>
        </form>
    </main>
</body>
</html>
