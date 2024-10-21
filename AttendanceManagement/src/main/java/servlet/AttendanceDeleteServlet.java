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
 * Servlet implementation class AttendanceDeleteServlet
 */
@WebServlet("/attendance-delete")
public class AttendanceDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttendanceDeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int id = Integer.parseInt(request.getParameter("id"));

		// AttendanceDAOクラスのインスタンス生成
		AttendanceDAO dao = new AttendanceDAO();

		try {
			// AttendanceDAOクラスのdeleteAttendanceメソッド呼び出し、データ削除
			dao.deleteAttendance(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// 勤怠一覧画面へ転送
		RequestDispatcher rd = request.getRequestDispatcher("attendance-list");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
