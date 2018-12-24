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

	// 1.ͨ���ֻ���ע��
	public static boolean logup(String userphone, String password) {
		Connection connection = DataBase.getConnection();

		String sql = "insert into user(user_phone,user_password)values(?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, userphone);
			prepareStatement.setString(2, password);
			prepareStatement.execute();
			System.out.println("�ֻ���ע��ɹ���");
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 2.ͨ���ֻ��ŵ�¼
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
		System.out.println("�ֻ��ŵ�¼��"+judge);
		return judge;

	}

	// 3.ͨ���û�Id����û�
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
	
	//4.�޸��û���
	  public static boolean updateUsername(int user_id,String username) {
		  Connection connection=DataBase.getConnection();
			String sql="update user set user_name=? where user_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				
				preparedStatement.setString(1, username);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
				connection.close();
				System.out.println("�޸��û����ɹ���");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	  }
	  
	  //5.�޸�ͷ��
	  public static void updateUsertouxiang(int user_id,String img){
		 
	    }
	    //6.�޸��Ա�
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
				System.out.println("�޸��Ա�ɹ���");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //7.�޸ļ��
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
				System.out.println("�޸ļ��ɹ���");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //8.�޸�����
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
				System.out.println("�޸����ճɹ���");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	    //9.�޸ļ���
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
				System.out.println("�޸ļ���ɹ���");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return false;
	    }
	  //10.ͨ���û��ֻ��Ų�ѯ�û�������Ϣ
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
