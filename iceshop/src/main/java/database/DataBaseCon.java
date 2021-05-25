package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet("/DBC")
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
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			String drv="com.mysql.cj.jdbc.Driver";
			try {
	            Class.forName(drv);
	        }catch(ClassNotFoundException e) {
	            System.out.println("ドライバがありません"+e.getMessage());
	        }

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
			
			String ordertotal ="1234";
			Data data = new Data();
			data.setOrderTotal(Integer.parseInt(ordertotal));
			request.setAttribute("data", data);

			// ordersテーブルの消費税(tax_id)に#####をUPDATEする
			/*
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


			out.println("</body>");
			out.println("</html>");

*/
			// クレジット画面へ遷移
			String credit_url = "/WEB-INF/jsp/CreditCard.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(credit_url);
			rd.forward(request, response);

		} catch (SQLException e) {
			System.out.println("MySQLに接続できませんでした。");
			e.printStackTrace();

		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

        // 現金画面へ遷移
        String cash_url = "/WEB-INF/jsp/kaikei.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(cash_url);
        rd.forward(request, response);

	}

}
