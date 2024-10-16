package Test.Web.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import Test.Web.entity.Category;
import Test.Web.entity.Video;
import Test.Web.services.IcategoryService;
import Test.Web.services.IvideoService;
import Test.Web.services.impl.CategoryService;
import Test.Web.services.impl.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import Test.Web.utils.Constant;
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5 )
@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add", "/admin/video/insert", 
							"/admin/video/edit", "/admin/video/update",
							"/admin/video/delete", "/admin/video/search"})
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IvideoService video_Service = new VideoService();
	public IcategoryService category_Service = new CategoryService();
	@Override	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("videos"))
		{
		List<Video> list = video_Service.findAll();		//Lấy dữ liệu ra từ DB.
		//System.out.print(list.get(0).);
		req.setAttribute("listvid", list);		//luu tru lai de ti nua day ra view
		req.getRequestDispatcher("/views/admin/video_list.jsp").forward(req, resp);	//quang vao file giao dien.
		}
		else if(url.contains("add"))
		{
			List<Category> list = category_Service.findAll();		//Lấy dữ liệu ra từ DB.
			System.out.print(list.get(0).getCategoryname());
			req.setAttribute("listcate", list);		//luu tru lai de ti nua day ra view
			req.getRequestDispatcher("/views/admin/video_add.jsp").forward(req, resp);	//quang vao file giao dien.
		}
		else if(url.contains("edit"))
		{
			String vidid = req.getParameter("id");		//id la ten dat trong file category_lsit .jsp;
			
			//Loi ra duoc cai du lieu co san hien thi truoc de nguoi dung biet muon sua nua khong:
			Video video = video_Service.findById(vidid);
			req.setAttribute("vid", video);
			
			req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);	
		}
		else if(url.contains("/admin/video/delete"))
		{
			String vidid = req.getParameter("id");
			try {
				video_Service.delete(vidid);
			} catch (Exception e) {
				e.printStackTrace();
			}
				resp.sendRedirect(req.getContextPath() + "/admin/videos");		
		}
	}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setCharacterEncoding("UTF-8");
	String url = req.getRequestURI();
	
	if(url.contains("insert"))
	{
		Video video = new Video();
		
		String video_id = req.getParameter("VideoID");
		String video_title = req.getParameter("VideoTitle");
		String status_temp = req.getParameter("status");
		String description = req.getParameter("Description");
		int cateid = Integer.parseInt(req.getParameter("Category"));
		  // Tìm Category object từ categoryId
	    Category category = category_Service.findById(cateid);
		int views = Integer.parseInt(req.getParameter("ViewCount"));
			video.setVideoId(video_id);
			video.setTitle(video_title);
			video.setActive(status_temp.equals("active")?1:0);
			video.setDescription(description);
			video.setViews(views);
			video.setCategory(category);
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
				video.setPoster(fname);			//fname la cai ten file duoc xu ly roi. con filename la raw, lay nguyen ban.
			}else
			{
				video.setPoster("avatar.png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		video_Service.insert(video);
		resp.sendRedirect(req.getContextPath()+"/admin/videos");
	}
//	else if(url.contains("update"))			// Update dung POST
//	{
//		int category_id = Integer.parseInt(req.getParameter("categoryid"));
//		String category_name = req.getParameter("categoryname");
//		String status_temp = req.getParameter("status");
//		int status = Integer.parseInt(status_temp);
//		
//		//upload file bang multipart (chua viet):
//		
//		
//		
//		Category category = new Category();
//		category.setCategoryId(category_id);
//		category.setCategoryname(category_name);
//		
//		category.setStatus(status);
//		//luu anh cu:
//		Category cateold = category_Service.findById(category_id);
//		String fileold = cateold.getImages();
//		//xu ly images:
//		String fname = "";
//		String uploadPath=Constant.UPLOAD_DIRECTORY;
//		File uploadDir = new File(uploadPath);
//		if(!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//		try {
//			Part part = req.getPart("images");
//			if(part.getSize()>0) {
//				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//				//Doi ten file trong t.hop bi trung:
//				int index = filename.lastIndexOf(".");		//cai dau ngan cach dua ten file va phan mo rong. VD: google.com
//				String ext = filename.substring(index + 1);	//phan mo rong
//				fname = System.currentTimeMillis() + "." + ext;
//				//upload file:
//				part.write(uploadPath + "/"	+ fname);
//				//ghi ten file vao  data.
//				category.setImages(fname);			//fname la cai ten file duoc xu ly roi. con filename la raw, lay nguyen ban.
//			}else
//			{
//				category.setImages(fileold);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		video_Service.update(category);
//		resp.sendRedirect(req.getContextPath()+"/admin/categories");
//	}
}
	
}
