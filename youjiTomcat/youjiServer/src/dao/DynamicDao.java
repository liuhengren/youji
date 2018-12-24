package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Dynamic;

public class DynamicDao {
	
	
	//1.DynamicDao 获得所有动态
	public static JSONArray getDynamic() {
		
		Connection connection=DataBase.getConnection();
		JSONArray array=new JSONArray();
		String sql="select * from dynamic";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				JSONObject object=new JSONObject();
				
						object.put("id", result.getInt("dynamic_id"));
						object.put("user_id", result.getInt("dynamic_user_id"));
						object.put("text", result.getString("dynamic_text"));
						object.put("img", result.getString("dynamic_img"));
						object.put("collection_num", result.getInt("dynamic_collection_num"));
						object.put("like_num", result.getInt("dynamic_like_num"));
						object.put("comment_num", result.getInt("dynamic_comment_num"));
						object.put("address", result.getString("dynamic_address"));
						object.put("time", result.getDate("dynamic_time"));
						object.put("partition_id", result.getInt("dynamic_partition_id"));
					
				array.put(object);
								
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
	
	
	//2.插入动态内容（文字）
		public static int addDynamic(Dynamic dynamic) {
			Connection connection=DataBase.getConnection();
			
			int id=0;
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		
			System.out.println(timestamp);
			String sql="insert into dynamic("
					+ "dynamic_user_id,dynamic_text,"
					+ "dynamic_collection_num,dynamic_like_num,dynamic_comment_num,"
					+ "dynamic_address,dynamic_time,dynamic_partition_id) "
					+ "values(?,?,?,?,?,?,?,?)";
			String sql2="select dynamic_id from dynamic where dynamic_time=?";
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, dynamic.getUser_id());
				prepareStatement.setString(2, dynamic.getText());
				prepareStatement.setInt(3, 0);
				prepareStatement.setInt(4,0);
				prepareStatement.setInt(5, 0);
				prepareStatement.setString(6,dynamic.getAddress());
				prepareStatement.setTimestamp(7, timestamp);
				prepareStatement.setInt(8, dynamic.getPartition_id());
				prepareStatement.execute();
				
				PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
				prepareStatement2.setTimestamp(1, timestamp);
				 prepareStatement2.executeQuery();
				 ResultSet result=prepareStatement2.executeQuery();
					while(result.next()) {
						id=result.getInt("dynamic_id");
					}
				connection.close();
				
				return id;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
		} 
		
		
		//3.插入动态图片
		public  static boolean insertDynamicImage(int id,String img) {
			Connection connection=DataBase.getConnection();
			String sql="insert into dynamic(dynamic_img) values(?) where dynamic_id=?";
			PreparedStatement prepareStatement;
			try {
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setString(1, img);
				prepareStatement.setInt(2, id);
				 prepareStatement.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
	
	
	//4.ͨ通过分类Id获得动态
	public  static JSONArray getDynamicByPartitionId(int partition_id) {
		Connection connection=DataBase.getConnection();
		JSONArray array=new JSONArray();
		String sql="select * from dynamic where dynamic_partition_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, partition_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				JSONObject object=new JSONObject();
				
				object.put("id", result.getInt("dynamic_id"));
				object.put("user_id", result.getInt("dynamic_user_id"));
				object.put("text", result.getString("dynamic_text"));
				object.put("img", result.getString("dynamic_img"));
				object.put("collection_num", result.getInt("dynamic_collection_num"));
				object.put("like_num", result.getInt("dynamic_like_num"));
				object.put("comment_num", result.getInt("dynamic_comment_num"));
				object.put("address", result.getString("dynamic_address"));
				object.put("time", result.getDate("dynamic_time"));
				object.put("partition_id", result.getInt("dynamic_partition_id"));
			
				array.put(object);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
		
	}
	
	//5.通过用户Id获得动态
	public static JSONArray getDynamicByUserId(int user_id){
		Connection connection=DataBase.getConnection();
		JSONArray array=new JSONArray();
		String sql="select * from dynamic where dynamic_user_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				JSONObject object=new JSONObject();
				
				object.put("id", result.getInt("dynamic_id"));
				object.put("user_id", result.getInt("dynamic_user_id"));
				object.put("text", result.getString("dynamic_text"));
				object.put("img", result.getString("dynamic_img"));
				object.put("collection_num", result.getInt("dynamic_collection_num"));
				object.put("like_num", result.getInt("dynamic_like_num"));
				object.put("comment_num", result.getInt("dynamic_comment_num"));
				object.put("address", result.getString("dynamic_address"));
				object.put("time", result.getDate("dynamic_time"));
				object.put("partition_id", result.getInt("dynamic_partition_id"));
			
				array.put(object);
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
	
	//6.ͨ通过Id查询动态
	public static  JSONObject getDynamicById(int dynamic_id) {
		Connection connection=DataBase.getConnection();
		JSONObject object=new JSONObject();
		String sql="select * from dynamic where dynamic_id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {
				object.put("id", result.getInt("dynamic_id"));
				object.put("user_id", result.getInt("dynamic_user_id"));
				object.put("text", result.getString("dynamic_text"));
				object.put("img", result.getString("dynamic_img"));
				object.put("collection_num", result.getInt("dynamic_collection_num"));
				object.put("like_num", result.getInt("dynamic_like_num"));
				object.put("comment_num", result.getInt("dynamic_comment_num"));
				object.put("address", result.getString("dynamic_address"));
				object.put("time", result.getDate("dynamic_time"));
				object.put("partition_id", result.getInt("dynamic_partition_id"));
				
				 
				
				}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}

	//7.ͨ删除一条动态
	  public static boolean deleteDynamic(int Dynamic_id) {
		  
		  Connection connection=DataBase.getConnection();
			Dynamic dynamic=null;
			String sql="delete  from dynamic where dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, Dynamic_id);
				
				preparedStatement.executeUpdate();
			System.out.println("ɾ���ɹ���");
				connection.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	  }

	  //8.查看当前动态是否被收藏
	  public static boolean isCollected(int user_id,int  dynamic_id) {
			Connection connection=DataBase.getConnection();
			
			String sql="select * from collection where collection_user_id=? and collection_dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, dynamic_id);
				ResultSet result=preparedStatement.executeQuery();
				
				while(result.next()) {
					return true;
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;	 
			}
	//9.查看当前动态是否被点赞
	  
	  public static boolean isLikeuped(int user_id,int  dynamic_id) {
			Connection connection=DataBase.getConnection();
			
			String sql="select * from likeup where likeup_user_id=? and likeup_dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, dynamic_id);
				ResultSet result=preparedStatement.executeQuery();
				
				while(result.next()) {
					return true;
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;	 
			}
	    
	  
	  //9.�����ȶȻ�ö�̬
	  public List<Dynamic> getDynamicOrderHot(){
		  return null;
	 }
	  
	 
}
