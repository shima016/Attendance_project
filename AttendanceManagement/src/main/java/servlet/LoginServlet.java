package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページへ転送
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDAO dao = new UserDAO();
		UserBean user = null;

		try {
			// UserDAOのloginメソッドでユーザを取得
			user = dao.login(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// ログイン成功かどうかの判定
		if (user != null) {
			// ユーザ情報をセッションに保存
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// トップページにリダイレクト
			response.sendRedirect("top.jsp");
		} else {
			// ログイン失敗、エラーメッセージをリクエストに追加してログインページへ転送
			request.setAttribute("errorMessage", "ユーザ名またはパスワードが違います");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
}