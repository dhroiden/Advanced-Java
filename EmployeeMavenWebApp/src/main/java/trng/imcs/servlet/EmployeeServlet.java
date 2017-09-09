package trng.imcs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trng.imcs.dao.EmployeeDAOImplementations;
import trng.imcs.jdbc.JdbcConnectionFactory;
import trng.imcs.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EmployeeServlet() {
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
		Employee employee = null;
		try {
			employee = daoImplementations.getEmployeeById(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		if (employee!=null) {
			request.setAttribute("employee", employee);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/employee.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "No employee with the id: "+id+" found.");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/nodata.jsp");
			rd.forward(request, response);
		}
	}

}
