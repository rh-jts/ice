package iceshop;

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
 * Servlet implementation class MainManu
 */
@WebServlet("/MainManu")
public class MainManu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainManu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
                    "jdbc:mysql://localhost/iceshop?serverTimezone=JST&useUnicode=true&characterEncoding=UTF-8", "root", "root");
            Statement stm = con.createStatement();

            String sql = "SELECT * FROM sizes";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()) {
                //行からデータを取得
                int id = rs.getInt("size_id");
                String name = rs.getString("size_name");
                out.println("size_id:" + id + "size_name:"+name );
                out.println("<br>");
            }
                out.println("</body>");
                out.println("</html>");


        }catch(SQLException e) {
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
