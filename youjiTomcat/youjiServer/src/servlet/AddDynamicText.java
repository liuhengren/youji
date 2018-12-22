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

import bean.Dynamic;
import dao.DynamicDao;

/**
 * Servlet implementation class AddDynamicText
 */
@WebServlet("/AddDynamicText")
public class AddDynamicText extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDynamicText() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String s = reader.readLine();
		System.out.println("s::" + s);
		String string = "";
		while (s != null) {
			string += s;
			s = reader.readLine();
		}

		JSONObject object2 = new JSONObject(string);
		Dynamic dynamic = new Dynamic();
		dynamic.setUser_id(object2.getInt("user_id"));
		dynamic.setText(object2.getString("text"));
		dynamic.setPartition_id(object2.getInt("partition_id"));
		dynamic.setAddress(object2.getString("address"));

		int id = DynamicDao.addDynamic(dynamic);
		JSONObject object = new JSONObject();
		object.put("id", id);
		out.append(object.toString());
		doGet(request, response);
	}

}
