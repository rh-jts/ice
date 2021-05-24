package ajax;

import java.io.BufferedReader;
import java.io.IOException;
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

import packw.Ices;
import packw.JsonObject;

@WebServlet("/RecieveIceDatas")
@JsonIgnoreProperties(ignoreUnknown=true)
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
		
		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		System.out.println(reqJson);
		
    	String json = "{\"ices\":[{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"ice\":[[0,3],[null,null],[null,null]],\"price\":520,\"is_active\":true},{\"product_num\":1,\"icenum\":2,\"container\":1,\"quantity\":1,\"ice\":[[3,2],[8,1],[null,null]],\"price\":620,\"is_active\":true}]}";
		
		ObjectMapper mapper = new ObjectMapper();
		JsonObject jo = mapper.readValue(reqJson, JsonObject.class);
    	
    	List<Ices> icelist = new ArrayList<Ices>();
    	icelist = jo.getIces();
    	System.out.println(icelist.get(0).getContainer());

	}
}
