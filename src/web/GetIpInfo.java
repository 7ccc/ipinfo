package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetIpInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		request.getMethod();
		JsonEntity je = null;
		if("POST".equals(request.getMethod().toUpperCase())){
			je = Util.getIpInfo(request.getParameter("ip"));
		}else{
			//由于没测试获取外网IP所以把他的值固定了,理论上可以用u.getIpAddr(request)获取
			je = Util.getIpInfo("114.114.114.114");
		}
		request.getSession().setAttribute("loc", je.getLoc());
		request.setAttribute("info", je);
		request.getRequestDispatcher("/WEB-INF/jsp/view.jsp").forward(request, response);
	}
	

}
