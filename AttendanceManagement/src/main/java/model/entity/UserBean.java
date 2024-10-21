package model.entity;

import java.io.Serializable;

public class UserBean implements Serializable {
	/**
	 * ユーザID
	 */
	private int id;

	/**
	 * ユーザ名
	 */
	private String username;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 引数なしのデフォルトコンストラクタ
	 */
	public UserBean() {
	}

	/**
	 * 引数ありのコンストラクタ
	 * @param id ユーザID
	 * @param username ユーザ名
	 * @param password パスワード
	 */
	public UserBean(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * ユーザIDを取得する
	 * @return id ユーザID
	 */
	public int getId() {
		return id;
	}

	/**
	 * ユーザIDをセットする
	 * @param id セットするユーザID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ユーザ名を取得する
	 * @return username ユーザ名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * ユーザ名をセットする
	 * @param username セットするユーザ名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * パスワードを取得する
	 * @return password パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードをセットする
	 * @param password セットするパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}