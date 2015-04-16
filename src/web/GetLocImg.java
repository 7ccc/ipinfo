package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于AJAX获取位置图片
 * @author Seven
 */
public class GetLocImg extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loc = (String)request.getSession().getAttribute("loc");
		String base64Img = Util.bytesToBase64(Util.getLocImg(loc));
		//由于返回的不是json也没有中文所以就不设置消息头了
		response.getWriter().write(base64Img);
	}

}
