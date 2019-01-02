package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DynamicDao;

/**
 * Servlet implementation class AddDynamicInmageServlet
 */
@WebServlet("/AddDynamicInmageServlet")
public class AddDynamicImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDynamicImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("连接成功！");
		String sid=request.getHeader("id");
		int id=Integer.parseInt(sid);
		InputStream input = request.getInputStream();
		double patha=Math.random()*1000;
		String path="upload\\"+patha+".jpg";
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
		DynamicDao.insertDynamicImage(id, patha+".jpg");
		
	}

}
