<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
  </head>
  <body>
    <script>
    $(function(){
      
      // ボタン押下時の処理
      $('#btn').on('click',function(){
        $.ajax({
          url: "AjaxServlet",
          type: "GET",
          data: {num : $("#text1").val()}
        }).done(function (result) {
          // 通信成功時のコールバック
          $("#text1").val(result);
        }).fail(function () {
          // 通信失敗時のコールバック
          alert("読み込み失敗");
        }).always(function (result) {
          // 常に実行する処理
        });
      });
      
    });
    </script>
    <h2>Hello Application</h2>
    <input type="text" id="text1" />
    <br><br>
    <button id="btn">Ajax</button>
  </body>
</html>