<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
</head>
<body>
<h1>一覧画面</h1>
<table border="1">
    <tr>
       <th>名前</th>
       <th>読み</th>
       <th>郵便番号</th>
       <th>住所</th>
       <th>電話番号</th>
       <th>メールアドレス</th>
       <th>削除</th>
     </tr>

<c:forEach var="userInfo" items="${ userInfoList }">
     <tr>
         <td>${ userInfo.name }</td>
         <td>${ userInfo.yomi }</td>
         <td>${ userInfo.zip }</td>
         <td>${ userInfo.address }</td>
         <td>${ userInfo.tel }</td>
         <td>${ userInfo.email }</td>
         <td>削除</td>
     </tr>
</c:forEach>

</table>
<a href="${ pageContext.request.contextPath }/regist.jsp">新規登録ページ</a>
</body>
</html>