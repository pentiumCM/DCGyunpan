package org.jit.dcg.pub;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//web项目前端展现资源文件
	public static Properties g_UIPro = new Properties();
	
	public static Properties g_JSPro = new Properties();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
       System.out.println("**********开始加载MainServlet***********");
       
       //加载web工程项目的页面展现资源文件
       g_UIPro = Common.getUIConfig();
       
       //加载web工程项目的提示资源文件
       g_JSPro = Common.getJSConfig();
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

