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
 * Servlet implementation class Main1
 */
@WebServlet("/Taxnado_a")
public class DateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateRecord() {
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
					"jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");
			Statement stm = con.createStatement();



			// ordersテーブルの消費税(tax_id)にvalueをUPDATEする
            String sql1 = "UPDATE taxs SET tax_id = 15 where tax_name='テイクアウト'";
            int num1 = stm.executeUpdate(sql1);
            ResultSet rs1 = stm.executeQuery(sql1);



            // ordersテーブルの受取金額(order_recieved_amount)を10にUPDATEする
            String sql2 = "UPDATE orders SET order_recieved_amount = 10 order_id = 1 IS NULL";
            int num2 = stm.executeUpdate(sql2);
            ResultSet rs2 = stm.executeQuery(sql2);



            // ordersテーブルの合計金額(order_total_amount)の更新作業
            String sql3 = "UPDATE orders SET order_total_amount = 20 WHERE order_id = 1 IS NULL";
            int num3 = stm.executeUpdate(sql3);
            ResultSet rs3 = stm.executeQuery(sql3);



            // pricesテーブルの割引(extra_id)にvalueをUPDATEする
            String sql4 = "UPDATE discounts SET discount_id = 20 where discount_name='10%'";
            int num4 = stm.executeUpdate(sql4);
            ResultSet rs4 = stm.executeQuery(sql4);



            // productsテーブルの支払い方法(paymethod_id)にvalueをUPDATEする
            String sql5 = "UPDATE paymethods SET paymethod_id = 30 where paymethod_name='cash'";
            int num5 = stm.executeUpdate(sql5);
            ResultSet rs5 = stm.executeQuery(sql5);



            //productsテーブルの小計(product_subtotal_amount)の更新作業
            String sql6 = "UPDATE products SET product_subtotal_amount = 10 WHERE product_id = 1";
            int num6 = stm.executeUpdate(sql6);
            ResultSet rs6 = stm.executeQuery(sql6);



            // productsテーブルの注文数(product_quantity)の更新作業
            String sql7 = "UPDATE products SET product_quantity = 30 WHERE product_id = 1 IS NULL";
            int num7 = stm.executeUpdate(sql7);
            ResultSet rs7 = stm.executeQuery(sql7);

		}catch(SQLException e) {
			System.out.println("MySQLに接続できませんでした。");
			e.printStackTrace();

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

