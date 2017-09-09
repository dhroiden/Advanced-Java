package trng.imcs.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trng.imcs.dao.BonusDAOImplementation;
import trng.imcs.dao.EmployeeDAOImplementations;
import trng.imcs.model.Employee;

/**
 * Servlet implementation class DepartmentServlet
 */
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("userIdValue");
		EmployeeDAOImplementations daoImplementations = new EmployeeDAOImplementations();
		List<Employee> employeeList= null;
		try {
			employeeList = daoImplementations.getEmployeeByDepId(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		if (employeeList!=null) {
			request.setAttribute("employeeList", employeeList);
			for(Employee employee:employeeList) {
				System.out.println(employee.getDepNo()+" "+employee.getEmpNo()+" "+employee.getSalary());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/pages/department.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "No employee's with the department id: "+id+" found.");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/nodata.jsp");
			rd.forward(request, response);
		}
	}

}
