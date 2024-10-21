<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠一覧</title>
</head>
<body>
	<h1>勤怠一覧</h1>

	<!-- 検索フォーム -->
	<form action="attendance-search" method="post">
		<label for="searchDate">日付で検索：</label> <input type="date"
			name="searchDate" id="searchDate" required>
		<button type="submit">検索</button>
	</form>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>ユーザID</th>
			<th>日付</th>
			<th>出勤時間</th>
			<th>退勤時間</th>
			<th>残業時間</th>
			<th>操作</th>
		</tr>

		<%
		// サーブレットから渡されたattendanceListを取得
		List<model.entity.AttendanceBean> attendanceList = (List<model.entity.AttendanceBean>) request
				.getAttribute("attendanceList");

		if (attendanceList != null && !attendanceList.isEmpty()) {
			for (model.entity.AttendanceBean attendance : attendanceList) {
		%>
		<tr>
			<td><%=attendance.getId()%></td>
			<td><%=attendance.getUserId()%></td>
			<td><%=attendance.getDate()%></td>
			<td><%=attendance.getCheckInTime()%></td>
			<td><%=attendance.getCheckOutTime()%></td>
			<td><%=attendance.getOvertimeHours()%></td>
			<td>
				<form action="attendance-edit" method="get" style="display: inline;">
					<input type="hidden" name="id" value="<%=attendance.getId()%>">
					<button type="submit">編集</button>
				</form>
				<form action="attendance-delete" method="post"
					style="display: inline;">
					<input type="hidden" name="id" value="<%=attendance.getId()%>">
					<button type="submit">削除</button>
				</form>
			</td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="7">該当するデータがありません。</td>
		</tr>
		<%
		}
		%>
	</table>

	<br>
	<form action="attendance-list" method="get">
		<button type="submit">勤怠一覧を再表示</button>
	</form>
	<br>
	<a href="top.jsp">トップに戻る</a>
</body>
</html>