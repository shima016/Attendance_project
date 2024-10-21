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

@WebServlet("/overtime-management")
public class OvertimeManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OvertimeManagementServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 残業時間のリストを格納する変数
		List<AttendanceBean> overtimeList = null;

		// DAOのインスタンス生成
		AttendanceDAO dao = new AttendanceDAO();

		try {
			// 残業時間のリストを取得
			overtimeList = dao.getOvertimeList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// リクエストスコープに残業時間のリストをセット
		request.setAttribute("overtimeList", overtimeList);

		// JSPに転送
		RequestDispatcher rd = request.getRequestDispatcher("overtime-management.jsp");
		rd.forward(request, response);
	}
}