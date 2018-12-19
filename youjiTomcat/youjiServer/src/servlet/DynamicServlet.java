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
		//1.DynamicDao ��������
		if("dynamic_allDynamic".equals(message)) {
			
			List<Dynamic>allDynamic=DynamicDao.getDynamic();
			JSONObject object=new JSONObject();
			object.put("list", allDynamic);
			response.getWriter().append(object.toString());
		}
		//2.����һ����̬
		else if("dynamic_addDynamic".equals(message)) 
		{
			DynamicDao.addDynamic(dynamic);
			
		}
		//3.ͨ���������Ҷ�̬
		else if("dynamic_getDynamicByPartitionId".equals(message))
		{
			List list=DynamicDao.getDynamicByPartitionId(partition_id);
			JSONObject object=new JSONObject();
			object.put("list", list);
			out.write(object.toString());
			
			
		}
		//4.�����û�Id���Ҷ�̬
		else if("dynamic_getDynamicByUserId".equals(message))
		{
			List list=DynamicDao.getDynamicByUserId(user_id);
			JSONObject object=new JSONObject();
			object.put("list", list);
			out.write(object.toString());
			
		}
		//5.ͨ����̬Id��ö�̬
		else if("dynamic_getDynamicById".equals(message))
		{
			Dynamic dynamic=DynamicDao.getDynamicById(dynamic_id);
			JSONObject object=new JSONObject();
			object.put("list", dynamic);
			out.write(object.toString());
		}
		//6.ͨ��Idɾ��һ����̬
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
