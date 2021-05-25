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
 * Servlet implementation class ConnectDB
 */
@WebServlet("/ConnectDB")
public class ConnectDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectDB() {
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
			Statement Stm = con.createStatement();

			//out.printline();はデータの受け取りができているかの確認
			String container_name = "SELECT * FROM containers";
			ResultSet rsA = Stm.executeQuery(container_name);
			while(rsA.next()) {
				String cupnm = rsA.getString("container_name");
			out.println("container_name" + cupnm);
			}

			String flavor_name = "SELECT * FROM flavors";
			ResultSet rsB = Stm.executeQuery(flavor_name);
			while(rsB.next()) {
				String flavornm = rsB.getString("flavor_name");
			out.println("flavor_name" + flavornm );
			}

			String icenum_name = "SELECT * FROM icenums";
			ResultSet rsC = Stm.executeQuery(icenum_name);
			while(rsC.next()) {
				String icnumnm = rsC.getString("icenum_name");
			out.println("icenum_name" + icnumnm);
			}

			String size_name = "SELECT * FROM sizes";
			ResultSet rsD = Stm.executeQuery(size_name);
			while(rsD.next()) {
				String sizenm = rsD.getString("size_name");
			out.println("size_name" + sizenm);
			}

			String prdoduct_quantity = "SELECT product_quantity FROM products";
			ResultSet rsE = Stm.executeQuery(prdoduct_quantity);
			while(rsE.next()) {
				int proq = rsE.getInt("product_quantity");
			out.println("product_quantity" + proq);
			}

		}catch(SQLException e) {
		System.out.println("MySQLに接続できませんでした。");
		System.out.println(e.getMessage());//実行後にコンソールで不具合が確認できる。

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
