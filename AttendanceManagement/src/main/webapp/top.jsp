<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠管理システム</title>
<style>
/* ログアウトボタンの位置を調整 */
.logout-button {
	margin-top: 20px; /* ボタンの左側にスペースを追加 */
}
</style>
</head>
<body>
	<h1>勤怠管理システム</h1>

	<!-- 勤怠一覧ボタン -->
	<form action="attendance-list" method="get">
		<button type="submit">勤怠一覧</button>
	</form>

	<!-- 勤怠登録ボタン -->
	<form action="attendance-register" method="get">
		<button type="submit">勤怠登録</button>
	</form>

	<!-- 残業管理ボタン -->
	<form action="overtime-management" method="get">
		<button type="submit">残業管理</button>
	</form>

	<!-- ログアウトボタン（スペースを追加） -->
	<form action="logout" method="get" class="logout-button">
		<button type="submit">ログアウト</button>
	</form>
</body>
</html>