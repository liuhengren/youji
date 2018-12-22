package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.Comment;
import bean.Dynamic;
import dao.CommentDao;
import dao.DynamicDao;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String s = reader.readLine();
		String string = "";
		while (s != null) {
			string += s;
			s = reader.readLine();
		}
		JSONObject object = new JSONObject(string);
		Comment comment=new Comment();
		comment.setDynamic_id(object.getInt("dynamic_id"));
		comment.setLike_num(0);
		comment.setText(object.getString("text"));
		comment.setUser_id(object.getInt("user_id"));
		boolean result = CommentDao.addComment(comment);
		JSONObject object2=new JSONObject();
		object.put("res",result);
		out.write(object.toString());
	}

}
