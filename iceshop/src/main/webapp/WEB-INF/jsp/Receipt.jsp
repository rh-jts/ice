<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>レシート</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
      crossorigin="anonymous"
    />
  </head>
  <body
    class="border border-dark"
    style="
      margin: 10px;
      margin-left: 20px;
      padding: 18px;
      width: 400px;
      height: 100%;
    "
  >
    <h1 style="text-align: center">３１アイスクリーム</h1>
    <br />
    <br />
    <h2 style="text-align: center">領収書</h2>
    <hr />
    <br />

    <p style="text-align: left">日時</p>
    <p id="date" style="text-align: right"></p>

    <table class="table">
      <tbody id="table_body"></tbody>
    </table>

	<%String uketori = request.getParameter("uketori"); %>
	<%String otsuri = request.getParameter("otsuri"); %>

    <p style="text-align: left">小計</p>
    <h2 id="subtotal" style="text-align: right">円</h2>
    <br />
    <p style="text-align: left">（消費税）</p>
    <h2 id="tax" style="text-align: right">円</h2>
    <br />
    <p style="text-align: left">割引</p>
    <h2 id="afterDiscount" style="text-align: right">円</h2>
    <br />
    <br />
    <p style="text-align: left">合計</p>
    <h2 id="final" style="text-align: right">円</h2>
    <br />
    <br />
    <p style="text-align: left">受取金</p>
    <h2 id="recieved" style="text-align: right"></h2>
    <br />
    <br />
    <p style="text-align: left">釣り銭</p>
    <h2 id="change" style="text-align: right"></h2>
    <hr />
    <br />
    <br />
    <br />



    <script>
      const ids = JSON.parse(sessionStorage.ice_data);
      let ice_data = {"ices" : [], "tax_discounts" : ids.tax_discounts, "total" : ids.total};
      ids.ices.forEach(v => {
        if (v != null) {
        ice_data.ices.push(v);
        }
      });

      const table_body = document.getElementById("table_body");

      const cup_names = ["コーン", "カップ", "ワッフル"];
      const icenum_names = ["キッズ", "スモール", "レギュラー", "キング"];
      const flavor_names = [
        "バニラ",
        "チョコレート",
        "キャラメルリボン",
        "ラムレーズン",
        "レモンシャーベット",
        "クッキー＆クリーム",
        "バナナ＆ストロベリー",
        "抹茶",
        "チョップチョコレート",
        "ナッツトゥユー",
        "ロッキーロード",
        "ストロベリー",
      ];

      let today = new Date();
      //年・月・日・曜日を取得
      let year = today.getFullYear();
      let month = today.getMonth() + 1;
      let week = today.getDay();
      let day = today.getDate();
      let week_ja = new Array("日", "月", "火", "水", "木", "金", "土");
      let time = new Date();
      //時・分・秒を取得
      let hour = time.getHours();
      let minute = time.getMinutes();
      let second = time.getSeconds();

      let today_string =
        year +
        "年" +
        month +
        "月" +
        day +
        "日 " +
        week_ja[week] +
        "曜日" +
        hour +
        "時" +
        minute +
        "分" +
        second +
        "秒";

      console.log(today_string);

      let subtotal_price = 0;
      let tax_price = 0;

      ice_data.ices.forEach((v) => {
        let row_html = "";
        let detail_html = "";

        v.ice.forEach((d, i) => {
          if (d[0] == null) return;
          if (i >= 1) detail_html += "<br>";
          detail_html += flavor_names[d[0]] + "（" + icenum_names[d[1]] + "）";
        });

        row_html =
          "<tr><td>" +
          detail_html +
          "</td><td>" +
          cup_names[v.container] +
          "</td><td>" +
          v.price +
          "円</td></tr>";

        table_body.insertAdjacentHTML("beforeend", row_html);

        subtotal_price += v.price * v.quantity;
      });

      let diff = subtotal_price - ice_data.total;
      tax_price = parseInt(subtotal_price * (parseFloat(ice_data.tax_discounts[1]) / 100));
      let discount_price = diff + tax_price;
      const recieved_amount = (isNaN(sessionStorage.recieved_amount) || sessionStorage.recieved_amount == null) ? ice_data.total : sessionStorage.recieved_amount;
      const change_amount = recieved_amount - ice_data.total;

      document.getElementById("date").innerText = today_string;
      document.getElementById("subtotal").innerText = subtotal_price + "円";
      document.getElementById("tax").innerText = tax_price + "円";
      document.getElementById("afterDiscount").innerText = discount_price + "円";
      document.getElementById("final").innerText = ice_data.total + "円";
      document.getElementById("recieved").innerText = recieved_amount + "円";
      document.getElementById("change").innerText = change_amount + "円";

      sessionStorage.clear();
    </script>

        <div class="text-right" id="confirm">
        <a href="http://localhost:8080/iceshop/main">
        <input type="submit" class="btn btn-outline-danger float-end"  value="次の会計へ">
        <br><br>
        </a>
    </div>

  </body>
</html>
