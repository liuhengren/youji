package servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
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

		String streamIn = ReadStream.read(new BufferedInputStream(request.getInputStream()));

		// 1.通过手机号注册
		if("user_login".equals(message)) {
			
			UserDao.login(userphone,password);
			
		}
		// 2.通过手机号登录
		else if("user_logup".equals(message)) {
			
			boolean judge=UserDao.login(userphone,password);
			JSONObject object=new JSONObject();
			out.write(object.toString());
					
		}
		// 3.通过用户Id获得用户
		else if("user_getUserById".equals(message)) {
		
			User user=UserDao.getUserById(user_id);
			JSONObject object=new JSONObject();
			object.put("user", user);
			out.write(object.toString());
		}
		//4.修改用户名
		else if("user_Username".equals(message)) {
			UserDao.updateUsername(user_id, username);
		}
		  //5.修改头像
		else if("user_touxiang".equals(message)) {
			UserDao.updateUsertouxiang(user_id, img);
		}
		//6.修改性别
		else if("user_sex".equals(message)) {
			UserDao.updateUserSex(user_id, userSex);
		}
		 //7.修改简介
		else if("user_instruction".equals(message)) {
			UserDao.updateUserIntroduction(user_id, userIntroduction);
		}
	    //8.修改生日
		else if("user_birthday".equals(message)) {
			UserDao.updateUserBirthday(user_id, userBirthday);
		}
		  //9.修改家乡
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
