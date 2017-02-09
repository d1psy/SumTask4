package web.command.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import persistence.dao.DBManager;
import web.command.Command;
 

/**
 * @author Golubtsov
 * Allows users to upload their photos
 */
public class UploadCommand  extends Command {
	
    DBManager dbManager = DBManager.getInstance();
	
 
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;
 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
    	String userIdString = request.getParameter("user");
    	String photoType = request.getParameter("photo");
    	
		if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
            writer.println("Request does not contain upload data");
            writer.flush();
            return null;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = null;
        if(photoType.equals("cert")) {
            uploadPath = "C:\\UploadTest\\marks\\" + userIdString;
        }
        else {
        	uploadPath = "C:\\UploadTest\\" + userIdString;
        }
        

        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
            List<?> formItems = upload.parseRequest(request);
            Iterator<?> iter = formItems.iterator();
             
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    if(fileName.equals("")) {
                    	if(photoType.equals("cert")) {
                        	return "markspage&usrid="+userIdString;
                        } 
                        else {
                        	return "profile";
                        }
                    }
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    if(photoType.equals("cert")) {
                    	setCertPhoto(request.getSession(), filePath);
                    	dbManager.addCertPhoto(filePath, Integer.parseInt(userIdString));
                    }
                    else {
                    	setSessionPhoto(request.getSession(), filePath);
                    	dbManager.addPhoto(filePath, Integer.parseInt(userIdString));
                    }
                    item.write(storeFile);
                }
            }
            System.out.println("SUCCESS");
        } catch (Exception ex) {
        	System.out.println("ERROR: " + ex.getMessage());
        }
        if(photoType.equals("cert")) {
        	return "markspage&usrid="+userIdString;
        } 
        else {
        	return "profile";
        }
	}
	
	private void setCertPhoto(HttpSession session, String path) {
		session.setAttribute("certPhoto", path);
	}
	
	private void setSessionPhoto(HttpSession session, String path) {
		session.setAttribute("photo", path);
	}
	
}