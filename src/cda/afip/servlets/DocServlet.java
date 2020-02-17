package cda.afip.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import cda.afip.model.DocManager;
import cda.afip.model.Document;
import cda.afip.model.User;
import cda.afip.model.UserManager;

@WebServlet("/docs")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class DocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DocServlet.class);
	private static final String VIEWS_PATH = "views/docs/";
	private static final String UPLOAD_DIRECTORY = "uploads";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logit("GET!!");

		String action=request.getParameter("action");	

		String view_name = VIEWS_PATH + (action==null?"index":action)+".jsp";
		String title = action==null?"Index":format_title(action);

		logger.info("Documents ==> action = "+action+" | view = "+view_name);

		if(!ExistView(view_name)) {
			view_name="views/error.jsp";
			title="404";
		}

		/*
		 * Model
		 */
		DocManager dm = new DocManager();
		if(action==null || "index".equals(action)) {			
			request.setAttribute("items", dm.getAll());
		}else if("add".equals(action) || "upload".equals(action)) { 
			request.setAttribute("item", new Document());		
			//add list users
			request.setAttribute("users", new UserManager().getAll());
		}else {
			Document item=null;
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				item = dm.getById(id);
			} catch (Exception e) {}		
			request.setAttribute("item", item);
			request.setAttribute("users", new UserManager().getAll());
		}

		request.setAttribute("title", title);	
		request.getRequestDispatcher(view_name).forward(request, response);
	}

	private String format_title(String txt) {
		return txt.substring(0,1).toUpperCase()+txt.substring(1).toLowerCase();
	}
	private boolean ExistView(String vue_name) {
		String fullPath = getServletContext().getRealPath(vue_name);
		try {
			File fview=new File(fullPath);
			return fview.exists();
		} catch (Exception e) {	}
		return false;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		if(request.getParameter("btn_file_upload")!=null) {			
//			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//			File uploadDir = new File(uploadPath);
//			if (!uploadDir.exists()) uploadDir.mkdir();
//
//			for (Part part : request.getParts()) {
//				String fileName = getFileName(part);	    
//				if(fileName!=null) {
//					logit("POST - upload : fileName = "+uploadPath + File.separator + fileName);
//					part.write(uploadPath + File.separator + fileName);
//				}
//			}
//		}

		DocManager dm = new DocManager();
		if(request.getParameter("btn_delete_action")!=null) {			
			int id=Integer.parseInt(request.getParameter("id"));
			logit("POST - Delete : "+id);		

			Document item = dm.getById(id);
			if(item!=null)
				dm.delete(item);
		}
		else if(request.getParameter("btn_add_action")!=null) {

			String name=request.getParameter("name");
			String path=request.getParameter("path");
			byte type=Byte.parseByte(request.getParameter("type"));
			int user_id=Integer.parseInt(request.getParameter("user"));

			User user = new UserManager().getById(user_id);
			logger.info("Document ==> form = "+name+" | path = "+path+" | type = "+type+" | user = "+user );			
			Document doc = new Document(0, name, path ,type, user);
			dm.add(doc);

		}
		doGet(request, response);
	}
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";"))	    	
			if (content.trim().startsWith("filename")) {

				String temp = content.substring(content.indexOf("=") + 2, content.length() - 1);
				File f = new File(temp);

				String filename = temp.substring(f.getParent().length()+1);
				logit("content : "+filename);	
				return filename;    
			}
		return null;
	}

	private void logit(String message) {
		logger.info("===========================================");
		logger.info("====>"+message);
		logger.info("===========================================");
	}

}
