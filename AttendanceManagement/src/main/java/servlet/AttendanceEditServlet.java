package servlet;

import java.io.IOException;
import java.sql.Date; // java.sql.Date
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AttendanceDAO;
import model.entity.AttendanceBean;

@WebServlet("/attendance-edit")
public class AttendanceEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 勤怠編集画面を表示する (GETリクエスト)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータからIDを取得
		int id = Integer.parseInt(request.getParameter("id"));
		AttendanceBean attendance = null;
		AttendanceDAO dao = new AttendanceDAO();

		try {
			// 勤怠データを取得
			attendance = dao.getAttendanceOne(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 勤怠データが取得できているか確認
		if (attendance != null) {
			// リクエストスコープに勤怠データを設定
			request.setAttribute("attendance", attendance);

			// JSPへ転送
			RequestDispatcher rd = request.getRequestDispatcher("attendance-edit.jsp");
			rd.forward(request, response);
		} else {
			// データが見つからない場合、エラーメッセージを返す
			System.out.println("Attendance data is null");
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "勤怠データが見つかりません");
		}
	}

	/**
	 * 勤怠情報を編集する (POSTリクエスト)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int id = Integer.parseInt(request.getParameter("id"));
		String dateStr = request.getParameter("date"); // 日付
		String checkInTime = request.getParameter("checkInTime"); // 出勤時間
		String checkOutTime = request.getParameter("checkOutTime"); // 退勤時間
		double overtimeHours = Double.parseDouble(request.getParameter("overtimeHours")); // 残業時間

		// 日付をjava.sql.Dateに変換
		Date date = Date.valueOf(dateStr);

		AttendanceDAO dao = new AttendanceDAO();

		try {
			// 勤怠データを更新
			dao.editAttendance(id, date, checkInTime, checkOutTime, overtimeHours);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 勤怠一覧にリダイレクト
		response.sendRedirect("attendance-list");
	}
}