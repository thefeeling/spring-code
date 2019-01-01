<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>로그인페이지</title>  
</head>
  
<body>
<h2>로그인 </h2>
<<<<<<< HEAD
<form name="form" method="post" action="loginProcess">
<table>
    <tr height="40px">
        <td>사용자아이디</td>
        <td><input type="text" name="id"></td>
    </tr>
    <tr height="40px">
        <td>패스워드</td>
        <td><input type="password" name="pw"></td>
=======
<form name="form" method="post" action="/blog/auth/loginProc">
<table>
    <tr height="40px">
        <td>사용자아이디</td>
        <td><input type="text" name="blogId"></td>
    </tr>
    <tr height="40px">
        <td>패스워드</td>
        <td><input type="password" name="blogPw"></td>
>>>>>>> deafc46b25ad31662a99b071a07a4059b63470cf
    </tr>
</table>
<table>
    <tr>
        <td align="center"><input type="submit" value="로그인"></td>
        <td align="center"><input type="reset" value="리셋"></td>
    </tr>
</table>
</form>
</body>
</html>