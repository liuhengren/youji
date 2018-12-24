package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeupDao {

	// 1.添加一条点赞
	public static boolean addLikeup(int user_id, int dynamic_id) {
		Connection connection = DataBase.getConnection();

		String sql = "insert into likeup(likeup_user_id,likeup_dynamic_id) values(?,?)";

		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, user_id);
			prepareStatement.setInt(2, dynamic_id);

			connection.close();

			// 给动态点赞数+1
			addOneLikeToDynamic(dynamic_id);

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return false;
	}
	// 2.删除一条点赞

	public static boolean deleteLikeup(int user_id, int dynamic_id) {
		Connection connection = DataBase.getConnection();

		String sql = "delete  from likeup where likeup_user_id=? and likeup_dynamic_id=? ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, dynamic_id);
			preparedStatement.executeUpdate();
			connection.close();
			// 给动态的点赞数-1
			deleteOneLikeToDynamic(dynamic_id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// 3.给动态的点赞数+1
	public static boolean addOneLikeToDynamic(int dynamic_id) {
		Connection connection = DataBase.getConnection();

		String sql = "select dynamic_like_num from dynamic where dynamic_id=?";
		String sql2 = "update dynamic set dynamic_like_num=? where dynamic_id=?";
		int num = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				num = result.getInt("dynamic_like_num");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			num = num + 1;
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

	// 3.给动态的点赞数-1
	public static boolean deleteOneLikeToDynamic(int dynamic_id) {
		Connection connection = DataBase.getConnection();

		String sql = "select dynamic_like_num from dynamic where dynamic_id=?";
		String sql2 = "update dynamic set dynamic_like_num=? where dynamic_id=?";
		int num = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				num = result.getInt("dynamic_like_num");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			num = num - 1;
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
