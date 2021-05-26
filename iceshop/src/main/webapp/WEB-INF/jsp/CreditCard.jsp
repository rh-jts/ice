<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ja">
  <head>
  <meta charset="UTF-8">
  <title>クレジットカード画面</title>
<style>
/* マージンとパディングの設定 */
div.site-box{
  margin: 10px;
  padding: 10px;
}
body{
  margin:10px;
  margin-left:20px;
  padding:18px;
}

/* 各ボタンの位置を設定 */
div.left{text-align:left;}
div.center1{text-align:center;}
div.center2{text-align:center;}
div.right{text-align:right;}
</style>

<!-- cssフォルダからbootstrapのcssシートを呼び出す -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous"
    />
</head>

<!-- サイトの枠線の設定 -->
<body class="border border-dark"
      style="width:800px">

  <div id="site-box">

    <!-- タイトル部分の設定 -->
    <h1 style="font-size:30px;
    text-align:center;
    white-space: nowrap;">
    クレジットカードの支払い回数をお選びください
    </h1>

    <!-- 戻るボタンの設定 -->
    <div class="left">
     <button type="button"
      class="btn btn-primary"
      style="width:110px;
      height:50px;
      font-size:25px;
      font-weight:bold;"
      onmouseover="this.style.background='#00ffff'"
      onmouseout="this.style.background='#0066ff'"
      onclick="location.href='http://localhost:8080/iceshop/main'" >
      <!-- クリックしたらメイン画面に戻る -->
      <!-- 今の設定だと阿部寛のHPに飛んでしまうので書き換えが必要 -->

      ←
      </button>
    </div>

    <br><br>

    <!-- 支払い回数の部分を非活性ボタンで設定 -->
    <div class="center1">
      <button type="button"
       disabled
        class="btn btn-outline-dark"
        style="width:180px;
        height:50px;
        font-size:25px;
        font-weight:bold;">
        支払い回数
      </button>
    </div>

    <!-- 1回ボタンを設定 -->
    <div class="center2">
      <button type="button"
        class="btn btn-info"
        id="button1"
        style="width:180px;
        height:50px;
        font-size:25px;
        font-weight:bold;"
        onmouseover="this.style.background='#00ffff'"
        onmouseout="this.style.background='#00ccff'"
        onclick="this.style.background='#ccffff';func1();">
        1回
      </button>
    </div>

    <br><br>

    <!-- 決定ボタンを設定 -->
    <div class="right">
      <button type="button"
        class="btn btn-danger"
        id="button2"
        disabled
        style="width:110px;
        height:50px;
        font-size: 25px;
        font-weight: bold;"
        onmouseover="this.style.background='#ff0000'"
        onmouseout="this.style.background='#ff9999'"
        onclick="location.href='http://localhost:8080/iceshop/receipt'" >
      <!-- クリックしたらレシート画面に進む -->


        決定
      </button>
    </div>
  </div>

  <!-- 活性・非活性切り替えスクリプトを設定 -->
  <script>
    // func1メソッドを設定
    function func1() {
      // 1回ボタンを押すと決定ボタンが活性化する
      document.getElementById("button1").disabled = true;
      // 決定ボタンが活性化すると1回ボタンが非活性化する
      document.getElementById("button2").disabled = false;
    }
  </script>

  <!-- JQuary呼び出しスクリプトを設定 -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
  </script>
  <!-- BootstrapのJS読み込み -->
  <script src="js/bootstrap.min.js">
  </script>
  <!-- 上記2つを設定しないとブートストラップが使えない。理由は知らん -->

</body>
</html>