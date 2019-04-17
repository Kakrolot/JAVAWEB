package cn.tedu.javaweb.adminServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import cn.tedu.javaweb.dao.BookDao;
import cn.tedu.javaweb.pojo.Book;
import cn.tedu.javaweb.utils.DateConverter;

@WebServlet("/admin/page/productAddServlet")
public class ProductAddServlet extends HttpServlet{
	//�ṩ������ڵķ���:ר�Ž�����Ʒ�ļ����ϴ�
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����requset�ı����ʽ
		request.setCharacterEncoding("utf-8");
		//���ж�form���Ƿ�Ϊ�ϴ��ļ�������
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		//��ȡ�ϴ��ļ���Ŀ��·��
		String dirPath = request.getServletContext().getRealPath("/user/img/goods");
		
		System.out.println(dirPath);
		//��ȡ�ļ��ϴ��Ľ�����
		//ר�����ڽ����ϴ���ͼƬ�����ļ���
		ServletFileUpload upload = new ServletFileUpload();
		if(isMultipart){
			//������ļ��ϴ����͵�form��
			//ͨ���ļ��ϴ�������,��ȡform���ĵ�����
			//�õ������д����form����������
			InputStream in = null;//������,��ȡ�������ݵ�������
			FileOutputStream out = null;//��������ӳ�����д����������
			try {
				FileItemIterator items = upload.getItemIterator(request);
				//ʹ��whileѭ�� ����������
				
				
				while(items.hasNext()){
					//������Ҫͨ��������ʽ��ȡ				
					FileItemStream item = items.next();
					in = item.openStream();//���ı����������ȡ����
					if (item.isFormField()) {//��ͨ�ı���
						//��ȡ��ͨ�ı����name��ֵ�� ����name=isbn
						String fieldNmae = item.getFieldName();
						//��ȡ�ı����valueֵ value="997812312"	
						String fieldValue = Streams.asString(in,"utf-8");
						System.out.println("��ͨ�ı���:"+fieldNmae+"="+fieldValue);
						//�����ݷ�װ��request������:��key = value��ֵ����ʽ����requeset��װ
						request.setAttribute(fieldNmae, fieldValue);
					}else {
						//�ϴ��ļ�
						//��ȡ�ϴ��ı��������:����:�ϴ���һ��png
						String imageName = item.getName();//ͼƬ������
						System.out.println("�ϴ�ͼƬ������:"+imageName);
						if(imageName == null ||"".equals(imageName)){
							//��ô��Ҫִ������Ĵ�����
							continue;//����whileѭ��,ִ����һ����¼
						}
						//��ȡ�ϴ��ı��������:����:<input type="file" name = "detail1big"/>
						String fieldName = item.getFieldName();
						//����һ��ͼƬ��·��
						String imgPath = dirPath+File.separator+request.getAttribute("isbn")+File.separator+fieldName+".jpg";
						String realPath = "D://workspace1_JavaEE/day03/WebContent/user/img/goods"+File.separator+request.getAttribute("isbn")+File.separator+fieldName+".jpg";
						System.out.println("����ͼƬ��λ��"+realPath);
						//����ͼƬ������λ�ã������ļ����Լ�ͼƬ
						File file = new File(realPath);
						//������һ������ΪIsbn�ı�ŵ��ļ���
						file.getParentFile().mkdir();
						//�ڸ��ļ����У�����һ���µ��ļ�
						file.createNewFile();//����Ŀ���ļ�
						//ʹ������������һ��ͼƬ��Ŀ��λ�á�
						out = new FileOutputStream(file);
						//ʹ�û��������������ļ���
						//������ָ����:�ֽ������飬��ͼƬ�����ֽڴ�����
						byte[] buf = new byte[1024];//���1024���ֽ�
						int len = 0;//��ȡ�ֽ�ʱ�ı��:���һ���ֽ�Ϊ-1
						while((len=in.read(buf))!=-1){
							//������-1ʱ ֤����û�а�ͼƬ ��ȫ����
							//����0 ��ʾƫ��������0��ʼ��len����
							out.write(buf, 0, len);
							//ѭ����ȡͼƬ������
							
						}
						
					}
					//�ж�item��һ����¼:����ͨ�ı������ϴ����ļ�
					//���������һ����¼
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (in!=null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				
			}
		}
		//����dao��ķ�����������book�鼮�Ļ�����Ϣ
		Book book = new Book();
		book.setAuthor(request.getAttribute("author").toString());
		book.setEdition(Integer.parseInt(request.getAttribute("edition").toString()));
		book.setForm(request.getAttribute("form").toString());
		book.setFormat(request.getAttribute("format").toString());
		book.setIsbn(request.getAttribute("isbn").toString());
		book.setPackaging(request.getAttribute("packaging").toString());
		book.setPages(Integer.parseInt(request.getAttribute("pages").toString()));
		book.setPress(request.getAttribute("press").toString());
		book.setPrice(Double.parseDouble(request.getAttribute("price").toString()));
		try {
			book.setPublished(DateConverter.parseDate(request.getAttribute("published").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setTitle(request.getAttribute("title").toString());
		book.setWords(Integer.parseInt(request.getAttribute("words").toString()));
		Writer writer = response.getWriter();
		BookDao dao = new BookDao();
		dao.insert(book);
		writer.write("yes");
		writer.close();
	}

}
