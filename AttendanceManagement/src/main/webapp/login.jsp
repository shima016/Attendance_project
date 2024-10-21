<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<h1>ログイン</h1>

	<!-- エラーメッセージ表示 -->
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
	%>
	<p style="color: red;"><%=errorMessage%></p>
	<%
	}
	%>

	<!-- ログインフォーム -->
	<form action="login" method="post">
		<div>
			<label for="username">ユーザ名</label> <input type="text" name="username"
				id="username" required>
		</div>
		<div>
			<label for="password">パスワード</label> <input type="password"
				name="password" id="password" required>
		</div>
		<div>
			<button type="submit">ログイン</button>
		</div>
	</form>
</body>
</html>