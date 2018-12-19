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
		// 1.ͨ���û�Id��ø��û��������ղ�
		if("collection_ByUserId".equals(message)) {
			List list=CollectionDao.getCollectionByUserId(user_id);
			JSONObject object=new JSONObject();
			out.write(object.toString());
		}
		// 2.���һ���ղ�
		else if("collection_addCollection".equals(message)) {
			List list=CollectionDao.addCollection(user_id, dynamic_id);
			JSONObject object=new JSONObject();
			out.write(object.toString());
		}
		//3.ɾ��һ���ղ�
		else if("collection_deleteCollection".equals(message)) {
			CollectionDao.deleteCollection(collection_id);
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
