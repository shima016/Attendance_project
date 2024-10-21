<%@page import="model.entity.AttendanceBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AttendanceBean attendance = (AttendanceBean) request.getAttribute("attendance");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠編集</title>
</head>
<body>
	<h1>勤怠編集</h1>
	<form action="attendance-edit" method="post">
		<div>
			<p>
				ユーザID：<%=attendance.getUserId()%></p>
		</div>
		<div>
			<label for="date">日付</label> <input type="date" name="date" id="date"
				value="<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(attendance.getDate())%>">
		</div>
		<div>
			<label for="checkInTime">出勤時間</label> <input type="time"
				name="checkInTime" id="checkInTime"
				value="<%=new java.text.SimpleDateFormat("HH:mm").format(attendance.getCheckInTime())%>">
		</div>
		<div>
			<label for="checkOutTime">退勤時間</label> <input type="time"
				name="checkOutTime" id="checkOutTime"
				value="<%=new java.text.SimpleDateFormat("HH:mm").format(attendance.getCheckOutTime())%>">
		</div>
		<div>
			<div>
				<label for="overtimeHours">残業時間</label> <input type="number"
					name="overtimeHours" id="overtimeHours"
					value="<%=attendance.getOvertimeHours()%>" min="0" step="0.5">

			</div>
			<div>
				<button type="submit">更新</button>
				<input type="hidden" name="id" value="<%=attendance.getId()%>">
				<a href="attendance-list">戻る</a>
			</div>
	</form>
</body>
</html>