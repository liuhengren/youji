package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Dynamic;
import bean.Follow;


public class FollowDao {

	


	//1.通过用户Id获得他的关注者
	 public static  List<Follow> getFollowByUserId(int user_id){
			
			Connection connection=DataBase.getConnection();
			List <Follow> followList=new ArrayList<Follow>();
			String sql="select * from follow where user_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				ResultSet result=preparedStatement.executeQuery();
				while(result.next()) {
					Follow follow=new Follow(
							result.getInt("follow_id"),
							result.getInt("user_id"),
							result.getInt("follow_user_id"));
					followList.add(follow);
				}
					
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return followList;	 
			}
	 
	 
	 //2.添加关注者
	 public static void addFollow(int user_id,int follow_user_id) {
		 Connection connection=DataBase.getConnection();
			
			String sql="insert into follow(user_id,follow_user_id)values(?,?)";
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(1, user_id);
				prepareStatement.setInt(2, follow_user_id);
				
				boolean result = prepareStatement.execute();
				System.out.println("添加关注:"+result);
				connection.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	 
	 
	 //3.删除关注者
	 public static void deleteFollow(int user_id,int follow_user_id) {
		  Connection connection=DataBase.getConnection();
		  
			String sql="delete from follow where user_id=? and follow_user_id=?";
			try {
				
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, follow_user_id);
				
				preparedStatement.executeUpdate();
			
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  }
	 
	 //4.判断是否被关注
	 public static boolean isFollow(int user_id,int follow_user_id) {
			Connection connection=DataBase.getConnection();
			boolean judge=false;
			String sql="select * from follow where user_id=? and follow_user_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, follow_user_id);
				ResultSet result=preparedStatement.executeQuery();
				
				while(result.next()) {
					judge= true;
				}
					System.out.println(judge+"被关注");
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return judge;	 
			}
	
}
