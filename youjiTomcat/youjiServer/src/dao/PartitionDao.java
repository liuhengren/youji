package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Dynamic;
import bean.Partition;

public class PartitionDao {

	//获得分区
	 public static String getPartition(int partition_id) {
		 Connection connection=DataBase.getConnection();
		 String partition_text=null;
			String sql="select partition_text from partition where partition_id=?";
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, partition_id);
				
				ResultSet result=preparedStatement.executeQuery();
				while(result.next()) {
				
					partition_text = result.getString("partition_text");
				
					}
				System.out.println("您获得的分区是："+partition_text);
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return partition_text;
		}
	
}
