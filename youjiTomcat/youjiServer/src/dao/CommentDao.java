package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Comment;

public class CommentDao {

	// 1.根据动态Id获得所有的评论
	public static JSONArray getCommentByDynamicId(int dynamic_id) {
		Connection connection = DataBase.getConnection();
		JSONArray array = new JSONArray();
		String sql = "select * from comment where comment_dynamic_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				JSONObject object = new JSONObject();
				object.put("id", result.getInt("comment_id"));
				object.put("text", result.getString("comment_text"));
				object.put("time", result.getTimestamp("comment_time"));
				object.put("dynamic_id", result.getInt("comment_dynamic_id"));
				object.put("like_num", result.getInt("comment_like_num"));
				object.put("user_id", result.getInt("comment_user_id"));
				array.put(object);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}

	// 2.添加一条评论
	public static boolean addComment(Comment comment) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Connection connection = DataBase.getConnection();
		String sql = "insert into comment(" + "comment_text,comment_dynamic_id,"
				+ "comment_like_num,comment_user_id,comment_time values(?,?,?,?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, comment.getText());
			prepareStatement.setInt(2, comment.getDynamic_id());
			prepareStatement.setInt(3, comment.getLike_num());
			prepareStatement.setInt(4, comment.getUser_id());
			prepareStatement.setTimestamp(5, timestamp);

			boolean result = prepareStatement.execute();
			connection.close();
			//给动态评论数+1
			addOneCommentToDynamic(comment.getDynamic_id());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 3.给评论点赞数+1
	public static boolean addOnelikeToComment(int comment_id) {
		Connection connection = DataBase.getConnection();

		String sql = "select comment_like_num from comment where comment_id=?";
		String sql2 = "update comment set comment_like_num=? where comment_id=?";
		int num = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				num = result.getInt("comment_like_num");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			num = num + 1;
			preparedStatement2.setInt(1, num);
			preparedStatement2.setInt(2, comment_id);
			preparedStatement2.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// 4.给动态的评论数+1
	public static boolean addOneCommentToDynamic(int dynamic_id) {
		Connection connection = DataBase.getConnection();

		String sql = "select dynamic_comment_num from dynamic where dynamic_id=?";
		String sql2 = "update dynamic set dynamic_comment_num=? where dynamic_id=?";
		int num = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dynamic_id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				num = result.getInt("dynamic_comment_num");
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
	//5.通过用户Id获得一个comment对象
	public static JSONArray getCommentByUserId(int user_id) {
		
		Connection connection = DataBase.getConnection();
		JSONArray array = new JSONArray();
		String sql = "select * from comment where comment_user_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				JSONObject object = new JSONObject();
				object.put("id", result.getInt("comment_id"));
				object.put("text", result.getString("comment_text"));
				object.put("dynamic_id", result.getInt("comment_dynamic_id"));
				object.put("time", result.getTimestamp("comment_time"));
				object.put("like_num", result.getInt("comment_like_num"));
				object.put("user_id", result.getInt("comment_user_id"));
				array.put(object);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
		
	}

}
