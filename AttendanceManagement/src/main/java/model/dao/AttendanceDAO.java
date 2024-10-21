package model.dao;

import java.sql.Connection;
import java.sql.Date; // java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time; // java.sql.Time
import java.util.ArrayList;
import java.util.List;

import model.entity.AttendanceBean;

public class AttendanceDAO {
	/**
	 * 勤怠データのリストを取得する
	 * @return 勤怠データのリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<AttendanceBean> getAttendanceList() throws SQLException, ClassNotFoundException {
		List<AttendanceBean> attendanceList = new ArrayList<>();

		String sql = "SELECT id, user_id, date, check_in_time, check_out_time, overtime_hours FROM attendance_records";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet res = pstmt.executeQuery()) {

			while (res.next()) {
				int id = res.getInt("id");
				int userId = res.getInt("user_id");
				Date date = res.getDate("date");
				Time checkInTime = res.getTime("check_in_time"); // Time型に修正
				Time checkOutTime = res.getTime("check_out_time"); // Time型に修正
				double overtimeHours = res.getDouble("overtime_hours");

				AttendanceBean attendance = new AttendanceBean(id, userId, date, checkInTime, checkOutTime,
						overtimeHours);
				attendanceList.add(attendance);

				// デバッグ用に出力
				System.out.println("ID: " + id + ", UserID: " + userId + ", Date: " + date
						+ ", Check-In Time: " + checkInTime + ", Check-Out Time: " + checkOutTime
						+ ", Overtime Hours: " + overtimeHours);
			}
		}

		return attendanceList;
	}

	/**
	 * 勤怠データを登録するメソッド
	 * @param userId ユーザID
	 * @param date 日付
	 * @param checkInTime 出勤時間
	 * @param checkOutTime 退勤時間
	 * @param overtimeHours 残業時間
	 * @return 登録された件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int registerAttendance(int userId, String date, String checkInTime, String checkOutTime,
			double overtimeHours) throws SQLException, ClassNotFoundException {
		int count = 0;
		String sql = "INSERT INTO attendance_records (user_id, date, check_in_time, check_out_time, overtime_hours) VALUES (?, ?, ?, ?, ?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
			pstmt.setString(2, date);
			pstmt.setString(3, checkInTime);
			pstmt.setString(4, checkOutTime);
			pstmt.setDouble(5, overtimeHours);

			count = pstmt.executeUpdate();
		}
		return count;
	}

	/**
	 * 1件の勤怠情報を取得
	 * @param id 勤怠ID
	 * @return 勤怠データ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public AttendanceBean getAttendanceOne(int id) throws SQLException, ClassNotFoundException {
		AttendanceBean attendance = null;
		String sql = "SELECT id, user_id, date, check_in_time, check_out_time, overtime_hours FROM attendance_records WHERE id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダーにIDをセット
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();

			if (res.next()) {
				// 取得したデータをAttendanceBeanにセット
				int userId = res.getInt("user_id");
				Date date = res.getDate("date");
				Time checkInTime = res.getTime("check_in_time");
				Time checkOutTime = res.getTime("check_out_time");
				double overtimeHours = res.getDouble("overtime_hours");

				attendance = new AttendanceBean(id, userId, date, checkInTime, checkOutTime, overtimeHours);
			}
		}
		return attendance;
	}

	/**
	 * 勤怠情報を編集
	 * @param id 勤怠ID
	 * @param date 日付
	 * @param checkInTime 出勤時間
	 * @param checkOutTime 退勤時間
	 * @param overtimeHours 残業時間
	 * @return 更新件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int editAttendance(int id, Date date, String checkInTimeStr, String checkOutTimeStr, double overtimeHours)
			throws SQLException, ClassNotFoundException {
		int count = 0;

		// 出勤時間と退勤時間をjava.sql.Timeに変換
		Time checkInTime = Time.valueOf(checkInTimeStr + ":00"); // 参考にしてほしいコード基づき修正
		Time checkOutTime = Time.valueOf(checkOutTimeStr + ":00"); // 参考にしてほしいコード基づき修正

		String sql = "UPDATE attendance_records SET date = ?, check_in_time = ?, check_out_time = ?, overtime_hours = ? WHERE id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダーに値をセット
			pstmt.setDate(1, date); // 日付をセット
			pstmt.setTime(2, checkInTime); // 出勤時間をセット
			pstmt.setTime(3, checkOutTime); // 退勤時間をセット
			pstmt.setDouble(4, overtimeHours); // 残業時間をセット
			pstmt.setInt(5, id); // IDをセット

			// SQL実行
			count = pstmt.executeUpdate();
		}
		return count;
	}

	/**
	 * 勤怠データを削除するメソッド
	 * @param id 勤怠ID
	 * @return 削除件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int deleteAttendance(int id) throws ClassNotFoundException, SQLException {
		int count = 0; // 削除件数を格納する変数

		// SQL文（プレースホルダー1つ）
		String sql = "DELETE FROM attendance_records WHERE id = ?";

		// データベース接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// 1つ目のプレースホルダーに引数idの値をセット
			pstmt.setInt(1, id);

			// SQL実行し、削除件数をcountに代入
			count = pstmt.executeUpdate();
		}
		return count;
	}

	public List<AttendanceBean> searchAttendanceByDate(Date searchDate) throws SQLException, ClassNotFoundException {
		List<AttendanceBean> attendanceList = new ArrayList<>();

		String sql = "SELECT id, user_id, date, check_in_time, check_out_time, overtime_hours FROM attendance_records WHERE date = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setDate(1, searchDate); // 検索する日付を設定
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				int userId = res.getInt("user_id");
				Date date = res.getDate("date");

				// 出勤時間と退勤時間は Time 型で取得し、それを java.util.Date に変換
				Time checkInTimeSql = res.getTime("check_in_time");
				Time checkOutTimeSql = res.getTime("check_out_time");

				Date checkInTime = new Date(checkInTimeSql.getTime());
				Date checkOutTime = new Date(checkOutTimeSql.getTime());

				double overtimeHours = res.getDouble("overtime_hours");

				AttendanceBean attendance = new AttendanceBean(id, userId, date, checkInTime, checkOutTime,
						overtimeHours);
				attendanceList.add(attendance);
			}
		}

		return attendanceList;
	}

	public List<AttendanceBean> getOvertimeList() throws SQLException, ClassNotFoundException {
		List<AttendanceBean> overtimeList = new ArrayList<>();

		String sql = "SELECT id, user_id, date, overtime_hours FROM attendance_records WHERE overtime_hours > 0";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet res = pstmt.executeQuery()) {

			while (res.next()) {
				int id = res.getInt("id");
				int userId = res.getInt("user_id");
				Date date = res.getDate("date");
				double overtimeHours = res.getDouble("overtime_hours");

				AttendanceBean attendance = new AttendanceBean(id, userId, date, null, null, overtimeHours);
				overtimeList.add(attendance);
			}
		}

		return overtimeList;
	}

	public List<AttendanceBean> searchOvertimeById(int userId) throws SQLException, ClassNotFoundException {
		List<AttendanceBean> overtimeList = new ArrayList<>();

		String sql = "SELECT id, user_id, date, overtime_hours FROM attendance_records WHERE user_id = ? AND overtime_hours > 0";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, userId); // IDを条件に設定
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int id = res.getInt("id");
				Date date = res.getDate("date");
				double overtimeHours = res.getDouble("overtime_hours");

				AttendanceBean attendance = new AttendanceBean(id, userId, date, null, null, overtimeHours);
				overtimeList.add(attendance);
			}
		}

		return overtimeList;
	}

}
