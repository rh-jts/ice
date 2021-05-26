package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Data;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//		RequestDispatcher d= request.getRequestDispatcher("/WEB-INF/jsp/kaikei.jsp");
		//		d.forward(request, response);
		//		String ordertotal = request.getParameter("ordertotal");
		
		
		try {
			Thread.sleep(2000);
			// DB接続処理
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");
			Statement stmt = con.createStatement();
			// ordersテーブルで最も新しい行のorder_total_amountを取得
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM orders ORDER BY order_created_on DESC LIMIT 1");
			rs.next();
			int ordertotal = rs.getInt("order_total_amount");
			System.out.println("最新合計金額 : " + ordertotal);
			//comment
			Data data = new Data();
			data.setOrderTotal(ordertotal);
			request.setAttribute("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}


//		String ordertotal = "1234";


		//		PrintWriter out = response.getWriter();
		//		out.println("<!DOCTYPE html>");
		//		out.println("<html>");
		//		out.println("<head>");
		//		out.println("<title> sample2 </title>");
		//		out.println("</head>");
		//		out.println("<body>");
		//		out.println("this is result"+ordertotal);
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/kaikei.jsp");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
