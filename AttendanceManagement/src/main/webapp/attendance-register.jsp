<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠登録</title>
</head>
<body>
	<div>
		<h1>勤怠登録</h1>
		<form action="attendance-register" method="post">
			<div>
				<label for="userId">ユーザID</label><br> <input type="number"
					name="userId" id="userId" required placeholder="数字を記入してください">
			</div>
			<div>
				<label for="date">日付</label><br> <input type="date" name="date"
					id="date" required>
			</div>
			<div>
				<label for="checkInTime">出勤時間</label><br> <input type="time"
					name="checkInTime" id="checkInTime" required>
			</div>
			<div>
				<label for="checkOutTime">退勤時間</label><br> <input type="time"
					name="checkOutTime" id="checkOutTime" required>
			</div>
			<div>
				<label for="overtimeHours">残業時間</label><br> <input
					type="number" step="0.5" name="overtimeHours" id="overtimeHours"
					required>
			</div>
			<div>
				<button type="submit">登録</button>
				<a href="top.jsp">トップに戻る</a>
				<!-- 戻るボタンでトップ画面に遷移 -->
			</div>
		</form>
	</div>
</body>
</html>