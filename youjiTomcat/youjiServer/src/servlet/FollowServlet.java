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

import bean.Follow;
import dao.FollowDao;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowServlet() {
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
		//1.通过用户Id获得他的关注者
		if("follow_ByUserId".equals(message)) {
			
			String id=request.getParameter("id");
			int user_id=Integer.parseInt(id);
			
			JSONArray array=FollowDao.getFollowByUserId(user_id);
			
			out.write(array.toString());
		}
		 //2.添加关注者
		else if("follow_addFollow".equals(message)) {
			
			String id=request.getParameter("user_id");
			String fid=request.getParameter("follow_user_id");
			int user_id=Integer.parseInt(id);
			int follow_user_id=Integer.parseInt(fid);
			
			boolean result = FollowDao.addFollow(user_id, follow_user_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		 //3.删除关注者
		else if("follow_deleteFollow".equals(message)) {
			
			String id=request.getParameter("user_id");
			String fid=request.getParameter("follow_user_id");
			int user_id=Integer.parseInt(id);
			int follow_user_id=Integer.parseInt(fid);
			boolean result = FollowDao.deleteFollow(user_id, follow_user_id);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		//4.判断是否被关注
		else if("follow_isFollow".equals(message)) {
			
			String id=request.getParameter("user_id");
			String fid=request.getParameter("follow_user_id");
			int user_id=Integer.parseInt(id);
			int follow_user_id=Integer.parseInt(fid);
			boolean judge=FollowDao.isFollow(user_id, follow_user_id);
			JSONObject object=new JSONObject();
			object.put("object", object);
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
