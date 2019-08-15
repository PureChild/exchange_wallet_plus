<%--
  User: SeungsooLee
  Date: 2019-08-10
  Time: 오후 12:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
</head>
<body>
    <%-- 메뉴바 include --%>
    <jsp:include page="menu.jsp"/>

    <main>
        <form class="join-form" action="/joinConfirm" method="post">
            <h1 class="page-title">회원가입</h1>

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

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-join-label" id="basic-addon3">Name</span>
                </div>
                <input type="text" class="form-control" name="name" placeholder="이름" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text form-join-label" id="basic-addon4">Account</span>
                </div>
                <input type="text" class="form-control" name="account" placeholder="계좌번호" aria-label="Username" aria-describedby="basic-addon1">
            </div>

            <input type="submit" class="btn btn-hana" value="회원가입">
        </form>
    </main>
</body>
</html>
