<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h2>게이트 페이지</h2>
    <div>
    <c:choose>
        <c:when test="${USER_INFO eq null}">
            <a href="/login">로그인 페이지</a>
        </c:when>
        <c:otherwise>
            <c:if test="${USER_INFO.loginType eq 'naver'}">
                <h3>네이버 아이디로 접속중</h3>
                <span><a href="/logout">로그아웃</a></span>
                <div>
                    <div>
                        <span>
                            <image src="${USER_INFO.profileImage}"/>
                        </span>
                        <span>${USER_INFO.id}</span>
                    </div>
                    <div>${USER_INFO.name}</div>
                </div>
            </c:if>
        </c:otherwise>
    </c:choose>
    </div>
</body>
</html>