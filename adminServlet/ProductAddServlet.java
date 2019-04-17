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
	//提供服务入口的方法:专门进行商品文件的上传
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置requset的编码格式
		request.setCharacterEncoding("utf-8");
		//先判断form表单是否为上传文件的类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		//获取上传文件的目标路径
		String dirPath = request.getServletContext().getRealPath("/user/img/goods");
		
		System.out.println(dirPath);
		//获取文件上传的解析器
		//专门用于解析上传的图片或者文件等
		ServletFileUpload upload = new ServletFileUpload();
		if(isMultipart){
			//如果是文件上传类型的form表单
			//通过文件上传解析器,获取form表单的迭代器
			//该迭代器中存放了form的所有数据
			InputStream in = null;//输入流,读取表单的数据到程序中
			FileOutputStream out = null;//输出流，从程序中写出表单的数据
			try {
				FileItemIterator items = upload.getItemIterator(request);
				//使用while循环 遍历迭代器
				
				
				while(items.hasNext()){
					//数据需要通过流的形式获取				
					FileItemStream item = items.next();
					in = item.openStream();//打开文本框的流，获取数据
					if (item.isFormField()) {//普通文本狂
						//获取普通文本框的name的值： 举例name=isbn
						String fieldNmae = item.getFieldName();
						//获取文本框的value值 value="997812312"	
						String fieldValue = Streams.asString(in,"utf-8");
						System.out.println("普通文本框:"+fieldNmae+"="+fieldValue);
						//把数据封装到request对象中:以key = value键值对形式进行requeset封装
						request.setAttribute(fieldNmae, fieldValue);
					}else {
						//上传文件
						//获取上传文本框的内容:举例:上传了一张png
						String imageName = item.getName();//图片的名字
						System.out.println("上传图片的名字:"+imageName);
						if(imageName == null ||"".equals(imageName)){
							//那么不要执行下面的代码了
							continue;//返回while循环,执行下一条记录
						}
						//获取上传文本框的名字:举例:<input type="file" name = "detail1big"/>
						String fieldName = item.getFieldName();
						//生成一张图片的路径
						String imgPath = dirPath+File.separator+request.getAttribute("isbn")+File.separator+fieldName+".jpg";
						String realPath = "D://workspace1_JavaEE/day03/WebContent/user/img/goods"+File.separator+request.getAttribute("isbn")+File.separator+fieldName+".jpg";
						System.out.println("最终图片的位置"+realPath);
						//根据图片的最终位置，生成文件夹以及图片
						File file = new File(realPath);
						//创建了一个名称为Isbn的编号的文件夹
						file.getParentFile().mkdir();
						//在该文件夹中，创建一个新的文件
						file.createNewFile();//创建目标文件
						//使用输出流，输出一张图片到目标位置、
						out = new FileOutputStream(file);
						//使用缓冲流，进行流的加载
						//缓冲区指的是:字节码数组，把图片按照字节存起来
						byte[] buf = new byte[1024];//存放1024个字节
						int len = 0;//读取字节时的编号:最后一个字节为-1
						while((len=in.read(buf))!=-1){
							//不等于-1时 证明还没有把图片 完全生成
							//参数0 表示偏移量，从0开始到len结束
							out.write(buf, 0, len);
							//循环读取图片的内容
							
						}
						
					}
					//判断item这一条记录:是普通文本还是上传的文件
					//如果存在下一个记录
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
		//调用dao层的方方法，插入book书籍的基本信息
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
