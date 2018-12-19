package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDao {

	// 1.通过手机号注册
	public void login(String userphone, String password) {
		Connection connection = DataBase.getConnection();

		String sql = "insert into user(user_phone,user_password)values(?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(0, userphone);
			prepareStatement.setString(1, password);
			boolean result = prepareStatement.execute();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2.通过手机号登录
	public boolean logup(String userphone, String password) {
		
		Connection connection = DataBase.getConnection();
		String sql = "select * from user where user_phone=?,user_password=?";
		boolean judge=false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(0, userphone);
			preparedStatement.setString(1, password);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				judge=true;
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return judge;

	}

	// 3.通过用户Id获得用户
	public static User getUserById(int user_id) {
		Connection connection = DataBase.getConnection();
		String sql = "select * from user where user_id=?";
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, user_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				user = new User(result.getInt("user_id"), result.getString("user_phone"), "",
						result.getString("user_name"), result.getString("user_sex"), result.getDate("user_birthday"),
						result.getString("user_address"), result.getInt("user_funnum"),
						result.getInt("user_collection_num"), result.getString("user_touxiang_url"),
						result.getString("user_background_url"), result.getString("user_introduction"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
	
	//4.修改用户名
	  public static void updateUsername(int user_id,String username) {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_name=? where user_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, user_id);
				preparedStatement.setString(1, username);
				preparedStatement.executeUpdate(sql);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	  }
	  
	  //5.修改头像
	  public static void updateUsertouxiang(int user_id,String img){
		 
	    }
	    //6.修改性别
	  public static void updateUserSex(int user_id,String userSex)
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_sex=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, user_id);
				preparedStatement.setString(1, userSex);
				preparedStatement.executeUpdate(sql);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
	    //7.修改简介
	  public  static void updateUserIntroduction(int user_id,String userIntroduction)
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_introduction=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, user_id);
				preparedStatement.setString(1, userIntroduction);
				preparedStatement.executeUpdate(sql);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
	    //8.修改生日
	  public static void updateUserBirthday(int user_id,Date userBirthday)
	    {

		  Connection connection=DataBase.getConnection();
			String sql="update user set user_birthday=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, user_id);
				preparedStatement.setDate(1, userBirthday);
				preparedStatement.executeUpdate(sql);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }
	    //9.修改家乡
	  public static void updateUserHometown(int user_id,String userHometown) 
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_address=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(0, user_id);
				preparedStatement.setString(1, userHometown);
				preparedStatement.executeUpdate(sql);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }

}
