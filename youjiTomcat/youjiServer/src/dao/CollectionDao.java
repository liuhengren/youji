package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Collection;
import bean.Dynamic;

public class CollectionDao {

	// 1.ͨ根据用户Id获得所有的收藏
	public static JSONArray getCollectionByUserId(int user_id) {
		Connection connection = DataBase.getConnection();
		JSONArray array=new JSONArray();
		String sql = "select * from collection where collection_user_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				JSONObject object=new JSONObject();
				object.put("id", result.getInt("collection_id"));
				object.put("user_id", result.getInt("collection_user_id"));
				object.put("dynamic_id", result.getInt("collection_dynamic_id"));
				array.put(object);

			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;

	}

	// 2.添加一条收藏
	public static boolean addCollection(int user_id, int dynamic_id) {
		Connection connection = DataBase.getConnection();
		
		String sql = "insert into collection(collection_user_id,collection_dynamic_id) values(?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, user_id);
			prepareStatement.setInt(2, dynamic_id);
			prepareStatement.execute();
			connection.close();
			//给动态的收藏数+1
			addOneCollectionToDynamic(dynamic_id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}

	//3.删除一条收藏
	public static boolean deleteCollection(int user_id, int dynamic_id) {
		
		
		Connection connection = DataBase.getConnection();
		
		String sql = "delete  from collection where collection_user_id=? and collection_dynamic_id=? ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, dynamic_id);
			 preparedStatement.executeUpdate();
			connection.close();
			//给动态点赞数-1
			deleteOneCollectionToDynamic(dynamic_id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	//4.给动态的收藏数+1
	public static  boolean addOneCollectionToDynamic(int dynamic_id){
			   Connection connection=DataBase.getConnection();
				 
				 	String sql="select dynamic_collection_num from dynamic where dynamic_id=?";
					String sql2="update dynamic set dynamic_collection_num=? where dynamic_id=?";
					int num=0;
					try {
						PreparedStatement preparedStatement=connection.prepareStatement(sql);
						preparedStatement.setInt(1, dynamic_id);
						ResultSet result=preparedStatement.executeQuery();
						while(result.next()) {
							num=result.getInt("dynamic_collection_num");
						}
						PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
						num=num+1;
						preparedStatement2.setInt(1, num);
						preparedStatement2.setInt(2, dynamic_id);
						preparedStatement2.executeUpdate();
						connection.close();
						return true;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
					
		    }

	//5.给动态的收藏数-1
		public static  boolean deleteOneCollectionToDynamic(int dynamic_id){
				   Connection connection=DataBase.getConnection();
					 
					 	String sql="select dynamic_collection_num from dynamic where dynamic_id=?";
						String sql2="update dynamic set dynamic_collection_num=? where dynamic_id=?";
						int num=0;
						try {
							PreparedStatement preparedStatement=connection.prepareStatement(sql);
							preparedStatement.setInt(1, dynamic_id);
							ResultSet result=preparedStatement.executeQuery();
							while(result.next()) {
								num=result.getInt("dynamic_collection_num");
							}
							PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
							num=num-1;
							preparedStatement2.setInt(1, num);
							preparedStatement2.setInt(2, dynamic_id);
							preparedStatement2.executeUpdate();
							connection.close();
							return true;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return false;
						
			    }

}
