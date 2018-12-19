package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import bean.User;
import jdk.nashorn.api.scripting.JSObject;

public class UserDao {

	// 1.通过手机号注册
	public static boolean logup(String userphone, String password) {
		Connection connection = DataBase.getConnection();

		String sql = "insert into user(user_phone,user_password)values(?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userphone);
			prepareStatement.setString(2, password);
			prepareStatement.execute();
			System.out.println("手机号注册成功！");
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 2.通过手机号登录
	public static boolean login(String userphone, String password) {
		
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
		System.out.println("手机号登录："+judge);
		return judge;

	}

	// 3.通过用户Id获得用户
	public static User getUserById(int user_id) {
		Connection connection = DataBase.getConnection();
		String sql = "select * from user where user_id=?";
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
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
	  public static boolean updateUsername(int user_id,String username) {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_name=? where user_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setString(1, username);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("修改用户名成功！");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	  }
	  
	  //5.修改头像
	  public static void updateUsertouxiang(int user_id,String img){
		 
	    }
	    //6.修改性别
	  public static boolean updateUserSex(int user_id,String userSex)
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_sex=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, userSex);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("修改性别成功！");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //7.修改简介
	  public  static boolean updateUserIntroduction(int user_id,String userIntroduction)
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_introduction=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setString(1, userIntroduction);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("修改简介成功！");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //8.修改生日
	  public static boolean updateUserBirthday(int user_id,Date userBirthday)
	    {

		  Connection connection=DataBase.getConnection();
			String sql="update user set user_birthday=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setDate(1, userBirthday);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("修改生日成功！");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //9.修改家乡
	  public static boolean updateUserHometown(int user_id,String userHometown) 
	    {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_address=? where user_id=?";
		
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setString(1, userHometown);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("修改家乡成功！");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	  //10.通过用户手机号查询用户所有信息
	  public static JSONObject getUserDetail(String userphone)
	  {
		  Connection connection = DataBase.getConnection();
			String sql = "select * from user where user_phone=?";
			JSONObject object=null;
			User user = null;
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userphone);
				ResultSet result = preparedStatement.executeQuery();
				while (result.next()) {
					object=new JSONObject();
					object.put("user_id", result.getInt("user_id"));
					object.put("user_phone", result.getString("user_phone")); 
					object.put("user_name",result.getString("user_name")); 
					object.put("user_sex", result.getString("user_sex")); 
					object.put("user_birthday", result.getDate("user_birthday")); 
					object.put("user_address", result.getString("user_address")); 
					object.put("user_funnum", result.getInt("user_funnum")); 
					object.put("user_collection_num", result.getInt("user_collection_num")); 
					object.put("user_touxiang_url", result.getString("user_touxiang_url"));
					object.put("user_background_url", result.getString("user_background_url")); 
					object.put("user_introduction", result.getString("user_introduction"));
					object.put("user_password", ""); 
							
					
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return object;
		  
	  }


}
