package VO;

public class AppLogRawData {
	private String id;
	private String userId;
	private String packageName;
	private String dailyUsageTime;
	private int dailyCount;
	private String createdDate;
	public AppLogRawData() {
		// TODO Auto-generated constructor stub
	}
	public AppLogRawData(String id, String packageName, String dailyUsageTime, int dailyCount, String createdDate) {
		this.id = id;
		this.packageName = packageName;
		this.dailyUsageTime = dailyUsageTime;
		this.dailyCount = dailyCount;
		this.createdDate = createdDate;
	}
	
	public AppLogRawData(String id, String userId, String packageName, String dailyUsageTime, int dailyCount,
			String createdDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.packageName = packageName;
		this.dailyUsageTime = dailyUsageTime;
		this.dailyCount = dailyCount;
		this.createdDate = createdDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getDailyUsageTime() {
		return dailyUsageTime;
	}
	public void setDailyUsageTime(String dailyUsageTime) {
		this.dailyUsageTime = dailyUsageTime;
	}
	public int getDailyCount() {
		return dailyCount;
	}
	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AppLogRawData [id=" + id + ", userId=" + userId + ", packageName=" + packageName + ", dailyUsageTime="
				+ dailyUsageTime + ", dailyCount=" + dailyCount + ", createdDate=" + createdDate + "]";
	}
	
	


}
