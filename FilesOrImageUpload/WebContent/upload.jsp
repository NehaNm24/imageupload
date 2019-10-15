<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form method="POST" action="UploadServlet" enctype="multipart/form-data" >
            <table border="0">
            	<tr>
            		<td>Enter your name:</td>
            		<td><input type="text" name="name" size="50"/></td>
            	</tr>
            	<tr>
            		<td>Upload your photo:</td>
            		<td><input type="file" name="photo" size="50"/></td>
            	</tr>
            	<tr>
            		<td colspan="2">
            			<input type="submit" value="Save">
            		</td>
            </table>
        </form>
    </body>
</body>
</html>