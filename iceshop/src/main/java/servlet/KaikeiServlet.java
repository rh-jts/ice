package MainProg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KaikeiServlet
 */
@WebServlet("/kaikei")
public class KaikeiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KaikeiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
//		RequestDispatcher d= request.getRequestDispatcher("/WEB-INF/jsp/kaikei.jsp");
//		d.forward(request, response);
//		String ordertotal = request.getParameter("ordertotal");
		String ordertotal ="1234";
		Data data = new Data();
		data.setOrderTotal(Integer.parseInt(ordertotal));
		request.setAttribute("data", data);


//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<title> sample2 </title>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("this is result"+ordertotal);
		RequestDispatcher d= request.getRequestDispatcher("/WEB-INF/jsp/kaikei.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
