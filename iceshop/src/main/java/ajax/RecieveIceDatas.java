package ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/RecieveIceDatas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecieveIceDatas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/HTML/main.html");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// DB接続処理
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8",
					"root", "root");
			Statement stmt = con.createStatement();

			// 送信されたJSONの取得
			BufferedReader buffer = new BufferedReader(request.getReader());
			String reqJson = buffer.readLine();
			System.out.println(reqJson);

			// JSONをJavaクラスへ格納する
			ObjectMapper mapper = new ObjectMapper();
			JsonObject jo = mapper.readValue(reqJson, JsonObject.class);

			// アイスの情報をリストに格納
			List<Ices> icelist = new ArrayList<Ices>();
			icelist = jo.getIces();
			
			// 税率と割引率をint型のリストで格納
			List<Integer> td = jo.getTax_discounts();
			
			// 合計金額を取得
			int total = jo.getTotal();
			System.out.println("合計金額 " + total);
			
			// ordersテーブルに行を追加
			String add_order_sql = "INSERT INTO orders(discount_id, tax_id, order_total_amount) VALUES(" + td.get(1) + ", " + td.get(0) + ", " + total + ")";
			stmt.executeUpdate(add_order_sql);
			
			// ordersテーブルで最も新しい行のorder_idを取得
			ResultSet rs = stmt.executeQuery("SELECT order_id FROM orders ORDER BY order_created_on DESC LIMIT 1");
			rs.next();
			int latest_order_id = rs.getInt("order_id");
			System.out.println("最新order_id : " + latest_order_id);
			
			// productsとdetailsテーブルへ行を追加
			for(Ices i : icelist) {
				//productsテーブルにアイス情報を追加
				String add_product_sql = "INSERT INTO products(order_id, icenum_id, container_id, product_quantity, product_subtotal_amount) "
						+ "VALUES(" + latest_order_id + ", " + i.getIcenum() + ", " + i.getContainer() + ", " + i.getQuantity() + ", " + i.getPrice() + ")";
				stmt.executeUpdate(add_product_sql);
				
				// productsテーブルで最も新しい行のproduct_idを取得
				ResultSet product_rs = stmt.executeQuery("SELECT product_id FROM products ORDER BY product_id DESC LIMIT 1");
				product_rs.next();
				int latest_product_id = product_rs.getInt("product_id");
				System.out.println("最新product_id : " + latest_product_id);
				
				// detailsテーブルにアイス詳細情報を追加
				System.out.println("---------- INSERT DETAILS ----------");
				List<List<Integer>> ice_details = i.getIce();
				for(List<Integer> d : ice_details) {
					if (d.get(0) == null) break;
					
					System.out.println(d);
					String add_detail_sql = "INSERT INTO details(product_id, flavor_id, size_id) "
							+ "VALUES(" + latest_product_id + ", " + d.get(0) + ", " + d.get(1) +")";
					stmt.executeUpdate(add_detail_sql);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
