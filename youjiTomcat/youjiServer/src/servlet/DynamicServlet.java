package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;



import bean.Dynamic;
import dao.DynamicDao;
import jdk.nashorn.api.scripting.JSObject;

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
		request.setCharacterEncoding("utf-8");
	
		PrintWriter out=response.getWriter();
		String message=request.getParameter("message");
		System.out.println("meassge::"+message);
		//1.DynamicDao 查找所有
		if("dynamic_allDynamic".equals(message)) {
			
			JSONArray array=DynamicDao.getDynamic();
			response.getWriter().append(array.toString());
			
		}
	
		//4.通过分区查找动态
		else if("dynamic_getDynamicByPartitionId".equals(message))
		{
			String id=request.getParameter("id");
			int partition_id=Integer.parseInt(id);
			JSONArray array=DynamicDao.getDynamicByPartitionId(partition_id);
			
			out.write(array.toString());
			
			
			
		}
		//5.根据用户Id查找动态
		else if("dynamic_getDynamicByUserId".equals(message))
		{
			String id=request.getParameter("id");
			int user_id=Integer.parseInt(id);
			JSONArray array=DynamicDao.getDynamicByUserId(user_id);
			
			out.write(array.toString());
			
			
		}
		//6.通过动态Id获得动态
		else if("dynamic_getDynamicById".equals(message))
		{
			String id=request.getParameter("id");
			int dynamic_id=Integer.parseInt(id);
			JSONObject object=DynamicDao.getDynamicById(dynamic_id);
			out.write(object.toString());
			
		}
		//7.通过Id删除一条动态
		else if("dynamic_deleteDynamic".equals(message))
		{
			String id=request.getParameter("id");
			int Dynamic_id=Integer.parseInt(id);

			boolean result = DynamicDao.deleteDynamic(Dynamic_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		 //8.查看当前动态是否被当前用户收藏
		else if("dynamic_isCollected".equals(message)) {
			String id=request.getParameter("user_id");
			String did=request.getParameter("dynamic_id");
			int user_id=Integer.parseInt(id);
			int dynamic_id=Integer.parseInt(did);

			boolean result = DynamicDao.isCollected(user_id, dynamic_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		 //9.查看当前动态是否被当前用户点赞
		else if("dynamic_isLikeuped".equals(message)) {
			String id=request.getParameter("user_id");
			String did=request.getParameter("dynamic_id");
			int user_id=Integer.parseInt(id);
			int dynamic_id=Integer.parseInt(did);

			boolean result = DynamicDao.isLikeuped(user_id, dynamic_id);
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
		
		System.out.println("post请求");
		doGet(request, response);
	


		
		
	
	}

}
