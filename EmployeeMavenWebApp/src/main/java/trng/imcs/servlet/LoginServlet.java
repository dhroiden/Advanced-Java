package trng.imcs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trng.imcs.empcred.EmpCreds;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		EmpCreds empCreds = new EmpCreds();
		if(empCreds.myMap.containsKey(userName) && empCreds.myMap.get(userName).equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("/landing.html");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "No employee with the given password and username was found.");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/nodata.jsp");
			rd.forward(request, response);
		}
	}

}
