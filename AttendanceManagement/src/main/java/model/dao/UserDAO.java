package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {

	/**
	 * ログインするためにユーザを取得するメソッド
	 * @param username ユーザ名
	 * @param password パスワード
	 * @return UserBean ユーザ情報（存在しない場合はnull）
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserBean login(String username, String password) throws SQLException, ClassNotFoundException {
		UserBean user = null;

		// SQL文
		String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";

		// データベース接続とSQL実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// パラメータを設定
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			// 実行結果を取得
			ResultSet res = pstmt.executeQuery();

			// 結果があればUserBeanにデータをセット
			if (res.next()) {
				int id = res.getInt("id");
				String dbUsername = res.getString("username");
				String dbPassword = res.getString("password");

				// UserBeanのインスタンス作成
				user = new UserBean(id, dbUsername, dbPassword);
			}
		}

		return user;
	}
}