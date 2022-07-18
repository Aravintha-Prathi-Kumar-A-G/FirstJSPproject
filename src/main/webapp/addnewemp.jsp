<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.PrintWriter" %>
 <%@ page import= "com.chainsys.jspproject.dao.EmployeeDao"%>
 <%@ page import= "com.chainsys.jspproject.pojo.Employee"%>
 <%@ page import="java.io.IOException" %>
 <%@ page import= "java.text.ParseException"%>
 <%@ page import= "java.util.Date" %>
 <%@ page import= "java.text.SimpleDateFormat" %>
 <%@ page import= "com.chainsys.jspproject.commonutil.Validator"%>
 <%@ page import= "com.chainsys.jspproject.commonutil.ExceptionManager"%>
 <%@ page import="com.chainsys.jspproject.commonutil.InvalidInputDataException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Employee</title>
</head>
<body>
<%
String source="AddNewEmployee";
String message="<h1>Error while "+source+"</h1>";
Employee emp = new Employee();
//Employee emp =(Employee)request.getAttribute("addemp");
try {

	String id = request.getParameter("id");
	try {
		Validator.checkStringForParseInt(id);
	} catch (InvalidInputDataException e) {
		message +=" Error in Employee id input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return; // It terminates the Code execution beyond this point 
	}
	int id1 = Integer.parseInt(id);
	try {
		Validator.CheckNumberForGreaterThanZero(id1);
	} catch (InvalidInputDataException e) {
		message +=" Error in Employee id input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);					out.print(errorPage);
       return;
	}
	emp.setEmp_id(id1);

	String emp_Firstname = request.getParameter("fname");
	try {
		Validator.checkStringOnly(emp_Firstname);
	} catch (InvalidInputDataException e) {
		message +=" Error in First Name input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	emp.setFirst_name(emp_Firstname);
	String emp_LastName = request.getParameter("Lname");
	try {
		Validator.checkStringOnly(emp_LastName);
	} catch (InvalidInputDataException e) {
		message +=" Error in Last Name input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	emp.setLast_name(emp_LastName);
	String emp_email = request.getParameter("email");
	try {
		Validator.checkEmail(emp_email);
	} catch (InvalidInputDataException e) {
		message +=" Error in email input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	emp.setEmail(emp_email);
	SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
	String emp_HireDate = request.getParameter("hdate");
	// Date hire_date=hire_dateFormate.parse(emp_HireDate);

	try {
		Validator.checkDate(emp_HireDate);
		Validator.checkHireDate(emp_HireDate);
	} catch (InvalidInputDataException e) {
		message +=" Error in Hire Date input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	try {
		emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
	} catch (ParseException e) {
		message +=" Error in Hire Date input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	String emp_Job_id = request.getParameter("jobid");
	try {
		Validator.checkJobId(emp_Job_id);
	} catch (InvalidInputDataException e) {
		message +=" Error in Job Id input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	emp.setJob_id(emp_Job_id);
	String emp_salary = null;
	try {
		emp_salary = request.getParameter("salary");
		Validator.checkFloat(emp_salary);
	} catch (InvalidInputDataException e) {
		message +=" Error in Salary input </p>";
		String errorPage=ExceptionManager.handleException(e, source, message);
		%><h1><%=errorPage%></h1><%
       return;
	}
	float salary = Float.parseFloat(emp_salary);
	emp.setSalary(salary);
	int result = EmployeeDao.insertEmployee(emp);
	out.println(result + "row inserted");
} catch (Exception e) {
	message +=" Error while inserting record </p>";
	String errorPage=ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1><%
   return;
   }
try {
} catch (Exception e) {
	message +="Message: "+e.getMessage();
	String errorPage=ExceptionManager.handleException(e, source, message);
	%><h1><%=errorPage%></h1><%
   return;
}
%>
</body>
</html>