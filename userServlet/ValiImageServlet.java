package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.utils.VerifyCode;
//专门生成一张验证码图片返回给浏览器
@WebServlet("/user/page/valiImageServlet")
public class ValiImageServlet extends HttpServlet{
	//提供服务入口的方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置reponse对象的缓存机制
		//控制浏览器不要缓存图片
		//响应头的过期时间
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		//调用验证码的对象的方法
		VerifyCode vc = new VerifyCode();
		//将图片保存到reponse响应的缓冲区
		vc.drawImage(response.getOutputStream());
		//获取验证码上的字母或数字
		String code = vc.getCode();
		request.getSession().setAttribute("code", code);
	}

}
