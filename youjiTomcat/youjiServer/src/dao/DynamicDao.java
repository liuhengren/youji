package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.Dynamic;

public class DynamicDao {
	
	
	//1.DynamicDao 查找所有
	public static List<bean.Dynamic> getDynamic() {
		
		Connection connection=DataBase.getConnection();
		List <Dynamic> allDaynamic=new ArrayList<Dynamic>();
		String sql="select * from dynamic";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				Dynamic dynamic=new Dynamic(
						result.getInt("dynamic_id"),
						result.getInt("dynamic_user_id"),
						result.getString("dynamic_text"),
						result.getString("dynamic_img"),
						result.getInt("dynamic_collection_num"),
						result.getInt("dynamic_like_num"),
						result.getInt("dynamic_comment_num"),
						result.getString("dynamic_address"),
						result.getDate("dynamic_time"),
						result.getInt("dynamic_partition_id")
						);
				allDaynamic.add(dynamic);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allDaynamic;
	}
	
	
	//2.插入一条动态
	private static boolean addDynamic(Dynamic dynamic) {
		Connection connection=DataBase.getConnection();
		
		String sql="insert into dynamic("
				+ "dynamic_user_id,dynamic_text,dynamic_img,"
				+ "dynamic_collection_num,dynamic_like_num,dynamic_comment_num,"
				+ "dynamic_address,dynamic_time,dynamic_partition_id) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, dynamic.getUser_id());
			prepareStatement.setString(2, dynamic.getText());
			prepareStatement.setString(3, dynamic.getImg());
			prepareStatement.setInt(4, dynamic.getCollection_num());
			prepareStatement.setInt(5, dynamic.getLike_num());
			prepareStatement.setInt(6, dynamic.getComment_num());
			prepareStatement.setString(7,dynamic.getAddress());
			prepareStatement.setDate(8, dynamic.getTime());
			prepareStatement.setInt(9, dynamic.getPartition_id());
			
			boolean result = prepareStatement.execute();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	} 
	
	
	//3.通过分区查找动态
	public  static List<Dynamic> getDynamicByPartitionId(int partition_id) {
		Connection connection=DataBase.getConnection();
		List <Dynamic> allPartitionDaynamic=new ArrayList<Dynamic>();
		String sql="select * from dynamic where dynamic_partition_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, partition_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				Dynamic dynamic=new Dynamic(
						result.getInt("dynamic_id"),
						result.getInt("dynamic_user_id"),
						result.getString("dynamic_text"),
						result.getString("dynamic_img"),
						result.getInt("dynamic_collection_num"),
						result.getInt("dynamic_like_num"),
						result.getInt("dynamic_comment_num"),
						result.getString("dynamic_address"),
						result.getDate("dynamic_time"),
						result.getInt("dynamic_partition_id")
						);
				allPartitionDaynamic.add(dynamic);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allPartitionDaynamic;
		
	}
	
	//4.根据用户Id查找动态
	public static List<Dynamic> getDynamicByUserId(int user_id){
		Connection connection=DataBase.getConnection();
		List <Dynamic> allUserDaynamic=new ArrayList<Dynamic>();
		String sql="select * from dynamic where dynamic_user_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				Dynamic dynamic=new Dynamic(
						result.getInt("dynamic_id"),
						result.getInt("dynamic_user_id"),
						result.getString("dynamic_text"),
						result.getString("dynamic_img"),
						result.getInt("dynamic_collection_num"),
						result.getInt("dynamic_like_num"),
						result.getInt("dynamic_comment_num"),
						result.getString("dynamic_address"),
						result.getDate("dynamic_time"),
						result.getInt("dynamic_partition_id")
						);
				allUserDaynamic.add(dynamic);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allUserDaynamic;
	}
	
	//5.通过动态Id获得动态
	public static  Dynamic getDynamicById(int dynamic_id) {
		Connection connection=DataBase.getConnection();
		Dynamic dynamic=null;
		String sql="select * from dynamic where dynamic_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				 dynamic=new Dynamic(
						result.getInt("dynamic_id"),
						result.getInt("dynamic_user_id"),
						result.getString("dynamic_text"),
						result.getString("dynamic_img"),
						result.getInt("dynamic_collection_num"),
						result.getInt("dynamic_like_num"),
						result.getInt("dynamic_comment_num"),
						result.getString("dynamic_address"),
						result.getDate("dynamic_time"),
						result.getInt("dynamic_partition_id")
						);
			
				}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dynamic;
	}

	//6.通过Id删除一条动态
	  public static boolean deleteDynamic(int Dynamic_id) {
		  
		  Connection connection=DataBase.getConnection();
			Dynamic dynamic=null;
			String sql="delete  from dynamic where dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, Dynamic_id);
				
				preparedStatement.executeUpdate();
			System.out.println("删除成功！");
				connection.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	  }

	  //7.根据热度获得动态
	  public List<Dynamic> getDynamicOrderHot(){
		  return null;
	 }
}
