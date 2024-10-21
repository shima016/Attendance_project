package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AttendanceDAO;

/**
 * 勤怠登録サーブレット
 */
@WebServlet("/attendance-register")
public class AttendanceRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttendanceRegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディング
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int userId = Integer.parseInt(request.getParameter("userId"));
		String date = request.getParameter("date");
		String checkInTime = request.getParameter("checkInTime");
		String checkOutTime = request.getParameter("checkOutTime");
		double overtimeHours = Double.parseDouble(request.getParameter("overtimeHours"));

		// DAOを使用して勤怠データを登録
		AttendanceDAO dao = new AttendanceDAO();
		try {
			dao.registerAttendance(userId, date, checkInTime, checkOutTime, overtimeHours);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 登録完了後に勤怠一覧画面にリダイレクト
		response.sendRedirect("attendance-list");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("attendance-register.jsp");
		rd.forward(request, response);
	}
}