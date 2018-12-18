package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Dynamic;


public class FollowDao {

	public FollowDao(int int1, int int2, int int3) {
		// TODO Auto-generated constructor stub
	}


	//1.通过用户Id获得他的关注者
	 public static  List<FollowDao> getFollowByUserId(int user_id){
			
			Connection connection=DataBase.getConnection();
			List <FollowDao> followList=new ArrayList<FollowDao>();
			String sql="select * from follow";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				ResultSet result=preparedStatement.executeQuery();
				while(result.next()) {
					FollowDao follow=new FollowDao(
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
	 public void addFollow(int user_id) {
		 Connection connection=DataBase.getConnection();
			
			String sql="insert into follow(user_id,follow_user_id)values(?,?)";
			try {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.setInt(0, dynamic.getUser_id());
				prepareStatement.setInt(1, dynamic.getCollection_num());
				
				
				boolean result = prepareStatement.execute();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	 
	 
	 //3.删除关注者
	 public void deleteFollow(int user_id) {
		  Connection connection=DataBase.getConnection();
			Dynamic dynamic=null;
			String sql="delete * from dynamic where dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, Dynamic_id);
				
				ResultSet result=preparedStatement.executeQuery(sql);
			
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  }
	
}
