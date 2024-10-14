package Test.Web.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
 
import Test.Web.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/image"})
public class DownloadFileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	String fileName = req.getParameter("fname");
	File file = new File(Constant.UPLOAD_DIRECTORY + "\\" + fileName);
	
	if (file.exists()) {
		resp.setContentType(getServletContext().getMimeType(fileName)); // Đặt loại nội dung cho file
        resp.setContentLengthLong(file.length());
        Files.copy(file.toPath(), resp.getOutputStream()); // Truyền file về client
        
	}
	else {
		 resp.sendError(HttpServletResponse.SC_NOT_FOUND); // Nếu không tìm thấy file thì trả về lỗi 404
		
	}
	}
	
	
}
