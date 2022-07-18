<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.io.PrintWriter" %>
 <%@ page import= "com.chainsys.jspproject.dao.EmployeeDao"
  import= "com.chainsys.jspproject.pojo.Employee"
  import="java.io.IOException" 
  import= "java.text.ParseException"
  import = "java.util.Date"
 import= "java.text.SimpleDateFormat"
 import= "com.chainsys.jspproject.commonutil.Validator"
 import= "com.chainsys.jspproject.commonutil.ExceptionManager"
 import="com.chainsys.jspproject.commonutil.InvalidInputDataException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete employee</title>
</head>
<body>
<%
String source="DeleteEmployee";
String message="<h1>Error while "+source+"</h1>";
Employee emp = new Employee();
// Employee emp = (Employee)request.getAttribute("empdelet");
String emp_id = request.getParameter("id");
try {
	Validator.checkStringForParseInt(emp_id);
} catch (InvalidInputDataException e) {
	message +=" Error in Employee id input </p>";
	String errorPage=ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1><%
}
int id = Integer.parseInt(emp_id);
int result = EmployeeDao.deleteEmployee(id);
%><h1><%=result%> row deleted</h1><%
try {
} catch (Exception e) {
	message += " Error in Employee id input </p>";
	String errorPage = ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1><%
}
%>
</body>
</html>