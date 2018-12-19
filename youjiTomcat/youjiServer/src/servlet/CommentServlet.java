package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//1.获取该动态的所有评论
		if("comment_ByDynamicId".equals(message)) {
			List list=CommentDao.getCommentByDynamicId(dynamic_id);
			JSONObject object=new JSONObject()；
					object.put("list", list);
			out.write(object.toString());
		}
		 //2.插入一条评论
		if("comment_addComment".equals(message)) {
			CommentDao.addComment(comment);
			
			
		}
		 //3.给该评论点赞+1
		if("comment_likeComment".equals(message)) {
			CommentDao.likeComment(comment_id);
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
