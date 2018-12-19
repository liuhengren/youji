package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.Partition;
import dao.PartitionDao;

/**
 * Servlet implementation class PartitionServlet
 */
@WebServlet("/PartitionServlet")
public class PartitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartitionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("有人访问");
//		response.setCharacterEncoding("utf-8");
//		String message=request.getParameter("message");
//		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//		StringBuffer sb = new StringBuffer("");
//		String temp;
//		while((temp = br.readLine()) != null){
//			sb.append(temp);
//		}
//		br.close();
//		String reqMessage = sb.toString();
//		System.out.println("请求报文:" + reqMessage);
//		
//		JSONObject jsonObject = new JSONObject(reqMessage);
//		String string = jsonObject.getString("partition_text");
//	
//		System.out.println(string);
		
		response.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		
		//1.获得分区
		if("partition_getPartition".equals(message)) {
			
			String id=request.getParameter("id");
			int partition_id=Integer.parseInt(id);
			
			PartitionDao.getPartition(partition_id);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
