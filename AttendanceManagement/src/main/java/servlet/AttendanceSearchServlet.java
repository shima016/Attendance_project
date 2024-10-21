package servlet;

import java.io.IOException;
import java.sql.Date;
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

@WebServlet("/attendance-search")
public class AttendanceSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendanceSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータのエンコーディングを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストから検索する日付を取得
		String dateParam = request.getParameter("searchDate");

		// 入力された日付をjava.sql.Dateに変換
		Date searchDate = Date.valueOf(dateParam);

		// AttendanceDAOを使って検索処理を実行
		AttendanceDAO dao = new AttendanceDAO();
		List<AttendanceBean> attendanceList = null;

		try {
			attendanceList = dao.searchAttendanceByDate(searchDate);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 検索結果をリクエストスコープに設定
		request.setAttribute("attendanceList", attendanceList);

		// 勤怠一覧ページに転送
		RequestDispatcher rd = request.getRequestDispatcher("attendance-list.jsp");
		rd.forward(request, response);
	}
}