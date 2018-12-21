package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Dynamic;

public class DynamicDao {
	
	
	//1.DynamicDao ��������
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
	
	
	//2.����һ����̬(ֻ������)
		public static int addDynamic(Dynamic dynamic) {
			Connection connection=DataBase.getConnection();
			
			String sql="insert into dynamic("
					+ "dynamic_user_id,dynamic_text,"
					+ "dynamic_collection_num,dynamic_like_num,dynamic_comment_num,"
					+ "dynamic_address,dynamic_time,dynamic_partition_id) "
					+ "values(?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, dynamic.getUser_id());
				prepareStatement.setString(2, dynamic.getText());
				prepareStatement.setInt(4, dynamic.getCollection_num());
				prepareStatement.setInt(5, dynamic.getLike_num());
				prepareStatement.setInt(6, dynamic.getComment_num());
				prepareStatement.setString(7,dynamic.getAddress());
				prepareStatement.setDate(8, dynamic.getTime());
				prepareStatement.setInt(9, dynamic.getPartition_id());
				boolean result = prepareStatement.execute();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		} 
		
		
		//���붯̬ͼƬ 
		public  static boolean insertDynamicImage(int id,String img) {
			Connection connection=DataBase.getConnection();
			String sql="insert into dynamic(dynamic_img)values(?)";
			PreparedStatement prepareStatement;
			try {
				prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
		}
	
	
	//3.ͨ���������Ҷ�̬
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
	
	//4.�����û�Id���Ҷ�̬
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
	
	//5.ͨ����̬Id��ö�̬
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

	//6.ͨ��Idɾ��һ����̬
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

	  //7.�����ȶȻ�ö�̬
	  public List<Dynamic> getDynamicOrderHot(){
		  return null;
	 }
}
