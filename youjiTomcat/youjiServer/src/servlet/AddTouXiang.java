package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DynamicDao;
import dao.UserDao;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class AddTouXiang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTouXiang() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("连接成功！");
		String uid=request.getHeader("id");
		int id=Integer.parseInt(uid);
		InputStream input = request.getInputStream();
		double name=Math.random()*1000;
		String path="upload\\"+name+".jpg";
		File file = new File(request.getSession().getServletContext().getRealPath("/")+path);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream output = new FileOutputStream(file);
		Byte[] bytes = new Byte[150000];
		int read = input.read();
		while(read!=-1) {
			output.write(read);
			read = input.read();
		}
		input.close();
		output.flush();
		output.close();
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write("1".getBytes());
		outputStream.close();
		UserDao.updateUsertouxiang(id, name+".jpg");
		
	}

}
