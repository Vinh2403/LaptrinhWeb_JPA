package Test.Web.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import Test.Web.entity.Category;
import Test.Web.services.IcategoryService;
import Test.Web.services.impl.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import Test.Web.utils.Constant;
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5 )
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/insert", 
							"/admin/category/edit", "/admin/category/update",
							"/admin/category/delete", "/admin/categpry/search"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IcategoryService category_Service = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories"))
		{
		List<Category> list = category_Service.findAll();		//Lấy dữ liệu ra từ DB.
		//System.out.print(list.get(0).getImages());
		req.setAttribute("listcate", list);		//luu tru lai de ti nua day ra view
		req.getRequestDispatcher("/views/admin/category_list.jsp").forward(req, resp);	//quang vao file giao dien.
		}
		else if(url.contains("add"))
		{
			req.getRequestDispatcher("/views/admin/category_add.jsp").forward(req, resp);	//quang vao file giao dien.
		}
		else if(url.contains("edit"))
		{
			int id = Integer.parseInt(req.getParameter("id"));		//id la ten dat trong file category_lsit .jsp;
			
			//Loi ra duoc cai du lieu co san hien thi truoc de nguoi dung biet muon sua nua khong:
			Category category = category_Service.findById(id);
			req.setAttribute("cate", category);
			
			req.getRequestDispatcher("/views/admin/category_edit.jsp").forward(req, resp);	
		}
		else if(url.contains("/admin/category/delete"))
		{
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				category_Service.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
				resp.sendRedirect(req.getContextPath() + "/admin/categories");		
		}
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setCharacterEncoding("UTF-8");
	String url = req.getRequestURI();
	
	if(url.contains("insert"))
	{
		Category category = new Category();
		
		String category_name = req.getParameter("categoryname");
		String status_temp = req.getParameter("status");
		int status = Integer.parseInt(status_temp);
			category.setCategoryname(category_name);
			category.setStatus(status);
		//String images = "https://techlandaudio.com.vn/wp-content/uploads/2023/03/Tai-nghe-SENNHEISER-IE-600.jpg";
		String fname = "";
		String uploadPath=Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Part part = req.getPart("images");
			if(part.getSize()>0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				//Doi ten file trong t.hop bi trung:
				int index = filename.lastIndexOf(".");		//cai dau ngan cach dua ten file va phan mo rong. VD: google.com
				String ext = filename.substring(index + 1);	//phan mo rong
				fname = System.currentTimeMillis() + "." + ext;
				//upload file:
				part.write(uploadPath + "/"	+ fname);
				//ghi ten file vao  data.
				category.setImages(fname);			//fname la cai ten file duoc xu ly roi. con filename la raw, lay nguyen ban.
			}else
			{
				category.setImages("avatar.png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		category_Service.insert(category);
		resp.sendRedirect(req.getContextPath()+"/admin/categories");
	}
	else if(url.contains("update"))			// Update dung POST
	{
		int category_id = Integer.parseInt(req.getParameter("categoryid"));
		String category_name = req.getParameter("categoryname");
		String status_temp = req.getParameter("status");
		int status = Integer.parseInt(status_temp);
		
		//upload file bang multipart (chua viet):
		
		
		
		Category category = new Category();
		category.setCategoryId(category_id);
		category.setCategoryname(category_name);
		
		category.setStatus(status);
		//luu anh cu:
		Category cateold = category_Service.findById(category_id);
		String fileold = cateold.getImages();
		//xu ly images:
		String fname = "";
		String uploadPath=Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Part part = req.getPart("images");
			if(part.getSize()>0) {
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				//Doi ten file trong t.hop bi trung:
				int index = filename.lastIndexOf(".");		//cai dau ngan cach dua ten file va phan mo rong. VD: google.com
				String ext = filename.substring(index + 1);	//phan mo rong
				fname = System.currentTimeMillis() + "." + ext;
				//upload file:
				part.write(uploadPath + "/"	+ fname);
				//ghi ten file vao  data.
				category.setImages(fname);			//fname la cai ten file duoc xu ly roi. con filename la raw, lay nguyen ban.
			}else
			{
				category.setImages(fileold);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		category_Service.update(category);
		resp.sendRedirect(req.getContextPath()+"/admin/categories");
	}
}
	
}
