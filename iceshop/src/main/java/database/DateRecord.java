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
@WebServlet("/Taxnado")
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


			// UPDATEを実行し、結果表(ResultSet)を取得
				// ordersテーブルの消費税(tax_id)にvalueをUPDATEする
				String sql1 = "UPDATE taxs SET tax_id = 15 where tax_name='テイクアウト'";
				int num1 = stm.executeUpdate(sql1);
				String sql4 = "SELECT tax_id FROM taxs";
				ResultSet rs1 = stm.executeQuery(sql4);

				// pricesテーブルの割引(extra_id)にvalueをUPDATEする
				String sql2 = "UPDATE discounts SET discount_id = 20 where discount_name='10%'";
				int num2 = stm.executeUpdate(sql2);
				String sql5 = "SELECT discount_id FROM discounts";
				ResultSet rs2 = stm.executeQuery(sql5);

				// productsテーブルの支払い方法(paymethod_id)にvalueをUPDATEする
				String sql3 = "UPDATE paymethods SET paymethod_id = 30 where paymethod_name='cash'";
				int num3 = stm.executeUpdate(sql3);
				String sql6 = "SELECT paymethod_id FROM paymethods";
				ResultSet rs3 = stm.executeQuery(sql6);


			// 確認作業「結果表に格納されたレコードの表示」
			while(rs1.next()) {
				// UPDATEした行からINSERTしたデータを取得
				int id = rs1.getInt("tax_id");
				// 試しに取得したデータを出力する
				out.println("消費税:"+id);
				out.println("<br>");
			}
			while(rs2.next()) {
				// UPDATEした行からINSERTしたデータを取得
				int id = rs2.getInt("discount_id");
				// 試しに取得したデータを出力する
				out.println("割引:" + id);
				out.println("<br>");
			}
			while(rs3.next()) {
				// UPDATEした行からINSERTしたデータを取得
				int id = rs3.getInt("paymethod_id");
				// 試しに取得したデータを出力する
				out.println("支払い方法:" + id);
				out.println("<br>");
			}
			out.println("</body>");
			out.println("</html>");


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

