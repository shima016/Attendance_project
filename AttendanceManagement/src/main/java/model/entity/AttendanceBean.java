package model.entity;

import java.io.Serializable;
import java.util.Date;

public class AttendanceBean implements Serializable {
	/**
	 * 勤怠ID
	 */
	private int id;

	/**
	 * ユーザID
	 */
	private int userId;

	/**
	 * 勤怠日
	 */
	private Date date;

	/**
	 * 出勤時間
	 */
	private Date checkInTime;

	/**
	 * 退勤時間
	 */
	private Date checkOutTime;

	/**
	 * 残業時間
	 */
	private double overtimeHours;

	/**
	 * 引数なしデフォルトコンストラクタ
	 */
	public AttendanceBean() {
	}

	/**
	 * 引数ありコンストラクタ
	 * @param id 勤怠ID
	 * @param userId ユーザID
	 * @param date 勤怠日
	 * @param checkInTime 出勤時間
	 * @param checkOutTime 退勤時間
	 * @param overtimeHours 残業時間
	 */
	public AttendanceBean(int id, int userId, Date date, Date checkInTime, Date checkOutTime, double overtimeHours) {
		this.id = id;
		this.userId = userId;
		this.date = date;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.overtimeHours = overtimeHours;
	}

	/**
	 * @return 勤怠ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id 勤怠IDをセットする
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return ユーザID
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId ユーザIDをセットする
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return 勤怠日
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date 勤怠日をセットする
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return 出勤時間
	 */
	public Date getCheckInTime() {
		return checkInTime;
	}

	/**
	 * @param checkInTime 出勤時間をセットする
	 */
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * @return 退勤時間
	 */
	public Date getCheckOutTime() {
		return checkOutTime;
	}

	/**
	 * @param checkOutTime 退勤時間をセットする
	 */
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/**
	 * @return 残業時間
	 */
	public double getOvertimeHours() {
		return overtimeHours;
	}

	/**
	 * @param overtimeHours 残業時間をセットする
	 */
	public void setOvertimeHours(double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}
}