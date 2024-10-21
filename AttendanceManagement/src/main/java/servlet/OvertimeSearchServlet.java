package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AttendanceDAO;
import model.entity.AttendanceBean;

@WebServlet("/overtime-search")
public class OvertimeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OvertimeSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストのエンコーディングを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータからユーザIDを取得
		String userIdStr = request.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);

		// 検索結果を格納する変数
		AttendanceDAO dao = new AttendanceDAO();
		List<AttendanceBean> overtimeList = null;

		try {
			// DAOのメソッドでユーザIDに基づく残業時間を検索
			overtimeList = dao.searchOvertimeById(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 検索結果をリクエストスコープにセット
		request.setAttribute("overtimeList", overtimeList);

		// 残業管理ページに転送
		RequestDispatcher rd = request.getRequestDispatcher("overtime-management.jsp");
		rd.forward(request, response);
	}
}
