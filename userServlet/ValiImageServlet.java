package cn.tedu.javaweb.userServlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.javaweb.utils.VerifyCode;
//ר������һ����֤��ͼƬ���ظ������
@WebServlet("/user/page/valiImageServlet")
public class ValiImageServlet extends HttpServlet{
	//�ṩ������ڵķ���
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����reponse����Ļ������
		//�����������Ҫ����ͼƬ
		//��Ӧͷ�Ĺ���ʱ��
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		//������֤��Ķ���ķ���
		VerifyCode vc = new VerifyCode();
		//��ͼƬ���浽reponse��Ӧ�Ļ�����
		vc.drawImage(response.getOutputStream());
		//��ȡ��֤���ϵ���ĸ������
		String code = vc.getCode();
		request.getSession().setAttribute("code", code);
	}

}