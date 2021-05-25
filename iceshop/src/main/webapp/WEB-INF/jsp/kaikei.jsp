<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="database.Data"%>
	<%
	Data data = (Data) request.getAttribute("data");
	%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>会計画面</title>
    <link rel="stylesheet" href="/iceshop/statics/css/style.css">
    <link href="/iceshop/statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body class="border border-dark" style="margin:auto; margin-top:17px; padding:18px;  width:1100px; height:700px;">
    <script>
    function dis(val) {
        document.getElementById("result").value += val
        var event = new Event('change', { bubbles: true });
        document.getElementById("result").dispatchEvent(event);
    }
    // String型からint型に変換： var b= + value;
    function getResult(result) {
            var result = result+0;
            return result;
    }
    //CLEARボタンの動作
    function clr() {
        document.getElementById("result").value = ""
        	var event = new Event('change', { bubbles: true });
        	document.getElementById("result").dispatchEvent(event);
    }
    //バックスペースボタンの動作
    function bs() {
        var b = document.getElementById("result").value;
        if (b.length != 0) {
           b= b.substring(0,b.length-1)
        }
        document.getElementById("result").value =b;
        var event = new Event('change', { bubbles: true });
        document.getElementById("result").dispatchEvent(event);
    }
    function otsuriKeisan(){
    	var otsuri = parseInt(document.getElementById("result").value) - parseInt(document.getElementById("goukei").innerText)
    	if(otsuri>0){
        	document.getElementById("otsuri").value = otsuri
    	}else{
        	document.getElementById("otsuri").value = 0
    	}
    }


        <%--
        getElementbyID("otsuri").innerHTML= <%=document.getElementById("result").value%>- <%= data.getOrderTotal() %>
    --%>
    </script>

        <a href="https://stackoverflow.com/">
        <button id="backButton" type="button" class="btn btn-outline-primary" >戻る</button>
        </a>
        <form action="/Receipt.jsp" method="post">
        <table class="table table-striped" id="money">
            <tr>
		<th>合計金額</th>
		<td><span id="goukei"><%= data.getOrderTotal() %></span><span>円</span> </td>
            </tr>
            <tr>
            <th>受取金額</th>
                <td><input type="number" name="uketori" id="result" onchange="otsuriKeisan()" size="50px"> 円</td>
            </tr>
            <tr>
            <th >釣銭</th>
                <td><input type="number" name="otsuri"id="otsuri">円</td>
            </tr>
        </table>

        <table class="calculator">
            <tbody class="calculatorBody">
                <tr>
                    <td colspan="2"><button class="btn btn-outline-secondary btn-block"  style="width:198px; height:60px" onclick="clr()">CLEAR</button></td>
                    <td><button class="btn btn-block btn-outline-dark" id="bs" style="width:98px; height:60px" onclick="bs()" ><i class="material-icons">&#xe14a;</i></button></td>
                </tr>
                <tr>
                    <td>
                        <button class="btn btn-block btn-outline-info " style="width:98px; height:60px" onclick="dis('7')">7</button></td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px"  onclick="dis('8')" >8</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('9')" >9</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('4')">4</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('5')">5</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('6')">6</button>
                    </td>
                </tr>
                <tr>
		    <td>
                    <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('1')">1</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('2')">2</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('3')">3</button>
                    </td>
		</tr>
                <tr>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('0')">0</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('00')">00</button>
                    </td>
                    <td>
                        <button class="btn btn-block btn-outline-info" style="width:98px; height:60px" onclick="dis('000')">000</button>
                    </td>
                </tr>
            </tbody>
        </table>
    <div class="text-right" id="confirm">
        <a href="http://localhost:8080/iceshop/receipt">
        <input type="submit" class="btn btn-outline-danger" style="font-size:20px; width:100px; height:50px; margin-top:-150px; margin-left:700px" value="確定">
        </a>
    </div>
    </form>
</body>
</html>