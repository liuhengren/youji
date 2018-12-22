package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.Dynamic;
import dao.DynamicDao;

/**
 * Servlet implementation class Dynamicservlet
 */
@WebServlet("/DynamicServlet")
public class DynamicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DynamicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		PrintWriter out=response.getWriter();
		//1.DynamicDao 查找所有
		if("dynamic_allDynamic".equals(message)) {
			
			JSONArray array=DynamicDao.getDynamic();
			response.getWriter().append(array.toString());
			
		}
		//2.插入一条动态
		else if("dynamic_addDynamic".equals(message)) 
		{
			
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(request.getInputStream()));
			JSONObject oJsonObject=new JSONObject(bufferedReader.readLine());
			Dynamic dynamic=new Dynamic();
			
			bufferedReader.close();
			out.append("奥出我要去吃饭了");
			
			DynamicDao.addDynamic(dynamic);
			
		}
		//3.通过分区查找动态
		else if("dynamic_getDynamicByPartitionId".equals(message))
		{
			String id=request.getParameter("id");
			int partition_id=Integer.parseInt(id);
			JSONArray array=DynamicDao.getDynamicByPartitionId(partition_id);
			
			out.write(array.toString());
			
			
			
		}
		//4.根据用户Id查找动态
		else if("dynamic_getDynamicByUserId".equals(message))
		{
			String id=request.getParameter("id");
			int user_id=Integer.parseInt(id);
			JSONArray array=DynamicDao.getDynamicByUserId(user_id);
			
			out.write(array.toString());
			
			
		}
		//5.通过动态Id获得动态
		else if("dynamic_getDynamicById".equals(message))
		{
			String id=request.getParameter("id");
			int dynamic_id=Integer.parseInt(id);
			JSONObject object=DynamicDao.getDynamicById(dynamic_id);
			out.write(object.toString());
			
		}
		//6.通过Id删除一条动态
		else if("dynamic_deleteDynamic".equals(message))
		{
			String id=request.getParameter("id");
			int Dynamic_id=Integer.parseInt(id);

			boolean result = DynamicDao.deleteDynamic(Dynamic_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
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
