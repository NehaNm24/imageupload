

import java.io.IOException;
import java.io.InputStream;
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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String name=request.getParameter("name");
			Part filepart = request.getPart("photo");
			System.out.println("Hello" + " "+name +" "+ filepart);
			InputStream inputStream=null;
			if(filepart !=null) {
				long fileSize= filepart.getSize();
				String fileContent = filepart.getContentType();
				inputStream = filepart.getInputStream();
				
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			PreparedStatement prepStmt = con.prepareStatement("insert into Employee values (Name, photo) values (?,?);");
			prepStmt.setString(1,name);
			prepStmt.setBlob(2,inputStream);
			int returnCode = prepStmt.executeUpdate();
			
			if(returnCode == 0) {
				request.setAttribute("Message","Error inserting file");
				getServletContext().getRequestDispatcher("/faliure.jsp").forward(request,response);
			}
			else {
				request.setAttribute("Message","Your record inserted");
				getServletContext().getRequestDispatcher("/success.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
