package servlet;

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

import ajax.Ices;
import ajax.JsonObject;

@WebServlet("/JsonTest")
@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET");

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/HTML/jsonpost.html");
		dispatcher.forward(request, response);
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST START");

		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		System.out.println(reqJson);

    	String json = "{\"ices\":[{\"product_num\":0,\"icenum\":1,\"container\":2,\"quantity\":1,\"ice\":[[0,3],[null,null],[null,null]],\"price\":520,\"is_active\":true},{\"product_num\":1,\"icenum\":2,\"container\":1,\"quantity\":1,\"ice\":[[3,2],[8,1],[null,null]],\"price\":620,\"is_active\":true}]}";

		ObjectMapper mapper = new ObjectMapper();
		JsonObject jo = mapper.readValue(json, JsonObject.class);


    	List<Ices> icelist = new ArrayList<Ices>();
    	icelist = jo.getIces();
    	System.out.println(icelist.get(0).getContainer());

//		// リクエストパラメータを取得
//		String weight = request.getParameter("weight"); // 体重
//		String height = request.getParameter("height"); // 身長
//
//		// 入力値をプロパティに設定
//		Health health = new Health();
//		health.setHeight(Double.parseDouble(height));
//		health.setWeight(Double.parseDouble(weight));
//		// 健康診断を実行し結果を設定
//		HealthCheckLogic healthCheckLogic = new HealthCheckLogic();
//		healthCheckLogic.execute(health);
//
//		// リクエストスコープに保存
//		request.setAttribute("health", health);
//
//		// フォワード
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/healthCheckResult.jsp");
//		dispatcher.forward(request, response);
	}
}
