<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="database.ReciptMid" %>    
<%  ReciptMid reinfo = (ReciptMid)request.getAttribute("recinfo"); %>    
    
    
<!DOCTYPE html>
<html lang="ja">
<head>
	<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
    crossorigin="anonymous"
  />

	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>

	<script>

		var today=new Date();
		//年・月・日・曜日を取得
		var year = today.getFullYear();
		var month = today.getMonth()+1;
		var week = today.getDay();
		var day = today.getDate();

		var week_ja= new Array("日","月","火","水","木","金","土");


		var time= new Date();
		//時・分・秒を取得
		var hour = time.getHours();
		var minute = time.getMinutes();
		var second = time.getSeconds();
	</script>


</head>
<body class="border border-dark" style="margin:10px;margin-left:20px; padding:18px; width:400px; height:100%">

<body>

	<h1 style="text-align:center">３１アイスクリーム</h1>
	<br> <br>
	<h2 style="text-align:center">領収書</h2>
	<hr>
	<br>



	<p style="text-align:left">日時</p>
	<p style="text-align:right">
		<script>document.write(year+"年"+month+"月"+day+"日 "
		+week_ja[week]+"曜日",hour+"時",+minute+"分"+second+"秒");</script>
	</p>
	<br> <br> <br>

	<table>
		<tbody id="table">
			<tr>
			<td>アイス1</td>
			<td>500</td>
			</tr>
			
		</tbody>
	</table>


	<br> <br>

	<%int totalPrice=1000;
	  int tax=100;
	  int discount=0;
	  int finalPrice=1100;
	  int receive=2000;
	  int change=900;
	  %>

	 <hr>

	<p style="text-align:left">小計</p>
	<h2 id="afterTax" style="text-align:right"><%=totalPrice+tax %>円</h2>
	<br>
	<p style="text-align:left">（消費税）</p>
	<h2 style="text-align:right"><%=tax %>円</h2>
	<br>
	<p style="text-align:left">割引</p>
	<h2 id="afterDiscount" style="text-align:right"><%=discount%>円</h2>
	<br> <br>
	<p style="text-align:left">合計</p>
	<h2 id="final" style="text-align:right"><%=finalPrice %>円</h2>
	<br> <br>
	<p style="text-align:left">受取金</p>
	<h2 id="receip" style="text-align:right"><%=receive %>円</h2>
	<br> <br>
	<p style="text-align:left">釣り銭</p>
	<h2 id="change" style="text-align:right"><%=change %>円</h2>
	<hr>
	<br> <br> <br>

	<from>

    <p>
		<button type="submit"
    class="btn btn-outline-info float-end"
    onclick="location.href='///C:/Users/user2021/Documents/グループ演習/flavors_triple_page3.html'"
    >次の会計へ
        </button>
    </p>
    <br> <br>


	</from>




	<script>

	<%String ice1="バニラ";
    int amount1=300;
    %>


		let order=[ice1,ice2,ice3]; //注文されたアイスの種類を配列で取得
		order = document.getElementById("table");

		let amoumt=[500,300,560]; //そのアイスの値段を配列で取得
		amoumt = document.getElementById("table");



		//id=tableを所得してtable変数に代入
		let table=document.getElementById("table");
		console.log(table);


		for(i=1;i<order.lenghth;i++){
			table.insertAdjacentHTML
			("beforeend","<td>order[i]</td><td>amount[i]</td>");
		};








	</script>

    <script src="js/main.js"></script>



</body>
</html>