package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main2
 */
@WebServlet("/Main2")
public class Main2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					"jdbc:mysql://localhost/ice?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");
			Statement stm = con.createStatement();

			// UPDATEを実行し、結果表(ResultSet)を取得
				//productsテーブルの小計(product_subtotal_amount)の更新作業
				String sql1 = "UPDATE products SET product_subtotal_amount = 10 WHERE product_id = 1";
				int num1 = stm.executeUpdate(sql1);
				ResultSet rs1 = stm.executeQuery(sql1);


				// ordersテーブルの合計金額(order_total_amount)の更新作業
				String sql2 = "UPDATE orders SET order_total_amount = 20 WHERE order_id = 1 IS NULL";
				int num2 = stm.executeUpdate(sql2);
				ResultSet rs2 = stm.executeQuery(sql2);


				// productsテーブルの注文数(product_quantity)の更新作業
				String sql3 = "UPDATE products SET product_quantity = 30 WHERE product_id = 1 IS NULL";
				int num3 = stm.executeUpdate(sql3);
				ResultSet rs3 = stm.executeQuery(sql3);



		}catch(SQLException e) {
			System.out.println("MySQLに接続できませんでした。");
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
