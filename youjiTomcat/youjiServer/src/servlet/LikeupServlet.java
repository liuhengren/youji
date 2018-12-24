package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CollectionDao;
import dao.CommentDao;
import dao.LikeupDao;

/**
 * Servlet implementation class LikeupServlet
 */
@WebServlet("/LikeupServlet")
public class LikeupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikeupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String message = request.getParameter("message");
		PrintWriter out = response.getWriter();

		// 1.用户给动态添加一条点赞
		if ("likeup_addlikeup".equals(message)) {

			String id = request.getParameter("user_id");
			String fid = request.getParameter("dynamic_id");
			int user_id = Integer.parseInt(id);
			int dynamic_id = Integer.parseInt(fid);

			boolean result = LikeupDao.addLikeup(user_id, dynamic_id);
			JSONObject object = new JSONObject();
			object.put("res", result);
			out.write(object.toString());

		}
		// 2.删除一条点赞
		else if ("likeup_deletelikeup".equals(message)) {
			String userid = request.getParameter("user_id");
			String dyid = request.getParameter("dynamic_id");
			int user_id = Integer.parseInt(userid);
			int dynamic_id = Integer.parseInt(dyid);
			boolean result = LikeupDao.deleteLikeup(user_id, dynamic_id);
			JSONObject object = new JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		// 3.通过用户ID获得所有的点赞
		else if ("like_getLikeupByUserId".equals(message)) {
			String id = request.getParameter("user_id");
			int user_id = Integer.parseInt(id);
			JSONArray array = LikeupDao.getLikeupByUserId(user_id);

			out.write(array.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
