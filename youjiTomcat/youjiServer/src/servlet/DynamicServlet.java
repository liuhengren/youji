package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			List<Dynamic>allDynamic=DynamicDao.getDynamic();
			JSONObject object=new JSONObject();
			object.put("list", allDynamic);
			response.getWriter().append(object.toString());
		}
		//2.插入一条动态
		else if("dynamic_addDynamic".equals(message)) 
		{
			DynamicDao.addDynamic(dynamic);
			
		}
		//3.通过分区查找动态
		else if("dynamic_getDynamicByPartitionId".equals(message))
		{
			List list=DynamicDao.getDynamicByPartitionId(partition_id);
			JSONObject object=new JSONObject();
			object.put("list", list);
			out.write(object.toString());
			
			
		}
		//4.根据用户Id查找动态
		else if("dynamic_getDynamicByUserId".equals(message))
		{
			List list=DynamicDao.getDynamicByUserId(user_id);
			JSONObject object=new JSONObject();
			object.put("list", list);
			out.write(object.toString());
			
		}
		//5.通过动态Id获得动态
		else if("dynamic_getDynamicById".equals(message))
		{
			Dynamic dynamic=DynamicDao.getDynamicById(dynamic_id);
			JSONObject object=new JSONObject();
			object.put("list", dynamic);
			out.write(object.toString());
		}
		//6.通过Id删除一条动态
		else if("dynamic_deleteDynamic".equals(message))
		{
			
			DynamicDao.deleteDynamic(Dynamic_id);
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
