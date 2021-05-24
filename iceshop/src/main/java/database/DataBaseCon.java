package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataBaseCon
 */
@WebServlet("/DataBaseCon")
public class DataBaseCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataBaseCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> sample2 </title>");
			out.println("</head>");
			out.println("<body>");

			// データベースに接続
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");
			Statement stmt = con.createStatement();

			//order
			String paym = "INSERT INTO orders(pricemethod_id) SELECT paymethod_id from paymethods";
			String disc = "INSERT INTO orders(discount_id) SELECT discount_id from discounts";
			String tax = "INSERT INTO orders(tax_id) SELECT tax_id from taxs";
			//products
			String ord1 = "INSERT INTO products(order_id) SELECT order_id from orders";
			String icen1 = "INSERT INTO products(icenum_id) SELECT icenum_id from icenums";
			String cont = "INSERT INTO products(container_id) SELECT container_id from containers";
			//detailis
			String ord2 = "INSERT INTO details(order_id) SELECT order_id from products";
			String flv = "INSERT INTO details(flavor_id) SELECT flavor_id from flavors";
			String siz1 = "INSERT INTO details(size_id) SELECT size_id from sizess";
			//prices
			String siz2 = "INSERT INTO prices(size_id) SELECT size_id from sizes";
			String icen2 = "INSERT INTO prices(order_icenum) SELECT icenum_id from icenums";

			//更新
			int upd = stmt.executeUpdate(paym);
			int upd2 = stmt.executeUpdate(disc);
			int upd3 = stmt.executeUpdate(tax);
			int upd4 = stmt.executeUpdate(ord1);
			int upd5 = stmt.executeUpdate(icen1);
			int upd6 = stmt.executeUpdate(cont);
			int upd7 = stmt.executeUpdate(ord2);
			int upd8 = stmt.executeUpdate(flv);
			int upd9 = stmt.executeUpdate(siz1);
			int upd10 = stmt.executeUpdate(siz2);
			int upd11 = stmt.executeUpdate(icen2);

			//レシート画面に出力するアイスの情報
			String sql1 = "SELECT flavor_id,size_id from details";
			String sql2 = "SELECT icenum_id, container_id from details";

			PreparedStatement pstmt = con.prepareStatement(sql1);
			PreparedStatement pstmt2 = con.prepareStatement(sql2);

			ResultSet rs1 = pstmt.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();

			//detailsのflover_idを受け取り、flvidをflavorsテーブルから
			//detailsのsize_idを受け取り、sizidをsizesテーブルから参照
			while(rs1.next()) {
				int flvid = rs1.getInt("flavor_id");
				int sizid = rs1.getInt("size_id");
				System.out.println(flvid + sizid);
			}

			while(rs2.next()) {
				int iceid = rs2.getInt("icenum_id");
				int conid = rs2.getInt("container_id");
				System.out.println(iceid + conid);
			}


			out.println("</body>");
			out.println("</html>");

		} catch (SQLException e) {
			System.out.println("MySQLに接続できませんでした。");
			System.out.println(e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		String forwardPath = null;
		String action = request.getParameter("action");
		forwardPath= "/WEB-INF/jsp/Recipt.jps";
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
