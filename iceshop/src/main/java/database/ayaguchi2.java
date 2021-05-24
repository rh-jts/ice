package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ayaguchi2
 */
@WebServlet("/ayaguchi2")
public class ayaguchi2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ayaguchi2() {
        super();
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
		doGet(request, response);
	}
	// データベースに接続
	Connection con = (Connection) DriverManager.getConnection(
			"jdbc:mysql://localhost/ice?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
			"root", "root");
	Statement stm = con.createStatement();


	// UPDATEの実行
		// ordersテーブルの受取金額(order_recieved_amount)を10にUPDATEする
		String sql = "UPDATE orders SET order_recieved_amount = 10 order_id = 1 IS NULL";
		int num = stm.executeUpdate(sql);
		//確認作業「SELECTで結果を持ってきて、結果表(ResultSet)を取得」
		String sql2 = "SELECT order_received_amount FROM orders";
		ResultSet rs = stm.executeQuery(sql2);

	// 確認作業「結果表に格納されたレコードの表示」
	while(rs.next()) {
		// UPDATEした行からUPDATEしたデータを取得
		int id = rs.getInt("order_recieved_amount");
		// 試しに取得したデータを出力する
		out.println("受取金額:" + id);
		out.println("<br>");
	}
}
