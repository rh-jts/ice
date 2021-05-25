package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataBaseCon
 */
@WebServlet("/DataBaseConR")
public class DataBaseConR extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataBaseConR() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");


		try {
			Class.forName("com.mysql.jdbc.Driver");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> sample2 </title>");
			out.println("</head>");
			out.println("<body>");

			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");//データベースの情報
			Statement stmt = con.createStatement();

/*
			// ordersテーブルの消費税(tax_id)に#####をUPDATEする
            String sql1 = "UPDATE taxs SET tax_id = ##### where tax_name='テイクアウト'";
            int num1 = stmt.executeUpdate(sql1);
            ResultSet rs1 = stmt.executeQuery(sql1);

            // ordersテーブルの受取金額(order_recieved_amount)を#####にUPDATEする
            String sql2 = "UPDATE orders SET order_recieved_amount = ###### WHERE order_id = 1 IS NULL";
            int num2 = stmt.executeUpdate(sql2);
            ResultSet rs2 = stmt.executeQuery(sql2);

            // ordersテーブルの合計金額(order_total_amount)の更新作業
            String sql3 = "UPDATE orders SET order_total_amount = ##### WHERE order_id = 1 IS NULL";
            int num3 = stmt.executeUpdate(sql3);
            ResultSet rs3 = stmt.executeQuery(sql3);

            // pricesテーブルの割引(extra_id)にvalueをUPDATEする
            String sql4 = "UPDATE discounts SET discount_id = ##### WHERE discount_name='10%'";
            int num4 = stmt.executeUpdate(sql4);
            ResultSet rs4 = stmt.executeQuery(sql4);

            // productsテーブルの支払い方法(paymethod_id)にvalueをUPDATEする
            String sql5 = "UPDATE paymethods SET paymethod_id = ##### WHERE paymethod_name='cash'";
            int num5 = stmt.executeUpdate(sql5);
            ResultSet rs5 = stmt.executeQuery(sql5);

            //productsテーブルの小計(product_subtotal_amount)の更新作業
            String sql6 = "UPDATE products SET product_subtotal_amount = ##### WHERE product_id = 1";
            int num6 = stmt.executeUpdate(sql6);
            ResultSet rs6 = stmt.executeQuery(sql6);

            // productsテーブルの注文数(product_quantity)の更新作業
            String sql7 = "UPDATE products SET product_quantity = ##### WHERE product_id = 1 IS NULL";
            int num7 = stmt.executeUpdate(sql7);
            ResultSet rs7 = stmt.executeQuery(sql7);
            */


			//主キーと外部キーの設定
			//orderに格納
			String paym = "INSERT INTO orders(paymethod_id) SELECT paymethod_id from paymethods";
			String disc = "INSERT INTO orders(discount_id) SELECT discount_id from discounts";
			String tax = "INSERT INTO orders(tax_id) SELECT tax_id from taxs";
			//productsに格納
			String ord1 = "INSERT INTO products(order_id) SELECT order_id from orders";
			String icen1 = "INSERT INTO products(icenum_id) SELECT icenum_id from icenums";
			String cont = "INSERT INTO products(container_id) SELECT container_id from containers";
			//detailisに格納
			String ord2 = "INSERT INTO details(product_id) SELECT product_id from products";
			String flv = "INSERT INTO details(flavor_id) SELECT flavor_id from flavors";
			String siz1 = "INSERT INTO details(size_id) SELECT size_id from sizess";
			//pricesに格納
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
			String flvinfo = "SELECT details.flavor_id, flavor_name from details,flavors where details.flavor_id=flavors.flavor_id";
			String sizinfo = "SELECT details.size_id, size_name from details,sizes where details.size_id=sizes.size_id";
			String iceinfo = "SELECT products.icenum_id, icenum_name from products where products.icenum_id = icenums.icenum_id";
			String coninfo = "SELECT products.containe_id, container_name from products where products.container_id = containers.container_id";
			String gouinfo ="SELECT order_total_amount from orders ";




			//PreparedStatementのインスタンスを作成
			PreparedStatement pstmt1 = con.prepareStatement(flvinfo);
			PreparedStatement pstmt2 = con.prepareStatement(sizinfo);
			PreparedStatement pstmt3 = con.prepareStatement(iceinfo);
			PreparedStatement pstmt4 = con.prepareStatement(coninfo);

			//ResultSetインスタンスにSELECT文の内容を格納
			ResultSet infoset1 = pstmt1.executeQuery();
			ResultSet infoset2 = pstmt2.executeQuery();
			ResultSet infoset3 = pstmt3.executeQuery();
			ResultSet infoset4 = pstmt4.executeQuery();

			while (infoset1.next()) { //フレーバーの数だけ繰り返す
				//---idに---_nameを格納する
				String flvid = infoset1.getString("flavor_name");
				String sizid = infoset2.getString("size_name");
				String iceid = infoset3.getString("icenum_name");
				String conid = infoset4.getString("container_name");

				ReciptMid recinfo = new ReciptMid(flvid, sizid, iceid, conid);

				//"xxxxx"という名前に---idを格納し、レシート画面に送信する。
				request.setAttribute("recinfo", recinfo);
			}


			out.println("</body>");
			out.println("</html>");

			RequestDispatcher recrd = request.getRequestDispatcher("/WEB-INF/jsp/CreditCard.jsp");
			recrd.forward(request, response);


		} catch (SQLException e) {
			System.out.println("MySQLに接続できませんでした。");
			System.out.println(e.getMessage());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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
