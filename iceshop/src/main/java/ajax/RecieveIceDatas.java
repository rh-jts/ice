package ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
			
			//
			for(Ices i : icelist) {

				
				// アイス詳細情報（details）
				List<List<String>> ice_details = i.getIce();
				for(List<String> d_row : ice_details) {
					for (String d : d_row) {
						System.out.println(d);
					}
				}
				
				
				System.out.println(i.getContainer());
			}

//			String shows = "SELECT * FROM orders";
//			ResultSet rs = stmt.executeQuery(shows);
//			while (rs.next()) {
//				System.out.println(rs.getInt("order_id"));
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
