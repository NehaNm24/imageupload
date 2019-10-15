package neha;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */

@MultipartConfig(maxFileSize = 1699999999)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static boolean isValidImageFile(String contentType) {
		if (!(contentType.equals("image/pjpeg") || contentType.equals("image/jpeg") || contentType.equals("image/png")
				|| contentType.equals("image/gif") || contentType.equals("image/bmp")
				|| contentType.equals("image/x-png") || contentType.equals("image/x-icon"))) {
			return false;
		}
		return true;
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter out = response.getWriter();
			String name=request.getParameter("name");
			Part filepart = request.getPart("photo");
			System.out.println("Hello" + " "+name +" "+ filepart);
			InputStream inputStream=null;
			
			if(filepart !=null) {
				
				long fileSize= filepart.getSize();
				
				String fileContent = filepart.getContentType();
				if (!UploadServlet.isValidImageFile(fileContent)) {
					String alert="Invalid image File! Content Type :-" + fileContent;
					out.println("<html><head></head><body onload=\"alert('Image format should be jpeg')\"></body></html>");
					
					
				}
				
				inputStream = filepart.getInputStream();
				
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			System.out.println("connection established");
			PreparedStatement prepStmt = con.prepareStatement("insert into Employee  (Name, image) values (?,?);");
			prepStmt.setString(1,name);
			prepStmt.setBlob(2,inputStream);
			int returnCode = prepStmt.executeUpdate();
			System.out.println("pstmt done");
			if(returnCode == 0) {
				System.out.println("fail");
				request.setAttribute("Message","Error inserting file");
				getServletContext().getRequestDispatcher("/faliure.jsp").forward(request,response);
			}
			else {
				
				request.setAttribute("Message","Your record inserted");
				getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
