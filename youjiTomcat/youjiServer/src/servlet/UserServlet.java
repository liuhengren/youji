package servlet;

import java.io.BufferedInputStream;
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

import bean.User;
import dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String message=request.getParameter("message");
		PrintWriter out = response.getWriter();

			// 1.ͨ���ֻ���ע��
		if("user_login".equals(message)) {
			
			String userphone=request.getParameter("userphone");
			String password=request.getParameter("password");
			System.out.println("userphone"+userphone);
			System.out.println("password"+password);
			boolean result = UserDao.logup(userphone,password);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
			
		}
		// 2.ͨ���ֻ��ŵ�¼
		else if("user_logup".equals(message)) {
			String userphone=request.getParameter("userphone");
			String password=request.getParameter("password");
			boolean result = UserDao.login(userphone,password);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
			
					
		}
		// 3.ͨ���û�Id����û�
		else if("user_getUserById".equals(message)) {
			String id=request.getParameter("id");
			int user_id=Integer.parseInt(id);
			User user=UserDao.getUserById(user_id);
			JSONObject object=new JSONObject();
			object.put("user", user);
			out.write(object.toString());
		}
		//4.�޸��û���
		else if("user_Username".equals(message)) {
			String id=request.getParameter("id");
			String username=request.getParameter("name");
			int user_id=Integer.parseInt(id);
			boolean result = UserDao.updateUsername(user_id, username);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
//		  //5.�޸�ͷ��
//		else if("user_touxiang".equals(message)) {
//			UserDao.updateUsertouxiang(user_id, img);
//		}
		//6.�޸��Ա�
		else if("user_sex".equals(message)) {
			String id=request.getParameter("id");
			String userSex=request.getParameter("sex");
			int user_id=Integer.parseInt(id);
			boolean result = UserDao.updateUserSex(user_id, userSex);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		 //7.�޸ļ��
		else if("user_instruction".equals(message)) {
			String id=request.getParameter("id");
			String userIntroduction=request.getParameter("instruction");
			int user_id=Integer.parseInt(id);
			UserDao.updateUserIntroduction(user_id, userIntroduction);
		}
//	    //8.�޸�����
//		else if("user_birthday".equals(message)) {
//			String id=request.getParameter("id");
//			String userBirthday=request.getParameter("birthday");
//			int user_id=Integer.parseInt(id);
//			UserDao.updateUserBirthday(user_id, userBirthday);
//		}
		  //9.�޸ļ���
		else if("user_address".equals(message)) {
			String id=request.getParameter("id");
			String userHometown=request.getParameter("address");
			int user_id=Integer.parseInt(id);
			boolean result = UserDao.updateUserHometown(user_id, userHometown);
			JSONObject object = new  JSONObject();
			object.put("res", result);
			out.write(object.toString());
		}
		 //10.ͨ���û��ֻ��Ų�ѯ�û�������Ϣ
		else if("user_UserDetail".equals(message)) {
			String phone=request.getParameter("phone");
			JSONObject phoneobject=UserDao.getUserDetail(phone);
		
			out.write(phoneobject.toString());
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
