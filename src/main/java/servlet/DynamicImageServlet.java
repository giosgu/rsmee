package servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DynamicImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2736575124811385269L;
	private static final String FILE = "file";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String file = request.getParameter(FILE);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		in.close();
		response.getOutputStream().write(bytes);
	}
	
		
}
