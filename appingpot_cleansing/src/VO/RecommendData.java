package VO;

import java.sql.Timestamp;

public class RecommendData {
	private String id;
	private String userId;
	private String packageId;
	private String packageName;
	private double tempRating;
	private String time;
	private double normalization1;
	private double normalizatedRatings;
	public RecommendData() {
		// TODO Auto-generated constructor stub
	}
	
	public RecommendData(String id, String packageId, double tempRating, String time) {
		this.id = id;
		this.packageId = packageId;
		this.tempRating = tempRating;
		this.time = time;
	}

	public RecommendData(String id, String userId,String packageName, double tempRating, String time) {
		this.id = id;
		this.userId=userId;
		this.packageName = packageName;
		this.tempRating = tempRating;
		this.time = time;
	}

	public RecommendData(String id, String userId, String packageId, String packageName, double tempRating, String time,
			double normalization1, double normalizatedRatings) {
		this.id = id;
		this.userId = userId;
		this.packageId = packageId;
		this.packageName = packageName;
		this.tempRating = tempRating;
		this.time = time;
		this.normalization1 = normalization1;
		this.normalizatedRatings = normalizatedRatings;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	public double getTempRating() {
		return tempRating;
	}

	public void setTempRating(double tempRating) {
		this.tempRating = tempRating;
	}

	public double getNormalizatedRatings() {
		return normalizatedRatings;
	}

	public void setNormalizatedRatings(double normalizatedRatings) {
		this.normalizatedRatings = normalizatedRatings;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

	public double getNormalization1() {
		return normalization1;
	}

	public void setNormalization1(double normalization1) {
		this.normalization1 = normalization1;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "RecommendData [id=" + id + ", userId=" + userId + ", packageId=" + packageId + ", packageName="
				+ packageName + ", tempRating=" + tempRating + ", time=" + time + ", normalization1=" + normalization1
				+ ", normalizatedRatings=" + normalizatedRatings + "]";
	}


	

	

	
	

	
	
	
	
	

}
