<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録ページ</title>
</head>
<body>
新規登録
<br>
<br>
${ message }
<br>
<br>
<form action="${ pageContext.request.contextPath }/regist" method="post">
<table>
<tr>
<th>名前</th>
<td><input type="text" name="name"/></td>
</tr>
<tr>
<th>読み</th>
<td><input type="text" name="yomi"/></td>
</tr>
<tr>
<th>郵便番号</th>
<td><input type="text" name="zip"/></td>
</tr>
<tr>
<th>住所</th>
<td><input type="text" name="address"/></td>
</tr>
<tr>
<th>電話番号</th>
<td><input type="text" name="tel"/></td>
</tr>
<tr>
<th>メールアドレス</th>
<td><input type="text" name="email"/></td>
</tr>
</table>
<input type="submit" value="登録"/>
</form>
<a href="${ pageContext.request.contextPath }/findall">一覧ページへ</a>
</body>
</html>