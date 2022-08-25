<%--
  Created by IntelliJ IDEA.
  User: yuk_j
  Date: 2022-08-24
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
</head>
<body>
    <div>
        <a href="${naverAuthUrl}">네이버 아이디로 로그인</a>
        <a href="/oauth2/login">그냥 실행</a>
    </div>
</body>
</html>