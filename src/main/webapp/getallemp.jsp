<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  
    import = "java.util.List,com.chainsys.jspproject.pojo.Employee,java.util.ArrayList"
    import = "com.chainsys.jspproject.dao.EmployeeDao"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get All Employees</title>
</head>
<body>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<table style="width:100%"> 
<thead> </thead>
 <DIV align="center"><TABLE>
<tr>
<th>Employee ID</th>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
<th>Hire Date</th>
<th>Job ID</th>
<th>Salary</th>
</tr>
<% 
List<Employee> allEmployee =  EmployeeDao.getAllEmployees();
// List<Employee> allEmployee = (ArrayList<Employee>)request.getAttribute("emplist");
for(Employee emp: allEmployee){
 %>
 <tr>
  <td> <%=emp.getEmp_id()%>  </td>
  <td> <%=emp.getFirst_name()%>  </td>
  <td> <%=emp.getLast_name()%>  </td>
  <td> <%=emp.getEmail()%>  </td>
  <td> <%=emp.getHire_date()%>  </td>
  <td> <%=emp.getJob_id()%>  </td>
  <td> <%=emp.getSalary()%>  </td>
  
  
 </tr>
 <%}%>
 </table>
</body>
</html>