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

@WebServlet("/attendance-list")
public class AttendanceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<AttendanceBean> attendanceList = null;

		AttendanceDAO dao = new AttendanceDAO();
		try {
			// DAOクラスのメソッドを呼び出して勤怠データを取得
			attendanceList = dao.getAttendanceList();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// リクエストスコープに勤怠データをセット
		request.setAttribute("attendanceList", attendanceList);

		// JSPにフォワードして、勤怠データを表示
		RequestDispatcher rd = request.getRequestDispatcher("attendance-list.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GETリクエストと同様に処理を行う
		doGet(request, response);
	}
}