<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>残業管理</title>
</head>
<body>
	<h1>残業管理</h1>

	<!-- エラーメッセージ表示 -->
	<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
	%>
	<p style="color: red;"><%=errorMessage%></p>
	<%
	}
	%>

	<!-- ID検索フォーム -->
	<form action="overtime-search" method="post">
		<label for="userId">ユーザIDで検索：</label> <input type="text" name="userId"
			id="userId" required>
		<button type="submit">検索</button>
	</form>

	<br>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>ユーザID</th>
			<th>日付</th>
			<th>残業時間</th>
		</tr>

		<%
		List<model.entity.AttendanceBean> overtimeList = (List<model.entity.AttendanceBean>) request
				.getAttribute("overtimeList");

		if (overtimeList != null && !overtimeList.isEmpty()) {
			for (model.entity.AttendanceBean attendance : overtimeList) {
		%>
		<tr>
			<td><%=attendance.getId()%></td>
			<td><%=attendance.getUserId()%></td>
			<td><%=attendance.getDate()%></td>
			<td><%=attendance.getOvertimeHours()%></td>
		</tr>
		<%
		}
		} else if (errorMessage == null) {
		%>
		<tr>
			<td colspan="4" style="text-align: center;">該当なし</td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<a href="top.jsp">トップに戻る</a>
</body>
</html>