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

import dao.CommentDao;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		//1.根据动态Id获得所有的评论
		if("comment_ByDynamicId".equals(message)) {
			String id=request.getParameter("dynamic_id");
			int dynamic_id=Integer.parseInt(id);
			JSONArray array=CommentDao.getCommentByDynamicId(dynamic_id);
			out.write(array.toString());
		}
	
		 //2.给用户的评论点赞+1
		else if("comment_likeComment".equals(message)) {
			String id=request.getParameter("id");
			int comment_id=Integer.parseInt(id);
			boolean result = CommentDao.addOnelikeToComment(comment_id);
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
