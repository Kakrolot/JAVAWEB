package cn.tedu.javaweb.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.dao.UserDao;
import cn.tedu.javaweb.pojo.User;
@WebFilter("/*")
public class AutoLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("���ٹ�����");
		
	}
	

	//���Ĳ����ɳ���Ա��д
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1.δ��¼���Զ���¼
		
		//���ز���ȥrequst����
		//�鿴session�Ự�����Ƿ���user����
		//���û��session����ʵ���Զ���¼
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		if(req.getSession(false)==null || req.getSession().getAttribute("user")==null){
			//2.ֻ�д��Զ���½cookie�����Զ���½
			Cookie[] cookies = req.getCookies();
			Cookie autoCookie = null;
			if (cookies!=null) {
				for(Cookie cookie:cookies){
					if("autologin".equals(cookie.getName())){
						autoCookie = cookie;
						break;
					}
				}
			}
			//��Ϊ�ղ��ܻ�ȡ�û�������
			if (autoCookie != null) {
				//3.ֻ��cookie�е��û������붼��ȷ�����Զ���¼
				//split����ַ���
				String username = URLDecoder.decode(autoCookie.getValue(),"UTF-8").split("#")[0];
				String password = URLDecoder.decode(autoCookie.getValue(),"UTF-8").split("#")[1];
				
				UserDao dao = new UserDao();
				User user = dao.selectByUnameAndUpwd(username, password);
				System.out.println("�Զ���½���û���:"+username);
				if(user!=null){
					//����������������,�Զ���½
					req.getSession().setAttribute("user",user);
				}
			}
		}
		//4.��������
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("��ʼ��������");
		
	}

}
