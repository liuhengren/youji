package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CollectionDao;

/**
 * Servlet implementation class CollectionServlet
 */
@WebServlet("/CollectionServlet")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		PrintWriter out=response.getWriter();
		// 1.通过用户Id获得该用户的所有收藏
		if("collection_ByUserId".equals(message)) {
			
			String id=request.getParameter("user_id");
			int user_id=Integer.parseInt(id);
			JSONArray array=CollectionDao.getCollectionByUserId(user_id);
			
			out.write(array.toString());
			
		}
		// 2.添加一条收藏
		else if("collection_addCollection".equals(message)) {
			
			String id=request.getParameter("user_id");
			String fid=request.getParameter("dynamic_id");
			int user_id=Integer.parseInt(id);
			int dynamic_id=Integer.parseInt(fid);
			
			boolean result=CollectionDao.addCollection(user_id, dynamic_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		
		}
		//3.删除一条收藏
		else if("collection_deleteCollection".equals(message)) {
			String fid=request.getParameter("id");
			int collection_id=Integer.parseInt(fid);
			boolean result = CollectionDao.deleteCollection(collection_id);
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
