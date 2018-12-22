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
		System.out.println("Get请求");
		PrintWriter out=response.getWriter();
		String message=request.getParameter("message");
		System.out.println("meassge::"+message);
		//1.DynamicDao 查找所有
		if("dynamic_allDynamic".equals(message)) {
			
			JSONArray array=DynamicDao.getDynamic();
			response.getWriter().append(array.toString());
			
		}
		
		//2.插入一条动态（内容）
		 if("dynamic_addDynamic_text".equals(message)) 
		{

		}
		
			
//			//3.插入动态的图片（图片）
//			else if("dynamic_addDynamic_img".equals(message)) {
//				
//					System.out.println("连接成功！");
//					InputStream input = request.getInputStream();
//					String path="upload\\"+Math.random()*1000+".jpg";
//					File file = new File(request.getSession().getServletContext().getRealPath("/")+path);
//					if (!file.exists()) {
//						file.createNewFile();
//					}
//					FileOutputStream output = new FileOutputStream(file);
//					Byte[] bytes = new Byte[150000];
//					int read = input.read();
//					while(read!=-1) {
//						output.write(read);
//						read = input.read();
//					}
//					input.close();
//					output.flush();
//					output.close();
//					ServletOutputStream outputStream = response.getOutputStream();
//					outputStream.write("1".getBytes());
//					outputStream.close();
//					
//					DynamicDao.insertDynamicImage(id, path);
//			}
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
