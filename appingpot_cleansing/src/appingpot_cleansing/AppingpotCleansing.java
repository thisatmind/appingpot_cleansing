package appingpot_cleansing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;

import VO.AppLogRawData;
import VO.RecommendData;

public class AppingpotCleansing {
	/*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/appingpot_db";

	static final String USERNAME = "root";
	static final String PASSWORD = "apmsetup";*/
	public static void main(String[] args) {
		
	}
	
	
	public static void cleansing() {
		Connection conn = null;
		Statement stmt = null;
		LinkedList<AppLogRawData> allLogRawDataList = new LinkedList<AppLogRawData>();
		LinkedList<RecommendData> recommendDataList1 = new LinkedList<RecommendData>();
		LinkedList<RecommendData> recommendDataList2 = new LinkedList<RecommendData>();
		java.sql.PreparedStatement pstmt = null;
		LinkedList<String> deleteIds = new LinkedList<String>();
		

		try {
			conn = DBConnector.getConnection();
			System.out.println("\n- MySQL Connection");
			stmt = conn.createStatement();
			

			String selectedSql;
			selectedSql = "SELECT al.id,"
					+ "ia.userId,"
					+ "ia.packageName,"
					+ "al.dailyUsageTime,"
					+ "al.dailyCount,"
					+ "al.createdDate "
					+ "from raw_app_log as al,installed_app as ia "
					+ "where al.installedAppId=ia.id;";
			ResultSet rs = stmt.executeQuery(selectedSql);

			while (rs.next()) {
				String id = rs.getString("id");
				String userId=rs.getString("userId");
				String packageName = rs.getString("packageName");
				String dailyUsageTime = rs.getString("dailyUsageTime");
				int dailyCount = rs.getInt("dailyCount");
				String createdDate = rs.getString("createdDate");
				deleteIds.add(id);
				
				allLogRawDataList.add(new AppLogRawData(id, userId,packageName, dailyUsageTime, dailyCount, createdDate));
				recommendDataList1 = calcurateRatings(allLogRawDataList, dailyCount);
				//command.setUpdatedTime(rs.getTimestamp("updated_time"));
				System.out.println("삭제합니다");
				for(int z=0;z<deleteIds.size();z++){
					System.out.println(deleteIds.get(z));
				}
			}

			for (int s = 0; s < recommendDataList1.size(); s++) {
				System.out.println(recommendDataList1.get(s).toString());
			}
			recommendDataList2=normalizeRating(recommendDataList1);
			
			
			/*
			 * normalized된 데이터를 삽입하기 위해선 packageId가 필요한데 android_app_market_info TB에서 얻어올수있다.
			 * 만약에 여기에 신규앱이라 해당 data가 없으면 42matters에 api를 요청해서 해당 앱에 대한 마켓정보를 얻어와야한다.
			 */
			String androidMarketDBSelectSql=null;
			String packageId=null;
			
			int marketSelectResultCount=0;
			for(int s=0;s<recommendDataList2.size();s++){
				marketSelectResultCount=0;
				androidMarketDBSelectSql="select packageId from android_app_market_info where pakcageName=?";
				pstmt=conn.prepareStatement(androidMarketDBSelectSql);
				pstmt.setString(1,recommendDataList2.get(s).getPackageName());
				rs=null;
				rs= pstmt.executeQuery();
				
				if(rs.next()==false){
					//42matter에서 요청해서 새로 디비갱신하고 packageIdset
					System.out.println("rs.next()==false");
				} else {
					rs.beforeFirst();
					while (rs.next()) {
						packageId=rs.getString("packageId");
						System.out.println("insert하려는 packageId :" + packageId);
						recommendDataList2.get(s).setPackageId(packageId);
					}
				}

			}
			
			
			
			/*
			 * normalized된 데이터삽입
			 * userId,packageId,rating,time
			 */
			int count=0;
			for(int k=0;k<recommendDataList2.size();k++){
				String insertSql="insert into normalized_app_log(userId,packageId,rating) values(?, ?, ?)";
				pstmt=conn.prepareStatement(insertSql);
				pstmt.setInt(1,Integer.parseInt(recommendDataList2.get(k).getUserId()));
	            pstmt.setInt(2, Integer.parseInt(recommendDataList2.get(k).getPackageId()));
	            pstmt.setDouble(3, recommendDataList2.get(k).getTempRating());
	            count += pstmt.executeUpdate();
				
			}
			System.out.println("성공한 insert의 개수:"+count);
			
            
			/*
			 * 삽입된 데이터들 삭제.
			 */

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se1) {
			se1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				else {

				}
			} catch (SQLException se2) {
			}
			try{
				if(conn!=null)
					conn.close();
				else{
					
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("\n\n- MySQL Connection Close");
	}
	public static LinkedList<RecommendData> normalizeRating(LinkedList<RecommendData> list){
		//해당 행의 값을 각각 제곱하여 모두 더한 값의 제곱근 z를 구함
		
				//1. 정규화전의 임시 선호점수를 배열로만들어 제곱근을 해줌.
				double[] normalList=null;
				//double[] normalList=new double[]{3,2,1};
				normalList =new double[list.size()];
				for(int s=0;s<list.size();s++){
					
					//System.out.println("ratings:"+list.get(s).getTempRating());
					normalList[s]=Math.pow(list.get(s).getTempRating(), 2);
					
					
					//normalList[s]=Math.pow(normalList[s], 2);ㅈ
					
					System.out.println("nomalList:"+normalList[s]);
				}
				//2. 제곱근한것을 더하고 루트를 씌워 z값을 구한다.
				double totalZ=0;
				double resultZ=0;
				for(int a=0;a<normalList.length;a++){
					totalZ+=normalList[a];
					
					System.out.println("ff:"+normalList[a]);
					System.out.println("현재toatlZ:"+totalZ);
				}
				
				resultZ=Math.sqrt(totalZ);
				//z을 가지고 해당 행의 값을 나누면 정규화된 데이터를 구함
				System.out.println("resultZ:"+resultZ);
				for(int k=0;k<list.size();k++){
					list.get(k).setTempRating((double)(list.get(k).getTempRating()/resultZ));
				}
				//정규환 한 값으로 각가의 엘리먼트를 나누는 것까지함.
				
				for(int z=0;z<list.size();z++)
					System.out.println(list.get(z));
				
				return list;
		
	}
	public static LinkedList<RecommendData> calcurateRatings(LinkedList<AppLogRawData> list,int dailyCount){
		LinkedList<RecommendData> recommendDataList =new LinkedList<RecommendData>();
		int[] dailyUsageTimeNumber =null;
		
		for(int i=0;i<list.size();i++){
			String usageTime=list.get(i).getDailyUsageTime();
			String[] dailyUsageTimeList = usageTime.split(",");
			dailyUsageTimeNumber= new int[dailyUsageTimeList.length];
			//from String[] to int[] 
			for(int j = 0; j<dailyUsageTimeList.length; j++){
				dailyUsageTimeNumber[j] = Integer.parseInt(dailyUsageTimeList[j]);
			}
			//int[]의 총 합 구하기
			int totalTime=0;
			for(int z = 0; z<dailyUsageTimeNumber.length; z++){
				totalTime+=dailyUsageTimeNumber[z];
			}
			System.out.println("--------------------------");
			System.out.println(i+":"+totalTime);
			System.out.println("--------------------------");
			recommendDataList.add(new RecommendData(list.get(i).getId(),list.get(i).getUserId(),list.get(i).getPackageName(),
					(double)totalTime/dailyCount,list.get(i).getCreatedDate()));
		}
		
		/*//해당 행의 값을 각각 제곱하여 모두 더한 값의 제곱근 z를 구함
		
		//1. 정규화전의 임시 선호점수를 배열로만들어 제곱근을 해줌.
		double[] normalList=null;
		for(int s=0;s<recommendDataList.size();s++){
			normalList =new double[recommendDataList.size()];
			normalList[s]=Math.pow(recommendDataList.get(s).getTempRating(), 2);
		}
		//2. 제곱근한것을 더하고 루트를 씌워 z값을 구한다.
		double totalZ=0;
		for(int a=0;a<normalList.length;a++){
			totalZ+=normalList[a];
		}
		totalZ=Math.sqrt(totalZ);
		//z을 가지고 해당 행의 값을 나누면 정규화된 데이터를 구함
		System.out.println("totalZ:"+totalZ);*/
		for(int b=0;b<recommendDataList.size();b++){
			
		}
		
		
		return recommendDataList;
		
		
	}
	
	/*public static int deleteRating(LinkedList<String> ids){
		
	}*/

}
