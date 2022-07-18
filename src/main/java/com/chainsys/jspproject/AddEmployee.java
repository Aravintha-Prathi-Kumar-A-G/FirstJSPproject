package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		List<Employee> emplist = EmployeeDao.getAllEmployees();
		Iterator<Employee> empItr = emplist.iterator();

		if (request.getParameter("input").equals("yes")) {
			response.setContentType("text/html");
			out.print("<html><head><title><Employees</title></head><body>");
			out.print("<table border=1px bgcolor=\"DodgerBlue\" width=50%>");
			out.print("<tr bgcolor=\"DarkSlateBlue\" align=center>");
			out.print("<th height=\"10\" width=\"90\">Emp_id:</th>");
			out.print("<th height=\"10\" width=\"90\">First_name:</th>");
			out.print("<th height=\"10\" width=\"90\">Last_name:</th>");
			while (empItr.hasNext()) {
				out.print("<tr align=center>");
				Employee emp = empItr.next();
				out.print("<td bgcolor=\"DeepSkyBlue\">" + emp.getEmp_id() + "</td>");
				out.print("<td bgcolor=\"DeepSkyBlue\">" + emp.getFirst_name() + "</td>");
				out.print("<td bgcolor=\"DeepSkyBlue\">" + emp.getLast_name() + "</td>");
				out.print("</tr>");
//			out.println("emp id:"+emp.getEmployee_id()+","+emp.getFirst_name()+","+
//					emp.getLast_name()+",");

			}
		} else
			out.println(" <br><h4>thank you for processing with us</h4>");
		out.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if (request.getParameter("submit").equals("Add")) {
			String source="AddNewEmployee";
			String message="<h1>Error while "+source+"</h1>";
			
			Employee emp = new Employee();
			try {

				String id = request.getParameter("id");
				try {
					Validator.checkStringForParseInt(id);
				} catch (InvalidInputDataException e) {
					message +=" Error in Employee id input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
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
					out.print(errorPage);
                   return;
				}
				emp.setFirst_name(emp_Firstname);
				String emp_LastName = request.getParameter("Lname");
				try {
					Validator.checkStringOnly(emp_LastName);
				} catch (InvalidInputDataException e) {
					message +=" Error in Last Name input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
                   return;
				}
				emp.setLast_name(emp_LastName);
				String emp_email = request.getParameter("email");
				try {
					Validator.checkEmail(emp_email);
				} catch (InvalidInputDataException e) {
					message +=" Error in email input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
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
					out.print(errorPage);
                   return;
				}
				try {
					emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
				} catch (ParseException e) {
					message +=" Error in Hire Date input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
                   return;
				}
				String emp_Job_id = request.getParameter("jobid");
				try {
					Validator.checkJobId(emp_Job_id);
				} catch (InvalidInputDataException e) {
					message +=" Error in Job Id input </p>";
					String errorPage=ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
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
					out.print(errorPage);
                   return;
				}
				float salary = Float.parseFloat(emp_salary);
				emp.setSalary(salary);
				int result = EmployeeDao.insertEmployee(emp);
				out.println(result + "row inserted");
			} catch (Exception e) {
				message +=" Error while inserting record </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
               return;
               }
			try {
				out.close();
			} catch (Exception e) {
				message +="Message: "+e.getMessage();
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
               return;
			}
		}else if(request.getParameter("submit").equals("update")) {
			doPut(request,response);
		}else if(request.getParameter("submit").equals("Delete")) {
			doDelete(request,response);
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String source="UpdateEmployee";
		String message="<h1>Error while "+source+"</h1>";
		PrintWriter out = response.getWriter();
		Employee emp = new Employee();
		try {

			String emp_id = request.getParameter("id");
			try {
				Validator.checkStringForParseInt(emp_id);
			} catch (InvalidInputDataException e) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;

			}
			int id = Integer.parseInt(emp_id);
			try {
				Validator.CheckNumberForGreaterThanZero(id);
			} catch (InvalidInputDataException e) {
				message +=" Error in Employee id input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			emp.setEmp_id(id);

			String emp_Firstname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(emp_Firstname);
			} catch (InvalidInputDataException e) {
				message +=" Error in First Name input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			emp.setFirst_name(emp_Firstname);
			String emp_LastName = request.getParameter("Lname");
			try {
				Validator.checkStringOnly(emp_LastName);
			} catch (InvalidInputDataException e) {
				message +=" Error in Lat Name input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			emp.setLast_name(emp_LastName);
			String emp_email = request.getParameter("email");
			try {
				Validator.checkEmail(emp_email);
			} catch (InvalidInputDataException e) {
				message +=" Error in email input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
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
				out.print(errorPage);
				return;
			}
			try {
				emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
			} catch (ParseException e) {
				message +=" Error in Hire Date input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			String emp_Job_id = request.getParameter("jobid");
			try {
				Validator.checkJobId(emp_Job_id);
			} catch (InvalidInputDataException e) {
				message +=" Error in Job Id input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
				
			}
			emp.setJob_id(emp_Job_id);
			String emp_salary = request.getParameter("salary");
			try {
				Validator.checkFloat(emp_salary);
			} catch (InvalidInputDataException e) {
				message +=" Error in salary input </p>";
				String errorPage=ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
				
			}
			float salary = Float.parseFloat(emp_salary);
			emp.setSalary(salary);
			int result = EmployeeDao.updateEmployee(emp);
			out.println(result + "row Updated");
		} catch (Exception e) {
			message +=" Error while inserting record </p>";
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
			return;
		}
		try {
			out.close();
		} catch (Exception e) {
			message +=" Error in Hire Date input </p>";
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source="DeleteEmployee";
		String message="<h1>Error while "+source+"</h1>";
		String emp_id = request.getParameter("id");
		try {
			Validator.checkStringForParseInt(emp_id);
		} catch (InvalidInputDataException e) {
			message +=" Error in Employee id input </p>";
			String errorPage=ExceptionManager.handleException(e, source, message);
			out.print(errorPage);
		}
		int id = Integer.parseInt(emp_id);
		int result = EmployeeDao.deleteEmployee(id);
		out.println(result + "row deleted");
		try {
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
