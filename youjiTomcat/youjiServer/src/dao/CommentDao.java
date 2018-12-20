package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;


public class CommentDao {
	
	//1.获取该动态的所有评论
	 public static List<Comment> getCommentByDynamicId(int dynamic_id)
	 {
		 Connection connection=DataBase.getConnection();
			List <Comment> allCommentByDynamicId=new ArrayList<Comment>();
			String sql="select * from comment where comment_dynamic_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, dynamic_id);
				ResultSet result=preparedStatement.executeQuery();
				
				while(result.next()) {
					Comment comment=new Comment(
							result.getInt("comment_id"),
							result.getString("comment_text"),
							result.getInt("comment_dynamic_id"),
							result.getInt("comment_like_num"),
							result.getInt("comment_user_id"));
					allCommentByDynamicId.add(comment);
					
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return allCommentByDynamicId;
	 }
	 
	 //2.插入一条评论
	   public static  boolean addComment(Comment comment)
	   {
		   Connection connection=DataBase.getConnection();
		   String sql="insert into comment("
				+ "comment_text,comment_dynamic_id,"
				+ "comment_like_num,comment_user_id,values(?,?,?,?)";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, comment.getText());
			prepareStatement.setInt(2, comment.getDynamic_id());
			prepareStatement.setInt(3, comment.getLike_num());
			prepareStatement.setInt(4, comment.getUser_id());
			
			boolean result = prepareStatement.execute();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	} 
	   
	   //3.给该评论点赞+1
	   public static  boolean likeComment(int comment_id)
	    {
		   Connection connection=DataBase.getConnection();
			 
			 	String sql="select comment_like_num from comment where comment_id=?";
				String sql2="update comment set comment_like_num=? where comment_id=?";
				int num=0;
				try {
					PreparedStatement preparedStatement=connection.prepareStatement(sql);
					preparedStatement.setInt(1, comment_id);
					ResultSet result=preparedStatement.executeQuery();
					while(result.next()) {
						num=result.getInt("comment_like_num");
					}
					PreparedStatement preparedStatement2=connection.prepareStatement(sql2);
					num=num+1;
					preparedStatement2.setInt(1, num);
					preparedStatement2.setInt(2, comment_id);
					preparedStatement2.executeUpdate();
					connection.close();
					System.out.println("点赞完成！");
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
				
	    }

}
