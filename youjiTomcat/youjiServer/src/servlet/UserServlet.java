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
			
			UserDao.login(userphone,password);
			
		}
		// 2.ͨ���ֻ��ŵ�¼
		else if("user_logup".equals(message)) {
			
			boolean judge=UserDao.login(userphone,password);
			JSONObject object=new JSONObject();
			out.write(object.toString());
					
		}
		// 3.ͨ���û�Id����û�
		else if("user_getUserById".equals(message)) {
		
			User user=UserDao.getUserById(user_id);
			JSONObject object=new JSONObject();
			object.put("user", user);
			out.write(object.toString());
		}
		//4.�޸��û���
		else if("user_Username".equals(message)) {
			UserDao.updateUsername(user_id, username);
		}
		  //5.�޸�ͷ��
		else if("user_touxiang".equals(message)) {
			UserDao.updateUsertouxiang(user_id, img);
		}
		//6.�޸��Ա�
		else if("user_sex".equals(message)) {
			UserDao.updateUserSex(user_id, userSex);
		}
		 //7.�޸ļ��
		else if("user_instruction".equals(message)) {
			UserDao.updateUserIntroduction(user_id, userIntroduction);
		}
	    //8.�޸�����
		else if("user_birthday".equals(message)) {
			UserDao.updateUserBirthday(user_id, userBirthday);
		}
		  //9.�޸ļ���
		else if("user_address".equals(message)) {
			UserDao.updateUserHometown(user_id, userHometown);
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